package com.example.pfad1.vos.cart;

import com.example.pfad1.entities.cart.OrderEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

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
