package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.factory.UploadFactory;
import com.jzj.vblog.utils.constant.CacheConstants;
import com.jzj.vblog.utils.redis.RedisCache;
import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.ResponseEnum;
import com.jzj.vblog.utils.sign.IpUtils;
import com.jzj.vblog.utils.sign.ServletUtils;
import com.jzj.vblog.utils.sign.SpringUtils;
import com.jzj.vblog.utils.sign.StringUtils;
import com.jzj.vblog.web.mapper.ArticleContentMapper;
import com.jzj.vblog.web.mapper.ArticleInformMapper;
import com.jzj.vblog.web.mapper.ArticleSummaryMapper;
import com.jzj.vblog.web.pojo.entity.ArticleContent;
import com.jzj.vblog.web.pojo.entity.ArticleInform;
import com.jzj.vblog.web.pojo.entity.SysDictData;
import com.jzj.vblog.web.pojo.vo.ArticleAddVo;
import com.jzj.vblog.web.pojo.vo.ArticleRankVo;
import com.jzj.vblog.web.pojo.vo.ArticleVo;
import com.jzj.vblog.web.service.ArticleInformService;
import com.jzj.vblog.web.service.SysConfigService;
import com.jzj.vblog.web.service.SysDictTypeService;
import com.jzj.vblog.web.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 文章基础信息 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-23
 */
@Service
@Slf4j
public class ArticleInformServiceImpl extends ServiceImpl<ArticleInformMapper, ArticleInform> implements ArticleInformService {

    private static final String LIKE_IP = "like_ip:";

    @Autowired
    private ArticleInformMapper articleInformMapper;

    @Autowired
    private ArticleContentMapper articleContentMapper;

    @Autowired
    private ArticleSummaryMapper articleSummaryMapper;

    @Autowired
    private SysDictTypeService dictTypeService;

    @Autowired
    private SysConfigService sysConfigService;

    @Autowired
    private RedisCache redisCache;

    private ThreadPoolTaskExecutor threadPoolTaskExecutor = SpringUtils.getBean("threadPoolTaskExecutor");

    /**
     * 后台分页文章列表
     *
     * @param entity
     * @return
     */
    @Override
    public List<ArticleInform> selectList(ArticleInform entity) {
        //获取标签列表
        List<SysDictData> tagList = dictTypeService.selectDictDataByType(CacheConstants.SYS_ARTICLE_TAG);
        List<ArticleInform> articleList = articleInformMapper.selectArticleList(entity);
        /* 封装标签 */
        for (ArticleInform inform : articleList) {
            List<String> tags = getTags(tagList, inform.getArticleTag());
            inform.setArticleTagList(tags);
        }
        return articleList;
    }


    /**
     * 前台分页显示文章
     *
     * @return
     */
    @Override
    public HashMap<String, Object> listPage(Map<String, Object> queryMap) {
        HashMap<String, Object> map = new HashMap<>(3);
        //获取查询条件
        Integer page = (Integer) queryMap.get("currPage");
        Integer limit = (Integer) queryMap.get("limit");
        String tagId = (String) queryMap.get("tagId");
        //分页查询
        Page<ArticleVo> pageList = articleInformMapper.selectPageVo(new Page<>(page, limit), tagId);
        if (pageList.getTotal() > 0) {
            //获取标签列表
            List<SysDictData> tagList = dictTypeService.selectDictDataByType(CacheConstants.SYS_ARTICLE_TAG);
            //获取列表
            List<ArticleVo> list = pageList.getRecords();
            list.forEach(s -> {
                //封装评论数
                s.setCommentsCount(0);
                String[] imgArray = s.getLogImg().split(",");
                //封装轮播图
                s.setBanner(imgArray);
                //封装标签
                List<String> tags = getTags(tagList, s.getTagIds());
                s.setTagNameArray(tags.toArray(new String[tags.size()]));
            });
            map.put("items", list);
            map.put("currPage", page);
            map.put("totalPage",(pageList.getTotal()+limit-1)/limit);
            map.put("tagList",tagList);
        }
        return map;
    }

    /**
     * 新增文章
     *
     * @param vo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int articleAdd(ArticleAddVo vo) {
        //文章基础信息
        ArticleInform inform = new ArticleInform();
        BeanUtils.copyProperties(vo, inform);
        //初始化值
        inform.setClickRate(0L);
        inform.setCreateBy("漫漫长路");
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
        if (id == null) {
            throw new BusinessException(ResponseEnum.Model_NULL_ERROR);
        }
        //文章基础信息
        ArticleAddVo model = articleInformMapper.selectArticleByIdVo(id);
        return model;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateArticleById(ArticleAddVo vo) {
        if (vo == null || vo.getId() == null) {
            throw new BusinessException(ResponseEnum.Model_NULL_ERROR);
        }
        //文章基本信息
        ArticleInform inform = new ArticleInform();
        BeanUtils.copyProperties(vo, inform);
        articleInformMapper.updateById(inform);
        //文章内容
        ArticleContent content = new ArticleContent();
        content.setContent(vo.getContent());
        content.setId(vo.getId());
        return articleContentMapper.updateById(content);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteArticleById(String[] ids, HttpServletRequest request) {
        try {
            List<String> imgList = new ArrayList<>();
            //根据ids批量查询图片地址和内容id
            List<ArticleInform> list = articleInformMapper.selectBatchIds(Arrays.asList(ids));
            list.forEach(s -> imgList.add(s.getLogImg()));
            //批量删除图片
            CompletableFuture.runAsync(() -> {
                UploadService uploadService = UploadFactory.getUploadService(sysConfigService);
                uploadService.deleteBtnImg(imgList, request);
            }, threadPoolTaskExecutor);
            //批量删除文章基础
            articleInformMapper.deleteBatchIds(Arrays.asList(ids));
            //批量删除文章内容
            articleContentMapper.deleteBatchIds(Arrays.asList(ids));
        } catch (Exception e) {
            log.error("批量删除错误:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArticleAddVo getFrontArticleById(String id) {
        if (id == null) {
            throw new BusinessException(ResponseEnum.Model_NULL_ERROR);
        }
        ArticleAddVo model = articleInformMapper.selectFrontArticleByIdVo(id);
        //获取标签列表
        List<SysDictData> tagList = dictTypeService.selectDictDataByType(CacheConstants.SYS_ARTICLE_TAG);
        List<String> tags = getTags(tagList, model.getArticleTag());
        model.setArticleTag(String.join(",", tags));
        //异步更新点击数
        CompletableFuture.runAsync(() -> {
            ArticleInform inform = articleInformMapper.selectById(id);
            inform.setClickRate(inform.getClickRate() + 1);
            articleInformMapper.updateById(inform);
        }, threadPoolTaskExecutor);
        return model;
    }

    /**
     * 获取后台文章排行榜
     *
     * @return
     */
    @Override
    public List<ArticleRankVo> getRank() {
        return articleInformMapper.selectArticleRank();
    }

    /**
     * 文章点赞
     *
     * @param id 文章id
     */
    @Override
    public void getByIdLike(String id) {
        try {
            if (id == null) {
                throw new BusinessException("数据异常");
            }
            //请求IP
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            String redisIp = redisCache.getCacheObject(LIKE_IP + ip + "," + id);
            if (StringUtils.isNotEmpty(redisIp)) {
                throw new BusinessException(201, "该文章已点赞");
            }
            ArticleInform inform = articleInformMapper.selectById(id);
            inform.setNumberLike(inform.getNumberLike() + 1);
            articleInformMapper.updateById(inform);
            //存入redis
            redisCache.setCacheObject(LIKE_IP + ip + "," + id, ip, 30, TimeUnit.MINUTES);
        } catch (Exception e) {
        }
    }

    /**
     * 根据标签编号获取中文名称
     *
     * @param tagList
     * @return
     */
    private List<String> getTags(List<SysDictData> tagList, String tagIds) {
        String[] tagArray = tagIds.split(",");
        List<String> tags = new ArrayList<>();
        //封装标签
        for (String tag : tagArray) {
            for (SysDictData sysDictData : tagList) {
                if (tag.equals(sysDictData.getDictValue())) {
                    tags.add(sysDictData.getDictLabel());
                }
            }
        }
        return tags;
    }

}
