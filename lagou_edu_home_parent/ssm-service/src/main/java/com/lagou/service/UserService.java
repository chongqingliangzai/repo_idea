package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;

public interface UserService {
    PageInfo findAllUserByPage(UserVo userVo);
    User login(User user) throws Exception;
    List<Role> findUserRelationRoleById(Integer id);
    void userContextRole(UserVo userVo);
    public ResponseResult getUserPermissions(Integer userId);
}
