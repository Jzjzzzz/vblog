package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.factory.UploadFactory;
import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.sign.SpringUtils;
import com.jzj.vblog.utils.sign.StringUtils;
import com.jzj.vblog.web.mapper.ArticleSummaryMapper;
import com.jzj.vblog.web.pojo.entity.ArticleSummary;
import com.jzj.vblog.web.service.ArticleSummaryService;
import com.jzj.vblog.web.service.SysConfigService;
import com.jzj.vblog.web.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * 文章归档 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-08-11
 */
@Service
public class ArticleSummaryServiceImpl extends ServiceImpl<ArticleSummaryMapper, ArticleSummary> implements ArticleSummaryService {

    @Autowired
    private ArticleSummaryMapper articleSummaryMapper;

    @Autowired
    private SysConfigService sysConfigService;

    private ThreadPoolTaskExecutor threadPoolTaskExecutor = SpringUtils.getBean("threadPoolTaskExecutor");

    @Override
    public List<ArticleSummary> selectSummaryList(ArticleSummary articleSummary) {
        return articleSummaryMapper.selectSummaryList(articleSummary);
    }

    /**
     * 校验资源是否唯一
     */
    @Override
    public String checkSummaryUnique(ArticleSummary articleSummary) {
        String summaryId = StringUtils.isNull(articleSummary.getId()) ? "-1L" : articleSummary.getId();
        ArticleSummary model = articleSummaryMapper.checkSummaryUnique(articleSummary.getName());
        if (StringUtils.isNotNull(model) && !model.getId().equals(summaryId) )
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int insertSummary(ArticleSummary articleSummary) {
        return articleSummaryMapper.insert(articleSummary);
    }

    @Override
    public int deleteSummaryByIds(List<String> ids, HttpServletRequest request) {
        try {
            List<String> imgList = new ArrayList<>();
            //根据ids查询
            List<ArticleSummary> list = articleSummaryMapper.selectBatchIds(ids);
            list.forEach(s -> imgList.add(s.getBanner()));
            int result = articleSummaryMapper.deleteBatchIds(ids);
            //多线程执行批量删除图片操作
            CompletableFuture.runAsync(() -> {
                UploadService uploadService = UploadFactory.getUploadService(sysConfigService);
                uploadService.deleteBtnImg(imgList, request); //批量删除图片
            }, threadPoolTaskExecutor);
            return result;
        } catch (Exception e) {
            log.error("批量删除错误:" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArticleSummary selectSummaryById(String id) {
        return articleSummaryMapper.selectById(id);
    }

    @Override
    public int updateSummary(ArticleSummary articleSummary) {
        return articleSummaryMapper.updateById(articleSummary);
    }

    @Override
    public HashMap<String, Object> summaryPage(Map<String, Object> queryMap) {
        HashMap<String, Object> map = new HashMap<>();
        Integer page = (Integer) queryMap.get("currPage");
        Integer limit = (Integer) queryMap.get("limit");
        Page<ArticleSummary> summaryPage = articleSummaryMapper.selectPage(new Page<>(page, limit), null);
        if(summaryPage.getTotal()>0){
            map.put("items",summaryPage.getRecords());
            map.put("currPage",page);
            map.put("hasNextPage",page * limit < summaryPage.getTotal());
        }
        return map;
    }
}
