package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.factory.UploadFactory;
import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.ResponseEnum;
import com.jzj.vblog.utils.sign.SpringUtils;
import com.jzj.vblog.web.mapper.ArticleContentMapper;
import com.jzj.vblog.web.mapper.ArticleInformMapper;
import com.jzj.vblog.web.pojo.entity.ArticleContent;
import com.jzj.vblog.web.pojo.entity.ArticleInform;
import com.jzj.vblog.web.pojo.entity.SysDictData;
import com.jzj.vblog.web.pojo.vo.ArticleAddVo;
import com.jzj.vblog.web.pojo.vo.ArticleVo;
import com.jzj.vblog.web.service.ArticleInformService;
import com.jzj.vblog.web.service.SysConfigService;
import com.jzj.vblog.web.service.SysDictTypeService;
import com.jzj.vblog.web.service.UploadService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * 文章基础信息 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-23
 */
@Service
public class ArticleInformServiceImpl extends ServiceImpl<ArticleInformMapper, ArticleInform> implements ArticleInformService {

    private static final String DICT_TAG_NAME = "sys_article_tag";

    @Autowired
    private ArticleInformMapper articleInformMapper;

    @Autowired
    private ArticleContentMapper articleContentMapper;

    @Autowired
    private SysDictTypeService dictTypeService;

    @Autowired
    private SysConfigService sysConfigService;

    private ThreadPoolTaskExecutor threadPoolTaskExecutor = SpringUtils.getBean("threadPoolTaskExecutor");

    /**
     * 后台分页文章列表
     * @param entity
     * @return
     */
    @Override
    public List<ArticleInform> selectList(ArticleInform entity) {
        List<SysDictData> tagList = dictTypeService.selectDictDataByType(DICT_TAG_NAME); //获取标签列表
        List<ArticleInform> articleList = articleInformMapper.selectArticleList(entity);
        for (ArticleInform inform : articleList) {
            List<String> strings = new ArrayList<>();
            String[] tagArray = inform.getArticleTag().split(",");
            for (String tag : tagArray) {
                for (SysDictData sysDictData : tagList) {
                    if(tag.equals(sysDictData.getDictValue())){
                        strings.add(sysDictData.getDictLabel());
                    }
                }
            }
            inform.setArticleTagList(strings);
        }
        return articleList;
    }


    /**
     * 前台分页显示文章
     * @param page
     * @param limit
     * @return
     */
    @Override
    public HashMap<String, Object> listPage(Long page, Long limit) {
        HashMap<String, Object> map = new HashMap<>();
        Page<ArticleVo> articleVoPage = articleInformMapper.selectPageVo(new Page<>(page, limit)); //分页查询
        if(articleVoPage.getTotal()>0){
            List<SysDictData> tagList = dictTypeService.selectDictDataByType(DICT_TAG_NAME); //获取标签列表
            List<ArticleVo> list = articleVoPage.getRecords(); //获取列表
            list.forEach(s->{
                List<String> tags = new ArrayList<>();
                s.setCommentsCount(0); //封装评论数
                String[] imgArray = s.getLogImg().split(",");
                s.setBanner(imgArray); //封装轮播图
                String[] tagArray = s.getTagIds().split(",");
                //封装标签
                for (String tag : tagArray) {
                    for (SysDictData sysDictData : tagList) {
                        if(tag.equals(sysDictData.getDictValue())){
                            tags.add(sysDictData.getDictLabel());
                        }
                    }
                }
                s.setTagNameArray(tags.toArray(new String[tags.size()]));
            });
            map.put("items",list);
            map.put("currPage",page);
            map.put("hasNextPage",page * limit < articleVoPage.getTotal());
        }
        return map;
    }

    /**
     * 新增文章
     * @param vo
     * @return
     */
    @Transactional
    @Override
    public int articleAdd(ArticleAddVo vo) {
        //文章基础信息
        ArticleInform inform = new ArticleInform();
        BeanUtils.copyProperties(vo,inform);
        //初始化值
        inform.setClickRate(0L);
        inform.setCreateBy("admin");
        inform.setNumberLike(0L);
        articleInformMapper.insert(inform);
        //文章内容
        ArticleContent content = new ArticleContent();
        content.setId(inform.getId());
        content.setContent(vo.getContent());
        return articleContentMapper.insert(content);
    }

    @Override
    public ArticleAddVo getArticleById(String id) {
        if(id==null) throw new BusinessException(ResponseEnum.Model_NULL_ERROR);
        //文章基础信息
        ArticleAddVo model = articleInformMapper.selectArticleByIdVo(id);
        return model;
    }

    @Transactional
    @Override
    public int updateArticleById(ArticleAddVo vo) {
        if(vo == null || vo.getId()==null) throw new BusinessException(ResponseEnum.Model_NULL_ERROR);
        //文章基本信息
        ArticleInform inform = new ArticleInform();
        BeanUtils.copyProperties(vo,inform);
        articleInformMapper.updateById(inform);
        //文章内容
        ArticleContent content = new ArticleContent();
        content.setContent(vo.getContent());
        content.setId(vo.getId());
        return articleContentMapper.updateById(content);
    }

    @Transactional
    @Override
    public void deleteArticleById(String [] ids,HttpServletRequest request) {
        try {
            List<String> imgList = new ArrayList<>();
            //根据ids批量查询图片地址和内容id
            List<ArticleInform> list = articleInformMapper.selectBatchIds(Arrays.asList(ids));
            list.forEach(s -> imgList.add(s.getLogImg()));
            //批量删除图片
            CompletableFuture.runAsync(() -> {
                UploadService uploadService = UploadFactory.getUploadService(sysConfigService);
                uploadService.deleteBtnImg(imgList, request); //批量删除图片
            }, threadPoolTaskExecutor);
            //批量删除文章基础
            articleInformMapper.deleteBatchIds(Arrays.asList(ids));
            //批量删除文章内容
            articleContentMapper.deleteBatchIds(Arrays.asList(ids));
        } catch (Exception e) {
            log.error("批量删除错误:" + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            threadPoolTaskExecutor.shutdown();
        }
    }

}
