package com.jzj.vblog.web.service.impl;

import com.jzj.vblog.web.pojo.entity.ArticleInform;
import com.jzj.vblog.web.mapper.ArticleInformMapper;
import com.jzj.vblog.web.pojo.entity.SysDictData;
import com.jzj.vblog.web.pojo.entity.SysDictType;
import com.jzj.vblog.web.service.ArticleInformService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.web.service.SysDictDataService;
import com.jzj.vblog.web.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文章基础信息 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-23
 */
@Service
public class ArticleInformServiceImpl extends ServiceImpl<ArticleInformMapper, ArticleInform> implements ArticleInformService {

    private static final String DICT_TAG_NAME = "sys_article_tag";
    @Autowired
    private ArticleInformMapper articleInformMapper;

    @Autowired
    private SysDictTypeService dictTypeService;

    @Override
    public List<ArticleInform> selectList(ArticleInform entity) {

        List<SysDictData> tagList = dictTypeService.selectDictDataByType(DICT_TAG_NAME); //获取标签列表
        List<ArticleInform> articleList = articleInformMapper.selectArticleList(entity);

        for (ArticleInform inform : articleList) {
            ArrayList<String> strings = new ArrayList<>();
            String[] tagArray = inform.getArticleTag().split(",");
            for (String tag : tagArray) {
                for (SysDictData sysDictData : tagList) {
                    if(tag.equals(sysDictData.getDictValue())){
                        strings.add(sysDictData.getDictLabel());
                    }
                }
            }
            inform.setArticleTagList(strings);
        }
        return articleList;
    }

}
