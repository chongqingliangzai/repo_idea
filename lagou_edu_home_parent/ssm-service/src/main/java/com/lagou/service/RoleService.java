package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {
    List<Role> findAllRole(Role role);
    List<Integer> findMenuByRoleId(Integer roleId);
    void roleContextMenu(RoleMenuVo roleMenuVo);
    void deleteRole(Integer id);
}
