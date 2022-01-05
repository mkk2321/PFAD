package com.example.pfad1.entities.cart;

import com.example.pfad1.interfaces.IUser;

import java.util.Date;

public class OrderEntity implements IUser {
    protected int index;
    protected String orderCode;
    protected String userId;
    protected Date createdAt;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
