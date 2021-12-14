package com.example.pfad1.vos.product;

import com.example.pfad1.entities.product.ProductEntity;
import com.example.pfad1.enums.product.ProductRegisterResult;
import com.example.pfad1.interfaces.IResult;

public class ProductRegisterVo extends ProductEntity implements IResult<ProductRegisterResult> {
    private ProductRegisterResult result;
    private ProductEntity[] productEntities;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ProductEntity[] getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(ProductEntity[] productEntities) {
        this.productEntities = productEntities;
    }

    @Override
    public ProductRegisterResult getResult() {
        return result;
    }

    @Override
    public void setResult(ProductRegisterResult result) {
        this.result = result;
    }

}
