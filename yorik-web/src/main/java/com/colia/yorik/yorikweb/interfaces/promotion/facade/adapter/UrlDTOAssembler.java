package com.colia.yorik.yorikweb.interfaces.promotion.facade.adapter;

import com.colia.yorik.yorikapplication.application.goods.valueObject.PddGoodsRecommendVO;
import com.colia.yorik.yorikapplication.application.goods.valueObject.PddGoodsSearchVO;
import com.colia.yorik.yorikapplication.application.promotion.valueObject.PddUrlVO;
import com.colia.yorik.yorikcommon.interfaces.dto.Assembler;
import com.colia.yorik.yorikweb.interfaces.promotion.facade.dto.UrlDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author konglingyao
 * @Date 2020/7/31
 */
@Component
public class UrlDTOAssembler implements Assembler<UrlDTO> {


    @Resource
    private UrlDTOMapper urlDTOMapper;

    @Override
    public UrlDTO toDTO(Object objects) {
        if (Objects.isNull(objects)) {
            return null;
        }

        //pdd
        if (objects instanceof PddUrlVO) {
            return urlDTOMapper.pddVOToDto((PddUrlVO) objects);
        }

        return null;
    }
}