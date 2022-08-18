package com.example.pd.cart.vo;

import com.example.pd.cart.entity.OrderEntity;
import com.example.pd.cart.enums.OrderDeleteResult;
import com.example.pd.interfaces.IResult;

public class OrderDeleteVo extends OrderEntity implements IResult<OrderDeleteResult> {
    private OrderDeleteResult result;

    @Override
    public OrderDeleteResult getResult() {
        return result;
    }

    @Override
    public void setResult(OrderDeleteResult result) {
        this.result = result;
    }
}
