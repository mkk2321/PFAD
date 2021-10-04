package com.example.pfad1.vos.product;

import com.example.pfad1.entities.product.ProductEntity;
import com.example.pfad1.enums.product.ProductRegisterResult;
import com.example.pfad1.interfaces.IResult;

public class ProductRegisterVo extends ProductEntity implements IResult<ProductRegisterResult> {
    private ProductRegisterResult result;
    private String file;

    @Override
    public ProductRegisterResult getResult() {
        return result;
    }

    @Override
    public void setResult(ProductRegisterResult result) {
        this.result = result;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
