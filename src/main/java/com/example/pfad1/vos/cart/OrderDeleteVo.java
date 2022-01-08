package com.example.pfad1.vos.cart;

import com.example.pfad1.entities.cart.OrderEntity;
import com.example.pfad1.enums.cart.OrderDeleteResult;
import com.example.pfad1.interfaces.IResult;

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
