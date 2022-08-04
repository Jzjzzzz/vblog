package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.web.mapper.ArticleContentMapper;
import com.jzj.vblog.web.mapper.ArticleInformMapper;
import com.jzj.vblog.web.pojo.entity.ArticleContent;
import com.jzj.vblog.web.pojo.entity.ArticleInform;
import com.jzj.vblog.web.pojo.entity.SysDictData;
import com.jzj.vblog.web.pojo.vo.ArticleAddVo;
import com.jzj.vblog.web.pojo.vo.ArticleVo;
import com.jzj.vblog.web.service.ArticleInformService;
import com.jzj.vblog.web.service.SysDictTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
        content.setInformId(inform.getId());
        content.setContent(vo.getContent());
        return articleContentMapper.insert(content);
    }

    @Override
    public ArticleAddVo getArticleById(String id) {
        //文章基础信息
        ArticleAddVo vo = articleInformMapper.selectArticleByIdVo(id);
        return null;
    }

}
