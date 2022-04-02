package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {
    List<PromotionSpace> findAllPromotionSpace();
    void savePromotionSpace(PromotionSpace promotionSpace);
    PromotionSpace findPromotionSpaceById(Integer id);
    void updatePromotionSpace(PromotionSpace promotionSpace);
}
