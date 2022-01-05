package com.example.pfad1.entities.cart;

import com.example.pfad1.interfaces.IUser;

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
