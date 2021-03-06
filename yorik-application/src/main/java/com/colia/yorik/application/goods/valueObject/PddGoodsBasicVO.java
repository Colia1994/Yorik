package com.colia.yorik.application.goods.valueObject;

import com.colia.yorik.support.domain.vo.ValueObject;
import lombok.Data;

/**
 * @Author konglingyao
 * @Date 2020/7/24
 */
@Data
public class PddGoodsBasicVO implements ValueObject<PddGoodsBasicVO> {

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品标题
     */
    private String goodsName;

    /**
     * 商品缩略图
     */
    private String goodsPic;

    /**
     * 最小成团价格，单位分
     */
    private Long minGroupPrice;

    /**
     * 最小单买价格，单位分
     */
    private Long minNormalPrice;


    @Override
    public boolean sameValueAs(PddGoodsBasicVO other) {
        return false;
    }

}
