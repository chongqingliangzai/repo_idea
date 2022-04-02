package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询成功,", allRole);
        return responseResult;
    }
    /*
    查询所有父子菜单信息
     */
    @Autowired
    private MenuService menuService;
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){
        /*查询所有的父级菜单*/
        List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList",subMenuListByPid);
        ResponseResult result = new ResponseResult(true, 200, "查询所有成功", map);
        return result;
    }
    @RequestMapping("/")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);
        ResponseResult result = new ResponseResult(true, 200, "查询所有成功", menuByRoleId);
        return result;
    }
    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVo role_menu_relation){
        roleService.roleContextMenu(role_menu_relation);
        ResponseResult result = new ResponseResult(true, 200, "查询所有成功", null);
        return result;
    }
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        ResponseResult result = new ResponseResult(true, 200, "删除所有成功", null);
        return result;
    }
}
