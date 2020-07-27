package com.colia.yorik.yorikweb.interfaces.order.controller;

import com.colia.yorik.yorikcommon.interfaces.ajaxresult.AjaxResponse;
import com.colia.yorik.yorikcommon.interfaces.ajaxresult.AjaxResultUtils;
import com.colia.yorik.yorikweb.interfaces.goods.facade.request.GoodsRecommendRequest;
import com.colia.yorik.yorikweb.interfaces.order.facade.OrderQueryFacade;
import com.colia.yorik.yorikweb.interfaces.order.facade.dto.OrderListDTO;
import com.colia.yorik.yorikweb.interfaces.order.facade.request.QueryOrderIncRequest;
import com.colia.yorik.yorikweb.interfaces.order.facade.request.QueryOrderRangeRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author konglingyao
 * @Date 2020/7/26
 */
@Controller
@RequestMapping("/api/order")
@Api(tags = "order-controller", description = "订单管理")
public class OrderController {

    @Resource
    private OrderQueryFacade orderQueryFacade;


    @ResponseBody
    @RequestMapping(value = "/pdd/queryOrderByTime", method = RequestMethod.POST)
    @ApiOperation(value = "用时间段查询推广订单")
    public AjaxResponse<OrderListDTO> getOrderListByTime(@RequestBody QueryOrderRangeRequest params) {
        return AjaxResultUtils.renderSuccess("cl you",
                orderQueryFacade.queryOrderByTimeRange(params));

    }

    @ResponseBody
    @RequestMapping(value = "/pdd/queryIncOrder", method = RequestMethod.POST)
    @ApiOperation(value = "查询增量订单")
    public AjaxResponse<OrderListDTO> getIncOrderList(@RequestBody QueryOrderIncRequest params) {

        return AjaxResultUtils.renderSuccess("cl you",
                orderQueryFacade.queryIncOrderList(params));

    }
}
