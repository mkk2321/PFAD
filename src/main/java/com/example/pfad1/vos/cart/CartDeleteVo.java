package com.example.pfad1.vos.cart;

import com.example.pfad1.entities.cart.CartEntity;
import com.example.pfad1.enums.cart.CartDeleteResult;
import com.example.pfad1.interfaces.IResult;

public class CartDeleteVo extends CartEntity implements IResult<CartDeleteResult> {
    private CartDeleteResult result;

    @Override
    public CartDeleteResult getResult() {
        return result;
    }

    @Override
    public void setResult(CartDeleteResult result) {
        this.result = result;
    }
}
