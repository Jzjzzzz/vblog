package com.jzj.vblog.web.mapper;

import com.jzj.vblog.web.pojo.entity.ArticleSummary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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

    List<ArticleSummary> selectSummaryList(ArticleSummary articleSummary);

    ArticleSummary checkSummaryUnique(String name);
}
