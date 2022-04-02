package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {
    /*
    查询所有角色
     */
    List<Role> findAllRole(Role role);
    /*
    根据角色Id查询状态信息
     */
    List<Integer> findMenuByRoleId(Integer roleId);
    /*
    根据roleId清空中间表关系
     */
    void deleteRoleContextMenu(Integer rid);
    /*
    为角色分配菜单信息
     */
    void roleContextMenu(Role_menu_relation role_menu_relation);
    /*
    删除角色
     */
    void deleteRole(Integer id);
}
