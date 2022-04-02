package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

public interface ResourceService {
    PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
