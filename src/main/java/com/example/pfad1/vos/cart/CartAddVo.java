package com.example.pfad1.vos.cart;

import com.example.pfad1.entities.cart.CartEntity;
import com.example.pfad1.enums.cart.CartAddResult;
import com.example.pfad1.interfaces.IResult;

public class CartAddVo extends CartEntity implements IResult<CartAddResult> {
    private CartAddResult result;

    @Override
    public CartAddResult getResult() {
        return result;
    }

    @Override
    public void setResult(CartAddResult result) {
        this.result = result;
    }
}
