package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {
    /*
    查询所有父子菜单信息
     */
    List<Menu> findSubMenuListByPid(int pid);
    List<Menu> findAllMenu();
    Menu findMenuById(Integer id);
}
