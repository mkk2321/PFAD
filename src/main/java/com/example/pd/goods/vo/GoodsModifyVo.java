package com.example.pd.goods.vo;

import com.example.pd.goods.entity.GoodsEntity;
import com.example.pd.goods.enums.GoodsModifyResult;
import com.example.pd.interfaces.IResult;

public class GoodsModifyVo extends GoodsEntity implements IResult<GoodsModifyResult> {
    private GoodsModifyResult result;

    @Override
    public GoodsModifyResult getResult() {
        return result;
    }

    @Override
    public void setResult(GoodsModifyResult result) {
        this.result = result;
    }
}
