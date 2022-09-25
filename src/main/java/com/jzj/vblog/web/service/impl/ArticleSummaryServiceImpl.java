package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.factory.UploadFactory;
import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.sign.SpringUtils;
import com.jzj.vblog.utils.sign.StringUtils;
import com.jzj.vblog.web.mapper.ArticleInformMapper;
import com.jzj.vblog.web.mapper.ArticleSummaryMapper;
import com.jzj.vblog.web.pojo.entity.ArticleInform;
import com.jzj.vblog.web.pojo.entity.ArticleSummary;
import com.jzj.vblog.web.pojo.entity.SysWebInformation;
import com.jzj.vblog.web.pojo.vo.ArticleListSummaryVo;
import com.jzj.vblog.web.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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

    private static final String TOP_STATUS = "1";

    @Autowired
    private ArticleSummaryMapper articleSummaryMapper;

    @Autowired
    private ArticleInformService articleInformService;

    @Autowired
    private SysConfigService sysConfigService;

    @Autowired
    private SysWebInformationService webInformationService;

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
        if (StringUtils.isNotNull(model) && !model.getId().equals(summaryId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public boolean checkSummaryTop(ArticleSummary articleSummary) {
        if (TOP_STATUS.equals(articleSummary.getTopStatus())) {
            if (articleSummary.getId() != null) {
                ArticleSummary summary = articleSummaryMapper.selectById(articleSummary.getId());
                if (summary.getTopStatus().equals(articleSummary.getTopStatus())) {
                    return false;
                }
            }
            return articleSummaryMapper.selectCount(new QueryWrapper<ArticleSummary>().eq("top_status", 1)) >= 3;
        }
        return false;
    }

    @Override
    public int insertSummary(ArticleSummary articleSummary) {
        //获取站点信息
        SysWebInformation information = webInformationService.selectWebInformationById();
        articleSummary.setAuthor(information.getWebName());
        return articleSummaryMapper.insert(articleSummary);
    }

    @Override
    public int deleteSummaryByIds(List<String> ids, HttpServletRequest request) {
        try {
            List<String> imgList = new ArrayList<>();
            //根据ids查询
            List<ArticleSummary> list = articleSummaryMapper.selectBatchIds(ids);
            list.forEach(s -> {
                imgList.add(s.getBanner());
                //更新文章基础表
                List<ArticleInform> informList = articleInformService.list(new QueryWrapper<ArticleInform>().eq("aggregate_id", s.getId()));
                if(informList.size()>0){
                    for (ArticleInform articleInform : informList) {
                        articleInform.setAggregateId("");
                    }
                    articleInformService.updateBatchById(informList);
                }
            });
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
        HashMap<String, Object> map = new HashMap<>(3);
        Integer page = (Integer) queryMap.get("currPage");
        Integer limit = (Integer) queryMap.get("limit");
        Page<ArticleSummary> summaryPage = articleSummaryMapper.selectPage(new Page<>(page, limit), new QueryWrapper<ArticleSummary>().eq("status", "1"));
        if (summaryPage.getTotal() > 0) {
            map.put("items", summaryPage.getRecords());
            map.put("hasNextPage", (long) page * limit < summaryPage.getTotal());
        }
        return map;
    }

    @Override
    public List<ArticleListSummaryVo> articleList(String id) {
        //获取文章列表
        List<ArticleInform> informs = articleInformService.list(new QueryWrapper<ArticleInform>().select("id","article_title","aggregate_id"));
        List<ArticleListSummaryVo> list = informs.stream().map(s -> {
            ArticleListSummaryVo vo = new ArticleListSummaryVo();
            vo.setKey(s.getId());
            vo.setLabel(s.getArticleTitle());
            if (id.equals(s.getAggregateId())) {
                vo.setBelong(1);
            }
            return vo;
        }).collect(Collectors.toList());
        return list;
    }
}
