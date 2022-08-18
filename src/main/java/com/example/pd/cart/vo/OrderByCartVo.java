package com.example.pd.cart.vo;

import com.example.pd.cart.entity.OrderEntity;
import com.example.pd.cart.enums.OrderByCartResult;
import com.example.pd.interfaces.IResult;

public class OrderByCartVo extends OrderEntity implements IResult<OrderByCartResult> {
    private OrderByCartResult result;

    @Override
    public OrderByCartResult getResult() {
        return result;
    }

    @Override
    public void setResult(OrderByCartResult result) {
        this.result = result;
    }
}
