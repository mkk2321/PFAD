package com.example.pd.goods.vo;

import com.example.pd.goods.entity.GoodsEntity;
import com.example.pd.goods.enums.GoodsRegisterResult;
import com.example.pd.interfaces.IResult;

public class GoodsRegisterVo extends GoodsEntity implements IResult<GoodsRegisterResult> {
    private GoodsRegisterResult result;
    private GoodsEntity[] productEntities;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public GoodsEntity[] getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(GoodsEntity[] productEntities) {
        this.productEntities = productEntities;
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
