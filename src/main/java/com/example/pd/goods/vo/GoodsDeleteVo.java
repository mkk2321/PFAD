package com.example.pd.goods.vo;

import com.example.pd.goods.entity.GoodsEntity;
import com.example.pd.goods.enums.GoodsDeleteResult;
import com.example.pd.interfaces.IResult;

public class GoodsDeleteVo extends GoodsEntity implements IResult<GoodsDeleteResult> {
    private GoodsDeleteResult result;

    @Override
    public GoodsDeleteResult getResult() {
        return result;
    }

    @Override
    public void setResult(GoodsDeleteResult result) {
        this.result = result;
    }
}
