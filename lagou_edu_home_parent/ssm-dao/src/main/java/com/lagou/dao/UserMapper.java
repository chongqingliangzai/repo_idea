package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {
    /*
    多条件组合查询
     */
    public List<User> findAllUserByPage(UserVo userVo);
    /*
    用户登录
     */
    User login(User user);
    /*
    根据用户id查询关联的用户信息
     */
    List<Role> findUserRelationRoleById(Integer id);
    /*
    根据用户ID清空用户表
     */
    void deleteUserContextRole(Integer id);

    void userContextRole(User_Role_relation user_role_relation);
    /*
    根据角色ID，查询角色所拥有的顶级菜单
     */
    List<Menu> findParentMenuByRoleId(List<Integer> ids);
    /*
    根据PID，查询子菜单信息
     */
    List<Menu> findSubMenuByPid(Integer id);
    /*
    获取用户拥有的资源权限信息
     */
    List<Resource> findResourceByRoleId(List<Integer> ids);
}
