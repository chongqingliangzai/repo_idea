package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {
    /*
    分页多条件查询
     */
    List<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
