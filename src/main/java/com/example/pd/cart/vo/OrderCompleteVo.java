package com.example.pd.cart.vo;

import java.text.SimpleDateFormat;

import com.example.pd.cart.entity.OrderEntity;

public class OrderCompleteVo extends OrderEntity {
    private OrderEntity[] orderEntities;

    public OrderEntity[] getOrderEntities() {
        return orderEntities;
    }

    public void setOrderEntities(OrderEntity[] orderEntities) {
        this.orderEntities = orderEntities;
    }

    public String formatCreatedAt(){
        return new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm").format(this.createdAt);
    }
}
