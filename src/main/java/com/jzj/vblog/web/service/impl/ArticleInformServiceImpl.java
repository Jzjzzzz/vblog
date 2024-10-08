package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.utils.constant.CacheConstants;
import com.jzj.vblog.utils.redis.RedisCache;
import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.ResponseEnum;
import com.jzj.vblog.utils.sign.IpUtils;
import com.jzj.vblog.utils.sign.ServletUtils;
import com.jzj.vblog.utils.sign.StringUtils;
import com.jzj.vblog.web.mapper.ArticleContentMapper;
import com.jzj.vblog.web.mapper.ArticleInformMapper;
import com.jzj.vblog.web.pojo.entity.ArticleContent;
import com.jzj.vblog.web.pojo.entity.ArticleInform;
import com.jzj.vblog.web.pojo.entity.SysDictData;
import com.jzj.vblog.web.pojo.entity.SysWebInformation;
import com.jzj.vblog.web.pojo.vo.*;
import com.jzj.vblog.web.service.ArticleInformService;
import com.jzj.vblog.web.service.SysDictTypeService;
import com.jzj.vblog.web.service.SysWebInformationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 文章基础信息 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-23 11:12
 */
@Service
public class ArticleInformServiceImpl extends ServiceImpl<ArticleInformMapper, ArticleInform> implements ArticleInformService {

    private static final String LIKE_IP = "like_ip:";

    @Autowired
    private ArticleInformMapper articleInformMapper;

    @Autowired
    private ArticleContentMapper articleContentMapper;

    @Autowired
    private SysDictTypeService dictTypeService;

    @Autowired
    private SysWebInformationService webInformationService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public List<ArticleInform> selectList(ArticleInform entity) {
        //获取标签列表
        List<SysDictData> tagList = dictTypeService.selectDictDataByType(CacheConstants.SYS_ARTICLE_TAG);
        List<ArticleInform> articleList = articleInformMapper.selectArticleList(entity);
        /* 封装标签 */
        for (ArticleInform inform : articleList) {
            List<String> tags = getTags(tagList, inform.getArticleTag());
            inform.setArticleTagList(tags);
            String key = CacheConstants.VBLOG_ARTICLE_CLICK + inform.getId();
            if (redisCache.hasKey(key)) {
                Number count = redisCache.getCacheObject(key);
                inform.setClickRate(count.longValue());
            }
        }
        return articleList;
    }


    @Override
    public HashMap<String, Object> listPage(Map<String, Object> query) {
        HashMap<String, Object> map = new HashMap<>();
        //获取查询条件
        Integer page = (Integer) query.get("currPage");
        Integer limit = (Integer) query.get("limit");
        String tagId = (String) query.get("tagId");
        String summaryId = (String) query.get("summaryId");
        //分页查询
        Page<ArticleVo> pageList = articleInformMapper.selectPageVo(new Page<>(page, limit), tagId, summaryId);
        //获取标签列表
        List<SysDictData> tagList = dictTypeService.selectDictDataByType(CacheConstants.SYS_ARTICLE_TAG);
        if (pageList.getTotal() > 0) {
            //获取列表
            List<ArticleVo> list = pageList.getRecords();
            list.forEach(s -> {
                String[] imgArray = s.getLogImg().split(",");
                //封装轮播图
                s.setBanner(imgArray);
                //封装标签
                List<String> tags = getTags(tagList, s.getTagIds());
                s.setTagNameArray(tags.toArray(new String[tags.size()]));
                String key = CacheConstants.VBLOG_ARTICLE_CLICK + s.getId();
                if (redisCache.hasKey(key)) {
                    Number count = redisCache.getCacheObject(key);
                    s.setViewsCount(count.longValue());
                }
            });
            map.put("items", list);
        }
        map.put("currPage", page);
        map.put("totalPage", (pageList.getTotal() + limit - 1) / limit);
        map.put("tagList", tagList);
        return map;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int articleAdd(ArticleAddVo vo) {
        //获取站点信息
        SysWebInformation information = webInformationService.selectWebInformationById();
        //文章基础信息
        ArticleInform inform = new ArticleInform();
        BeanUtils.copyProperties(vo, inform);
        //初始化值
        inform.setClickRate(0L);
        inform.setCreateBy(information.getWebName());
        inform.setNumberLike(0L);
        inform.setCommentNumber(0);
        articleInformMapper.insert(inform);
        //文章内容
        ArticleContent content = new ArticleContent();
        content.setId(inform.getId());
        content.setContent(vo.getContent());
        content.setHtmlContent(vo.getHtmlContent());
        return articleContentMapper.insert(content);
    }

    @Override
    public ArticleAddVo getArticleById(String id) {
        if (id == null) throw new BusinessException(ResponseEnum.Model_NULL_ERROR);
        //文章基础信息
        return articleInformMapper.selectArticleByIdVo(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateArticleById(ArticleAddVo vo) {
        if (vo == null || vo.getId() == null) throw new BusinessException(ResponseEnum.Model_NULL_ERROR);
        //文章基本信息
        ArticleInform inform = new ArticleInform();
        BeanUtils.copyProperties(vo, inform);
        articleInformMapper.updateById(inform);
        //文章内容
        ArticleContent content = new ArticleContent();
        content.setContent(vo.getContent());
        content.setHtmlContent(vo.getHtmlContent());
        content.setId(vo.getId());
        return articleContentMapper.updateById(content);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteArticleById(String[] ids, HttpServletRequest request) {
        try {
            //批量删除文章基础
            articleInformMapper.deleteBatchIds(Arrays.asList(ids));
            //批量删除文章内容
            articleContentMapper.deleteBatchIds(Arrays.asList(ids));
            return true;
        } catch (Exception e) {
            log.error("批量删除出错", e);
            return false;
        }
    }

    @Override
    public ArticleFrontVo getFrontArticleById(String id) {
        String key = CacheConstants.VBLOG_ARTICLE_CLICK + id;
        if (StringUtils.isEmpty(id)) {
            throw new BusinessException(ResponseEnum.Model_NULL_ERROR);
        }
        ArticleFrontVo model = articleInformMapper.selectFrontArticleByIdVo(id);
        //封装标签列表
        List<SysDictData> tagList = dictTypeService.selectDictDataByType(CacheConstants.SYS_ARTICLE_TAG);
        //全部标签列表
        model.setTagList(tagList);
        List<ArticleNextPreData> preNextList = getPreNextList(model);
        model.setArticleNextPreDataList(preNextList);
        //获取精品文章列表
        model.setArticlePopularList(articleInformMapper.selectArticlePopular());
        if (redisCache.hasKey(key)) {
            redisCache.count(key, null);
        } else {
            redisCache.count(key, model.getClickRate());
        }
        return model;
    }

    private List<ArticleNextPreData> getPreNextList(ArticleFrontVo model) {
        //封装上一条
        List<ArticleNextPreData> nextPreList = new ArrayList<>();
        ArticleNextPreData preData = new ArticleNextPreData();
        List<ArticleInform> preArticle = articleInformMapper.selectList(new QueryWrapper<ArticleInform>()
                .ne("id", model.getId())
                .le("create_time", model.getCreateTime())
                .eq("status", "1")
                .select("article_title", "id")
                .orderByDesc("create_time")
                .last("limit 1"));
        preData.setType("pre");
        preData.setName("没有更多了");
        preData.setRoute("#");
        if (preArticle != null && !preArticle.isEmpty()) {
            preData.setName(preArticle.get(0).getArticleTitle());
            preData.setRoute(preArticle.get(0).getId());
        }
        nextPreList.add(preData);
        //封装下一条
        ArticleNextPreData nextData = new ArticleNextPreData();
        List<ArticleInform> nextArticle = articleInformMapper.selectList(new QueryWrapper<ArticleInform>()
                .ne("id", model.getId())
                .ge("create_time", model.getCreateTime())
                .eq("status", "1")
                .select("article_title", "id").orderByAsc("create_time")
                .last("limit 1"));
        nextData.setType("next");
        nextData.setName("没有更多了");
        nextData.setRoute("#");
        if (nextArticle != null && !nextArticle.isEmpty()) {
            nextData.setName(nextArticle.get(0).getArticleTitle());
            nextData.setRoute(nextArticle.get(0).getId());
        }
        nextPreList.add(nextData);
        return nextPreList;
    }

    @Override
    public List<ArticleRankVo> getRank() {
        List<ArticleRankVo> list = articleInformMapper.selectArticleRank();
        list.forEach(s -> {
            String key = CacheConstants.VBLOG_ARTICLE_CLICK + s.getId();
            if (redisCache.hasKey(key)) {
                Number count = redisCache.getCacheObject(key);
                s.setClickRate(count.longValue());
            }
        });
        return list;
    }

    @Override
    public void getByIdLike(String id) {
        if (id == null) throw new BusinessException("数据异常");
        //请求IP
        String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        String redisIp = redisCache.getCacheObject(LIKE_IP + ip + "," + id);
        if (StringUtils.isNotEmpty(redisIp)) throw new BusinessException(201, "该文章已点赞");
        ArticleInform inform = articleInformMapper.selectById(id);
        inform.setNumberLike(inform.getNumberLike() + 1);
        articleInformMapper.updateById(inform);
        //存入redis
        redisCache.setCacheObject(LIKE_IP + ip + "," + id, ip, 30, TimeUnit.MINUTES);
    }

    @Transactional
    @Override
    public boolean updateSummaryById(String id, String summaryId, String type) {
        String[] ids = id.split(",");
        List<ArticleInform> informs = articleInformMapper.selectBatchIds(Arrays.asList(ids));
        for (ArticleInform inform : informs) {
            if ("1".equals(type)) {
                inform.setAggregateId(summaryId);
            } else {
                inform.setAggregateId("");
            }
        }
        return this.updateBatchById(informs);
    }

    @Override
    public ArticleHeadVo getHeadById(String id) {
        String key = CacheConstants.VBLOG_ARTICLE_CLICK + id;
        if (StringUtils.isEmpty(id)) {
            throw new BusinessException(ResponseEnum.Model_NULL_ERROR);
        }
        ArticleHeadVo model = articleInformMapper.selectFrontArticleHeadByIdVo(id);
        if (redisCache.hasKey(key)) {
            Number count = redisCache.getCacheObject(key);
            model.setClickRate(count.longValue());
        }
        //封装标签列表
        List<SysDictData> tagList = dictTypeService.selectDictDataByType(CacheConstants.SYS_ARTICLE_TAG);
        List<String> tags = getTags(tagList, model.getArticleTag());
        //文章标签列表
        model.setArticleTagList(tags);
        return model;
    }

    @Override
    public List<SwiperVo> getSwiperList() {
        return articleInformMapper.selectArticleTop();
    }

    @Override
    public List<SwiperVo> getArticleListKw(String kw) {
        return articleInformMapper.selectArticleListKw(kw);
    }

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
