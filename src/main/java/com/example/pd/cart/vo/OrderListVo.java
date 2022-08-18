package com.example.pd.cart.vo;

import com.example.pd.cart.entity.OrderEntity;
import com.example.pd.cart.enums.OrderListResult;
import com.example.pd.interfaces.IResult;

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
