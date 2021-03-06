package com.colia.yorik.web.interfaces.promotion.facade.impl;

import com.colia.yorik.application.promotion.PddPromotionService;
import com.colia.yorik.application.promotion.request.ConvertUrlRequest;
import com.colia.yorik.application.promotion.request.PromotionUrlRequest;
import com.colia.yorik.web.interfaces.promotion.facade.PromotionFacade;
import com.colia.yorik.web.interfaces.promotion.facade.adapter.UrlDTOAssembler;
import com.colia.yorik.web.interfaces.promotion.facade.dto.UrlDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author konglingyao
 * @Date 2020/7/31
 */
@Slf4j
@Service
public class PromotionFacadeImpl implements PromotionFacade {

    @Resource
    private UrlDTOAssembler urlDTOAssembler;

    @Resource
    private PddPromotionService pddPromotionService;

    /**
     * 生成推广链接 通过商品id
     *
     * @param params 商品id和链接可选项
     * @return 链接信息
     */
    @Override
    public UrlDTO generatePromotionUrl(PromotionUrlRequest params) {


        return urlDTOAssembler.toDTO(pddPromotionService.generatePromotionUrl(params));
    }

    /**
     * 转换推广链接 通过商品id
     *
     * @param params 原始链接
     * @return 链接信息
     */
    @Override
    public UrlDTO convertPromotionUrl(ConvertUrlRequest params) {
        return null;
    }
}
