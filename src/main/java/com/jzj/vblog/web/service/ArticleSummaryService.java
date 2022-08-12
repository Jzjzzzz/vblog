package com.jzj.vblog.web.service;

import com.jzj.vblog.web.pojo.entity.ArticleSummary;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章归档 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-08-11
 */
public interface ArticleSummaryService extends IService<ArticleSummary> {

    List<ArticleSummary> selectSummaryList(ArticleSummary articleSummary);

    String checkSummaryUnique(ArticleSummary articleSummary);

    /**
     * 检查置顶数是否已超过3
     * @return
     */
    boolean checkSummaryTop(String topStatus);

    int insertSummary(ArticleSummary articleSummary);

    int deleteSummaryByIds(List<String> ids, HttpServletRequest request);

    ArticleSummary selectSummaryById(String id);

    int updateSummary(ArticleSummary articleSummary);

    /**
     * 前台分页获取归档
     * @param queryMap
     * @return
     */
    HashMap<String, Object> summaryPage(Map<String, Object> queryMap);
}
