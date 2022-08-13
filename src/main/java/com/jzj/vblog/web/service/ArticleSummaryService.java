package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.ArticleSummary;

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

    /**
     * 获取全部归档
     *
     * @param articleSummary
     * @return 列表
     */
    List<ArticleSummary> selectSummaryList(ArticleSummary articleSummary);

    /**
     * 唯一性检查
     *
     * @param articleSummary 归档
     * @return 校验返回结果码
     */
    String checkSummaryUnique(ArticleSummary articleSummary);

    /**
     * 检查置顶数是否已超过3
     *
     * @param topStatus 该条纪录是否置顶
     * @return true/false
     */
    boolean checkSummaryTop(String topStatus);

    /**
     * 新增归档
     *
     * @param articleSummary 归档
     * @return 修改条数
     */
    int insertSummary(ArticleSummary articleSummary);

    /**
     * 批量删除归档
     *
     * @param ids     id数组
     * @param request
     * @return 修改条数
     */
    int deleteSummaryByIds(List<String> ids, HttpServletRequest request);

    /**
     * 根据id查询归档
     *
     * @param id 归档id
     * @return 归档
     */
    ArticleSummary selectSummaryById(String id);

    /**
     * 修改归档
     *
     * @param articleSummary 归档
     * @return 修改条数
     */
    int updateSummary(ArticleSummary articleSummary);

    /**
     * 前台分页获取归档
     *
     * @param queryMap
     * @return
     */
    HashMap<String, Object> summaryPage(Map<String, Object> queryMap);
}
