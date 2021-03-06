package com.colia.yorik.web.interfaces.goods.facade.request;

import com.colia.yorik.support.interfaces.requestBody.CommonCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 推荐商品请求参数
 *
 * @Author konglingyao
 * @Date 2020/7/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "推荐商品请求参数")
public class GoodsDetailRequest extends CommonCommand {


    @ApiModelProperty(value = "商品id", required = true)
    private Long goodsId;

    @ApiModelProperty("推广位id")
    private String pid;

    @ApiModelProperty("佣金优惠券对应推广类型，3：专属 4：招商")
    private Integer planType;

    @ApiModelProperty("搜索id")
    private String searchId;


    @Override
    public void validate() {

    }
}
