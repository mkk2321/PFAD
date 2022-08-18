package com.example.pd.cart.vo;

import com.example.pd.cart.entity.CartEntity;
import com.example.pd.cart.enums.CartDeleteResult;
import com.example.pd.interfaces.IResult;

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
