package com.jzj.vblog.job;

import com.jzj.vblog.job.base.BaseJob;
import com.jzj.vblog.utils.constant.CacheConstants;
import com.jzj.vblog.utils.redis.RedisCache;
import com.jzj.vblog.web.mapper.SysCountMapper;
import com.jzj.vblog.web.pojo.entity.ArticleInform;
import com.jzj.vblog.web.pojo.entity.SysCount;
import com.jzj.vblog.web.service.ArticleInformService;
import com.jzj.vblog.web.service.ArticleSummaryService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章统计定时计划
 */
@Slf4j
public class ArticleCountJob implements BaseJob {

    private static final int BATCH_COUNT = 500;

    @Autowired
    private SysCountMapper sysCountMapper;

    @Autowired
    private ArticleSummaryService articleSummaryService;
    @Autowired
    private ArticleInformService articleInformService;

    @Autowired
    private RedisCache redisCache;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //点击数更新集合
        List<ArticleInform> newList = new ArrayList<>();
        try {
            SysCount sysCount = new SysCount();
            //统计归档数
            sysCount.setSummaryCount((long) articleSummaryService.count());
            //统计文章数
            sysCount.setArticleCount((long) articleInformService.count());
            //统计点击数,点赞数
            List<ArticleInform> list = articleInformService.list();
            long clickCount = 0L;
            long likeCount = 0L;
            for (ArticleInform inform : list) {
                String id = inform.getId();
                String key = CacheConstants.VBLOG_ARTICLE_CLICK +id;
                if(redisCache.hasKey(key)){
                    Number count = redisCache.getCacheObject(key);
                    if(count.longValue()>inform.getClickRate()){
                        inform.setClickRate(count.longValue());
                        newList.add(inform);
                        redisCache.deleteObject(key);
                    }
                }
                clickCount += inform.getClickRate();
                likeCount += inform.getNumberLike();
            }
            if(newList.size()>BATCH_COUNT){
                articleInformService.updateBatchById(newList);
                newList.clear();
            }
            sysCount.setUserCount(0L);
            sysCount.setClickCount(clickCount);
            sysCount.setLikeCount(likeCount);
            //获取昨天的数据
            long clickDayCount = sysCount.getClickCount();
            long likeDayCount = sysCount.getLikeCount();
            SysCount beforeDayCount = sysCountMapper.getBeforeDayCount();
            if(null!=beforeDayCount){
                clickDayCount -= beforeDayCount.getClickCount();
                likeDayCount  -= beforeDayCount.getLikeCount();
            }
            sysCount.setClickDayCount(clickDayCount);
            sysCount.setLikeDayCount(likeDayCount);
            sysCountMapper.insert(sysCount);
        } catch (Exception e) {
            log.error("文章统计定时计划执行失败,失败原因:" + e.getMessage());
            e.printStackTrace();
        } finally {
            if(newList.size()>0){
                articleInformService.updateBatchById(newList);
            }
        }
    }
}
