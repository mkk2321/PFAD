package com.example.pd.goods.vo;

import com.example.pd.goods.entity.GoodsEntity;
import com.example.pd.goods.enums.GoodsRegisterResult;
import com.example.pd.interfaces.IResult;

public class GoodsRegisterVo extends GoodsEntity implements IResult<GoodsRegisterResult> {
    private GoodsRegisterResult result;
    private GoodsEntity[] goodsEntities;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public GoodsEntity[] getGoodsEntities() {
        return goodsEntities;
    }

    public void setGoodsEntities(GoodsEntity[] goodsEntities) {
        this.goodsEntities = goodsEntities;
    }

    @Override
    public GoodsRegisterResult getResult() {
        return result;
    }

    @Override
    public void setResult(GoodsRegisterResult result) {
        this.result = result;
    }

}
