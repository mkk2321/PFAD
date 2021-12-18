package com.example.pfad1.vos.cart;

import com.example.pfad1.entities.cart.CartEntity;
import com.example.pfad1.enums.cart.CartReadResult;
import com.example.pfad1.interfaces.IResult;

public class CartReadVo extends CartEntity implements IResult<CartReadResult> {
    private CartReadResult result;

    @Override
    public CartReadResult getResult() {
        return result;
    }

    @Override
    public void setResult(CartReadResult result) {
        this.result = result;
    }
}
