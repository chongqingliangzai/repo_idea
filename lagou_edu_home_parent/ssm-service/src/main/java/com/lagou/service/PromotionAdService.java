package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;

public interface PromotionAdService {
    /*
    分页查询
     */
    public PageInfo<PromotionAd> findAllPromotionAd(PromotionAdVo promotionAdVo);
    void updatePromotionAdStatus(int id,int status);
}
