package com.example.pd.goods.vo;

import com.example.pd.goods.entity.GoodsEntity;
import com.example.pd.goods.enums.ListResult;
import com.example.pd.interfaces.IResult;

public class GoodsVo extends GoodsEntity implements IResult<ListResult> {
    private ListResult result;
    private GoodsEntity[] goodsEntities;

    public GoodsEntity[] getGoodsEntities() {
        return goodsEntities;
    }

    public void setGoodsEntities(GoodsEntity[] goodsEntities) {
        this.goodsEntities = goodsEntities;
    }

    @Override
    public ListResult getResult() {
        return result;
    }

    @Override
    public void setResult(ListResult result) {
        this.result = result;
    }
}
