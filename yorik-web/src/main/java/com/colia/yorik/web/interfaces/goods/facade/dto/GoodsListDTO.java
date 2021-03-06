package com.colia.yorik.web.interfaces.goods.facade.dto;

import com.colia.yorik.support.interfaces.dto.PageInfoDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author konglingyao
 * @Date 2020/7/17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "商品列表")
public class GoodsListDTO extends PageInfoDTO<GoodsDetailDTO> {


    @ApiModelProperty("pdd 专用字段 搜索id，建议生成推广链接时候填写，提高收益")
    private String searchId;
}
