package com.example.pfad1.vos.cart;

import com.example.pfad1.entities.cart.CartEntity;
import com.example.pfad1.entities.cart.OrderEntity;
import com.example.pfad1.entities.product.ProductEntity;
import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.cart.OrderByCartResult;
import com.example.pfad1.enums.cart.OrderResult;
import com.example.pfad1.interfaces.IResult;

public class OrderByCartVo extends CartEntity implements IResult<OrderByCartResult>{
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
