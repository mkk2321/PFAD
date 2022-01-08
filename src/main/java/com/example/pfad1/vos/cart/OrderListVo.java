package com.example.pfad1.vos.cart;

import com.example.pfad1.entities.cart.OrderEntity;
import com.example.pfad1.enums.cart.OrderListResult;
import com.example.pfad1.interfaces.IResult;

import java.text.SimpleDateFormat;

public class OrderListVo extends OrderEntity implements IResult<OrderListResult> {
    private OrderListResult result;
    private OrderEntity[] orderEntities;

    public OrderEntity[] getOrderEntities() {
        return orderEntities;
    }

    public void setOrderEntities(OrderEntity[] orderEntities) {
        this.orderEntities = orderEntities;
    }

    @Override
    public OrderListResult getResult() {
        return result;
    }

    @Override
    public void setResult(OrderListResult result) {
        this.result = result;
    }

    public String formatCreatedAt() {
        return new SimpleDateFormat("yyyy.MM.dd").format(this.createdAt);
    }
}
