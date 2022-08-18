package com.example.pd.goods.vo;

import com.example.pd.goods.entity.GoodsEntity;
import com.example.pd.goods.enums.GoodsReadResult;
import com.example.pd.interfaces.IResult;

public class GoodsReadVo extends GoodsEntity implements IResult<GoodsReadResult> {
    private GoodsReadResult result;

    @Override
    public GoodsReadResult getResult() {
        return result;
    }

    @Override
    public void setResult(GoodsReadResult result) {
        this.result = result;
    }
}
