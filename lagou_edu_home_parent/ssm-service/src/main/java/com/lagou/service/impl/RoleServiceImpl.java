package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleid);

        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {

        //1. 清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //2. 为角色分配菜单

        for (Integer mid : roleMenuVo.getMenuIdList()) {

            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            //封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");


            roleMapper.roleContextMenu(role_menu_relation);
        }

    }

    @Override
    public void deleteRole(Integer roleid) {

        // 调用根据roleid清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleid);

        roleMapper.deleteRole(roleid);
    }

    @Override
    public List<ResourceCategory> findResourceCategoryAndResourceByRoleId(Integer id) {
        List<ResourceCategory> resourceCategoryByRoleId = roleMapper.findResourceCategoryByRoleId(id);
        for (ResourceCategory resourceCategory : resourceCategoryByRoleId) {
            List<Resource> resourceByRoleId = roleMapper.findResourceByRoleId(id);
            resourceCategory.setResourceList(resourceByRoleId);
        }
        return resourceCategoryByRoleId;
    }

    @Override
    public void resourceContextRole(RoleResourceVo roleResourceVo) {
        //根据ID删除关联资源信息
        roleMapper.deleteResourceByRoleId(roleResourceVo.getRoleId());
        //更新关联资源信息
        List<Integer> resourceIdList = roleResourceVo.getResourceIdList();
        for (Integer integer : resourceIdList) {
            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
            roleResourceRelation.setRole_id(roleResourceVo.getRoleId());
            roleResourceRelation.setResource_id(integer);
            Date date = new Date();
            roleResourceRelation.setCreated_time(date);
            roleResourceRelation.setUpdated_time(date);
            roleResourceRelation.setCreated_by("system");
            roleResourceRelation.setUpdated_by("system");
            roleMapper.resourceContextRole(roleResourceRelation);
        }
    }
}
