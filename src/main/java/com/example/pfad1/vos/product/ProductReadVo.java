package com.example.pfad1.vos.product;

import com.example.pfad1.entities.product.ProductEntity;
import com.example.pfad1.enums.product.ProductReadResult;
import com.example.pfad1.interfaces.IResult;

public class ProductReadVo extends ProductEntity implements IResult<ProductReadResult> {
    private ProductReadResult result;

    @Override
    public ProductReadResult getResult() {
        return result;
    }

    @Override
    public void setResult(ProductReadResult result) {
        this.result = result;
    }
}
