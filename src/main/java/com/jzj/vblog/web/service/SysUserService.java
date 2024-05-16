package com.jzj.vblog.web.service;

import com.jzj.vblog.web.pojo.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.vo.UserUpdateVo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Jzj
 * @since 2024-04-30
 */
public interface SysUserService extends IService<SysUser>, UserDetailsService {

    /**
     * 分页查询
     *
     * @param sysUser 查询参数
     * @return 列表
     */
    List<SysUser> pageList(SysUser sysUser);

    /**
     * 新增用户表
     *
     * @param sysUser 实体
     * @return 成功条数
     */
    int add(SysUser sysUser);

    /**
     * 根据id查询单条记录
     *
     * @param id id
     */
    SysUser selectById(String id);

    /**
     * 修改用户表
     *
     * @param sysUser 实体
     * @return 成功条数
     */
    int modify(SysUser sysUser);

    /**
     * 批量删除用户表
     *
     * @param ids id数组
     * @return 成功条数
     */
    int deleteByIds(List<String> ids, HttpServletRequest request);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    /**
     * 获取用户信息
     *
     * @return 返回用户信息
     */
    Map<String, Object> info(String username);

    int updateUser(UserUpdateVo vo);
}
