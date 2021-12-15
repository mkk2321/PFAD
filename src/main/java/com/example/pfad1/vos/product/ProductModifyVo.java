package com.example.pfad1.vos.product;

import com.example.pfad1.entities.product.ProductEntity;
import com.example.pfad1.enums.product.ProductDeleteResult;
import com.example.pfad1.enums.product.ProductModifyResult;
import com.example.pfad1.interfaces.IResult;

public class ProductModifyVo extends ProductEntity implements IResult<ProductModifyResult> {
    private ProductModifyResult result;

    @Override
    public ProductModifyResult getResult() {
        return result;
    }

    @Override
    public void setResult(ProductModifyResult result) {
        this.result = result;
    }
}
