package com.example.pd.cart.entity;

import com.example.pd.interfaces.IUser;

public class CartEntity implements IUser {
    protected int productIndex;
    protected String userId;
    protected int stock;

    public int getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(int productIndex) {
        this.productIndex = productIndex;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
