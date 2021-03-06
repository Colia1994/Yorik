package com.colia.yorik.application.promotion.adapter;

import com.colia.yorik.application.promotion.valueObject.PddPromotionVO;
import com.colia.yorik.application.promotion.valueObject.PddUrlVO;
import com.pdd.pop.sdk.http.api.pop.response.PddDdkGoodsPidGenerateResponse;
import com.pdd.pop.sdk.http.api.pop.response.PddDdkGoodsPromotionUrlGenerateResponse;
import com.pdd.pop.sdk.http.api.pop.response.PddDdkGoodsZsUnitUrlGenResponse;
import org.mapstruct.Mapper;

/**
 * @Author konglingyao
 * @Date 2020/7/26
 */
@Mapper(componentModel = "spring")
public interface PromotionMapper {

    PddPromotionVO toPopVO(PddDdkGoodsPidGenerateResponse.PIdGenerateResponsePIdListItem item);

    PddUrlVO toUrlVO(PddDdkGoodsPromotionUrlGenerateResponse.GoodsPromotionUrlGenerateResponseGoodsPromotionUrlListItem item);

    PddUrlVO toUrlVO(PddDdkGoodsZsUnitUrlGenResponse.GoodsZsUnitGenerateResponse item);
}
