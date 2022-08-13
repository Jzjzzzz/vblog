package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.ArticleSummary;

import java.util.List;

/**
 * <p>
 * 文章归档 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2022-08-11
 */
public interface ArticleSummaryMapper extends BaseMapper<ArticleSummary> {

    /**
     * 条件查询归档列表
     *
     * @param articleSummary
     * @return
     */
    List<ArticleSummary> selectSummaryList(ArticleSummary articleSummary);

    /**
     * 唯一性检查
     *
     * @param name
     * @return
     */
    ArticleSummary checkSummaryUnique(String name);
}
