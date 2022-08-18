package com.example.pd.cart.vo;

import com.example.pd.cart.entity.CartEntity;
import com.example.pd.cart.enums.CartAddResult;
import com.example.pd.interfaces.IResult;

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
