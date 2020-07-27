package com.colia.yorik.yorikapplication.application.goods.impl;

import com.colia.yorik.yorikapplication.application.goods.PddGoodsService;
import com.colia.yorik.yorikapplication.application.goods.adapter.GoodsMapper;
import com.colia.yorik.yorikapplication.application.goods.valueObject.PddGoodsBasicVO;
import com.colia.yorik.yorikapplication.application.goods.valueObject.PddGoodsDetailVO;
import com.colia.yorik.yorikapplication.application.goods.valueObject.PddGoodsRecommendVO;
import com.colia.yorik.yorikapplication.application.goods.valueObject.PddGoodsSearchVO;
import com.colia.yorik.yorikcommon.infrastructure.exception.BizProcessException;
import com.colia.yorik.yoriksupport.utils.HttpPddClient;
import com.pdd.pop.sdk.common.util.JsonUtil;
import com.pdd.pop.sdk.http.PopClient;
import com.pdd.pop.sdk.http.api.pop.request.PddDdkGoodsBasicInfoGetRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddDdkGoodsDetailRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddDdkGoodsRecommendGetRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddDdkGoodsSearchRequest;
import com.pdd.pop.sdk.http.api.pop.response.PddDdkGoodsBasicInfoGetResponse;
import com.pdd.pop.sdk.http.api.pop.response.PddDdkGoodsDetailResponse;
import com.pdd.pop.sdk.http.api.pop.response.PddDdkGoodsRecommendGetResponse;
import com.pdd.pop.sdk.http.api.pop.response.PddDdkGoodsSearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author konglingyao
 * @Date 2020/7/17
 */
@Slf4j
@Service
public class PddGoodsServiceImpl implements PddGoodsService {

    @Resource
    private GoodsMapper goodsVOMapper;


    /**
     * pdd推荐商品
     *
     * @param request pdd需要的请求参数
     * @return 商品流list
     */
    @Override
    public PddGoodsRecommendVO getRecommendGoods(PddDdkGoodsRecommendGetRequest request) {
        PopClient client = HttpPddClient.getPddClient();
        PddDdkGoodsRecommendGetResponse response;
        try {
            response = client.syncInvoke(request);
        } catch (Exception e) {
            log.error("PDD推荐商品接口异常", e);
            throw new BizProcessException("PDD推荐商品接口异常", e);
        }
        return goodsVOMapper.toPddGoodsRecommendVO(response.getGoodsBasicDetailResponse());
    }

    /**
     * pdd根据商品id获取基本信息
     *
     * @param goodsIdList 商品id列表
     * @return 商品基本信息
     */
    @Override
    public List<PddGoodsBasicVO> getGoodsBasicInfoByID(List<Long> goodsIdList) {
        PopClient client = HttpPddClient.getPddClient();

        PddDdkGoodsBasicInfoGetRequest request = new PddDdkGoodsBasicInfoGetRequest();

        request.setGoodsIdList(goodsIdList);
        PddDdkGoodsBasicInfoGetResponse response;
        try {
            response = client.syncInvoke(request);
        } catch (Exception e) {
            log.error("PDD获取商品基本信息接口异常", e);
            throw new BizProcessException("PDD获取商品基本信息接口异常", e);
        }

        return goodsVOMapper.toPddGoodsBasicList(response.getGoodsBasicDetailResponse().getGoodsList());
    }

    /**
     * 获取pdd商品详情
     *
     * @param request 商品详情请求参数 goods_id
     * @return 商品详情
     */
    @Override
    public List<PddGoodsDetailVO> getGoodsDetailInfo(PddDdkGoodsDetailRequest request) {

        PopClient client = HttpPddClient.getPddClient();
        PddDdkGoodsDetailResponse response;
        try {
            response = client.syncInvoke(request);
        } catch (Exception e) {
            log.error("PDD获取商品详细信息接口异常", e);
            throw new BizProcessException("PDD获取商品详细信息接口异常", e);
        }
        return goodsVOMapper.toPddGoodsDetailList(response.getGoodsDetailResponse().getGoodsDetails());
    }

    /**
     * 搜索商品
     *
     * @param request 请求相关
     * @return 商品列表
     */
    @Override
    public PddGoodsSearchVO searchGoods(PddDdkGoodsSearchRequest request) {
        PopClient client = HttpPddClient.getPddClient();

        List<Integer> activityTags = new ArrayList<>();
        activityTags.add(0);
        request.setActivityTags(activityTags);
        request.setCatId(0L);
        request.setCustomParameters("str");
        List<Long> goodsIdList = new ArrayList<>();
        goodsIdList.add(0L);
        request.setGoodsIdList(goodsIdList);
        request.setIsBrandGoods(false);
        request.setKeyword("str");
        request.setListId("str");
        request.setMerchantType(0);
        List<Integer> merchantTypeList = new ArrayList<>();
        merchantTypeList.add(0);
        request.setMerchantTypeList(merchantTypeList);
        request.setOptId(0L);
        request.setPage(0);
        request.setPageSize(0);
        request.setPid("str");
        List<PddDdkGoodsSearchRequest.RangeListItem> rangeList = new ArrayList<>();

        PddDdkGoodsSearchRequest.RangeListItem item = new PddDdkGoodsSearchRequest.RangeListItem();
        item.setRangeFrom(0L);
        item.setRangeId(0);
        item.setRangeTo(0L);
        rangeList.add(item);
        request.setRangeList(rangeList);
        request.setSortType(0);
        request.setWithCoupon(false);
        PddDdkGoodsSearchResponse response;
        try {
            log.info("searchGoods:请求参数：{}", JsonUtil.transferToJson(request));
            response = client.syncInvoke(request);
            log.info("searchGoods:返回参数：{}", JsonUtil.transferToJson(request));
        } catch (Exception e) {
            log.error("searchGoods接口异常", e);
            throw new BizProcessException("searchGoods接口异常", e);
        }

        return goodsVOMapper.toPddGoodsSearchVO(response.getGoodsSearchResponse());

    }


}
