package com.jzj.vblog.web.controller.admin;

import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.controller.BaseController;
import com.jzj.vblog.web.pojo.entity.FriendLink;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.page.TableDataInfo;
import com.jzj.vblog.web.service.FriendLinkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 友情链接 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-09-10
 */
@RestController
@RequestMapping("/friend/link")
public class FriendLinkController extends BaseController {
    @Autowired
    private FriendLinkService friendLinkService;

    @ApiOperation("分页列表")
    @GetMapping
    @PreAuthorize("hasAuthority('btn.link.list')")
    public TableDataInfo pageList(FriendLink friendLink) {
        startPage();
        List<FriendLink> list = friendLinkService.selectLinkList(friendLink);
        return getDataTable(list);
    }

    @Log(title = "友链管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增友链")
    @PostMapping
    @PreAuthorize("hasAuthority('btn.link.add')")
    public R add(@Validated @RequestBody FriendLink friendLink) {
        if (UserConstants.NOT_UNIQUE.equals(friendLinkService.checkLinkUnique(friendLink))) {
            return R.error("新增友链'" + friendLink.getName() + "'失败，友链已存在");
        }
        return toAjax(friendLinkService.insertLink(friendLink));
    }

    @ApiOperation("根据资源id获取资源详细信息")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('btn.link.list')")
    public R getInfo(@PathVariable String id) {
        return R.ok(friendLinkService.selectLinkById(id));
    }

    @ApiOperation("修改资源")
    @Log(title = "友链管理", businessType = BusinessType.UPDATE)
    @PutMapping
    @PreAuthorize("hasAuthority('btn.link.edit')")
    public R edit(@Validated @RequestBody FriendLink friendLink) {
        if (UserConstants.NOT_UNIQUE.equals(friendLinkService.checkLinkUnique(friendLink))) {
            return R.error("修改资源'" + friendLink.getName() + "'失败，资源已存在");
        }
        return toAjax(friendLinkService.updateLink(friendLink));
    }

    @ApiOperation("删除资源")
    @Log(title = "友链管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAuthority('btn.link.del')")
    public R remove(@PathVariable List<String> ids, HttpServletRequest request) {
        return toAjax(friendLinkService.deleteLinkByIds(ids, request));
    }

}

