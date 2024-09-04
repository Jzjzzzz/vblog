package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.web.mapper.ArticleContentMapper;
import com.jzj.vblog.web.pojo.entity.ArticleContent;
import com.jzj.vblog.web.service.ArticleContentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章内容 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-23 11:12
 */
@Service
public class ArticleContentServiceImpl extends ServiceImpl<ArticleContentMapper, ArticleContent> implements ArticleContentService {

}
