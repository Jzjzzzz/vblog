package com.jzj.vblog.utils.sign;

import com.github.pagehelper.PageHelper;
import com.jzj.vblog.web.pojo.page.PageDomain;
import com.jzj.vblog.web.pojo.page.TableSupport;

/**
 * <p>
 * 分页工具类
 * </p>
 *
 * @author Jzj
 * @since 2022/7/11 14:31
 */
public class PageUtils {

    /**
     * 设置请求分页数据
     */
    public static void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        Boolean reasonable = pageDomain.getReasonable();
        PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
    }

    /**
     * 清理分页的线程变量
     */
    public static void clearPage()
    {
        PageHelper.clearPage();
    }
}
