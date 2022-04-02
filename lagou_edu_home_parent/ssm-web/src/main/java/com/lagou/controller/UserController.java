package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo allUserByPage = userService.findAllUserByPage(userVo);
        return new ResponseResult(true,200,"查询成功",allUserByPage);
    }
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);
        if (user1 != null){
            //保存用户id及access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user1.getId());
            map.put("user",user1);
            return new ResponseResult(true,1,"登录成功",map);
        }else {
            return new ResponseResult(true,400,"用户名密码错误",null);
        }
    }
    @RequestMapping("/findUserRelationRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){
        List<Role> userRelationRoleById = userService.findUserRelationRoleById(id);
        ResponseResult result = new ResponseResult(true, 200, "查询成功", userRelationRoleById);
        return result;
    }
    @RequestMapping("/")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);
        return new ResponseResult(true,200,"修改成功",null);
    }
    /*
    获取用户权限进行菜单动态展示
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        String header_token = request.getHeader("Authorization");
        String session_token = (String) request.getSession().getAttribute("access_token");
        //3.判断token是否一直
        if (header_token.equals(session_token)){
            Integer user_id = (Integer)request.getSession().getAttribute("user_id");
            ResponseResult userPermissions = userService.getUserPermissions(user_id);
            return userPermissions;
        }else {
            ResponseResult result = new ResponseResult(false, 400, "获取数据信息失败", null);
            return result;
        }
    }
}
