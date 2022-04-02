package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {
    @Autowired
    private ResourceCategoryService categoryService;
    @RequestMapping("/")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> allResourceCategory = categoryService.findAllResourceCategory();
        return new ResponseResult(true,200,"查询所有资源文件成功",allResourceCategory);
    }
}