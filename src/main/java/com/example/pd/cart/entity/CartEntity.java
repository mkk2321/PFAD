package com.example.pd.cart.entity;

import com.example.pd.interfaces.IUser;

public class CartEntity implements IUser {
    protected int goodsIndex;
    protected String userId;
    protected int stock;

    public int getGoodsIndex() {
        return goodsIndex;
    }

    public void setGoodsIndex(int goodsIndex) {
        this.goodsIndex = goodsIndex;
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
