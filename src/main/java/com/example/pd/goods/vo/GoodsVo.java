package com.example.pd.goods.vo;

import com.example.pd.goods.entity.GoodsEntity;
import com.example.pd.goods.enums.ListResult;
import com.example.pd.interfaces.IResult;

public class GoodsVo extends GoodsEntity implements IResult<ListResult> {
    private ListResult result;
    private GoodsEntity[] productEntities;

    public GoodsEntity[] getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(GoodsEntity[] productEntities) {
        this.productEntities = productEntities;
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
