package com.example.pfad1.vos.product;

import com.example.pfad1.entities.product.ProductEntity;
import com.example.pfad1.enums.product.ProductDeleteResult;
import com.example.pfad1.interfaces.IResult;

public class ProductDeleteVo extends ProductEntity implements IResult<ProductDeleteResult> {
    private ProductDeleteResult result;

    @Override
    public ProductDeleteResult getResult() {
        return result;
    }

    @Override
    public void setResult(ProductDeleteResult result) {
        this.result = result;
    }
}
