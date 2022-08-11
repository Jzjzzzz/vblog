package com.jzj.vblog.web.service;

import com.jzj.vblog.web.pojo.entity.ArticleSummary;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    int insertSummary(ArticleSummary articleSummary);

    int deleteSummaryByIds(List<String> ids, HttpServletRequest request);

    ArticleSummary selectSummaryById(String id);

    int updateSummary(ArticleSummary articleSummary);
}
