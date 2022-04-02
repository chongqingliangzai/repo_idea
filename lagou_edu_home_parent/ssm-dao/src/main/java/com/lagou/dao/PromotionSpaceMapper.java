package com.lagou.dao;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {
    /*
    获取所有广告位
     */
    List<PromotionSpace> findAllPromotionSpace();
    /*
    添加广告位
     */
    void savePromotionSpace(PromotionSpace promotionSpace);
    /*
    查询广告为
     */
    PromotionSpace findPromotionSpaceById(Integer id);
    /*
    更新广告位
     */
    void updatePromotionSpace(PromotionSpace promotionSpace);
}
