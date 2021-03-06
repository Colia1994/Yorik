package com.colia.yorik.web.interfaces.goods.facade.adapter;

import com.colia.yorik.application.goods.valueObject.PddCatsVO;
import com.colia.yorik.support.interfaces.dto.Assembler;
import com.colia.yorik.web.interfaces.goods.facade.dto.GoodsCatsDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author konglingyao
 * @Date 2020/8/10
 */
@Component
public class GoodsCatsDTOAssembler implements Assembler<GoodsCatsDTO> {
    @Resource
    private GoodsCommonMapper goodsCommonMapper;

    @Override
    public GoodsCatsDTO toDTO(Object objects) {
        if (Objects.isNull(objects)) {
            return null;
        }

        //pdd
        if (objects instanceof PddCatsVO) {
            return goodsCommonMapper.pddToCatsDTO((PddCatsVO) objects);
        }

        return null;
    }
}
