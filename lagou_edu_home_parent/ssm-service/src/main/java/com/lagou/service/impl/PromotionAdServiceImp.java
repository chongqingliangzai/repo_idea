package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImp implements PromotionAdService {
    @Autowired
    private PromotionAdMapper promotionAdMapper;
    @Override
    public PageInfo<PromotionAd> findAllPromotionAd(PromotionAdVo promotionAdVo) {
        PageHelper.startPage(promotionAdVo.getCurrentPage(), promotionAdVo.getPageSize());
        List<PromotionAd> allPromotionAd = promotionAdMapper.findAllPromotionAd();
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(allPromotionAd);
        return pageInfo;
    }

    @Override
    public void updatePromotionAdStatus(int id, int status) {
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setUpdateTime(new Date());
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
