package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrtPage(),userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);
        PageInfo<User> userPageInfo = new PageInfo<>(allUserByPage);
        return userPageInfo;
    }

    @Override
    public User login(User user) throws Exception {
        User user1 = userMapper.login(user);
        if (user1 != null && Md5.verify(user.getPassword(),"lagou",user1.getPassword())){
            return user1;
        }else {
            return null;
        }


    }

    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> userRelationRoleById = userMapper.findUserRelationRoleById(id);

        return userRelationRoleById;
    }

    @Override
    public void userContextRole(UserVo userVo) {
        userMapper.deleteUserContextRole(userVo.getUserId());
        for (Integer integer : userVo.getRoleIdList()) {
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(integer);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            userMapper.userContextRole(user_role_relation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer userId) {
        //1.获取当前用户拥有的角色
        List<Role> userRelationRoleById = userMapper.findUserRelationRoleById(userId);
        //2.获取角色信息
        List<Integer> roleIds = new ArrayList<>();
        for (Role role : userRelationRoleById) {
            roleIds.add(role.getId());
        }
        //3.根据角色ID查询父菜单
        List<Menu> parentMenuByRoleId = userMapper.findParentMenuByRoleId(roleIds);
        //4.查询封装父菜单关联的子菜单
        for (Menu menu : parentMenuByRoleId) {
            List<Menu> subMenuByPid = userMapper.findSubMenuByPid(menu.getId());
            menu.setMenus(subMenuByPid);
        }
        List<Resource> resourceByRoleId = userMapper.findResourceByRoleId(roleIds);
        Map<String,Object> map = new HashMap<>();
        map.put("menuList",parentMenuByRoleId);
        map.put("resourceList",resourceByRoleId);
        return new ResponseResult(true,200,"获取用户信息成功",map);
    }
}
