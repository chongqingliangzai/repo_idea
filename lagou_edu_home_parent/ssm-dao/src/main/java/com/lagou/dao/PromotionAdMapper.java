package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {
    /*
    分页查询
     */
    public List<PromotionAd> findAllPromotionAd();
    /*
    广告上下线
     */
    void updatePromotionAdStatus(PromotionAd promotionAd);
}
