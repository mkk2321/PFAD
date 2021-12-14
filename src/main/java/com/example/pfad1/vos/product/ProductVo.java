package com.example.pfad1.vos.product;

import com.example.pfad1.entities.product.ProductEntity;
import com.example.pfad1.enums.product.ListResult;
import com.example.pfad1.interfaces.IResult;

public class ProductVo extends ProductEntity implements IResult<ListResult> {
    private ListResult result;
    private ProductEntity[] productEntities;

    public ProductEntity[] getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(ProductEntity[] productEntities) {
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
