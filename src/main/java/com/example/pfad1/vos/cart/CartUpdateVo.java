package com.example.pfad1.vos.cart;

import com.example.pfad1.entities.cart.CartEntity;
import com.example.pfad1.enums.cart.CartUpdateResult;
import com.example.pfad1.interfaces.IResult;

public class CartUpdateVo extends CartEntity implements IResult<CartUpdateResult> {
    private CartUpdateResult result;
    private String userId;
    private int[] stocks;
    private int[] productsIndex;

    public int[] getProductsIndex() {
        return productsIndex;
    }

    public void setProductsIndex(int[] productsIndex) {
        this.productsIndex = productsIndex;
    }

    public int[] getStocks() {
        return stocks;
    }

    public void setStocks(int[] stocks) {
        this.stocks = stocks;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public CartUpdateResult getResult() {
        return result;
    }

    @Override
    public void setResult(CartUpdateResult result) {
        this.result = result;
    }
}
