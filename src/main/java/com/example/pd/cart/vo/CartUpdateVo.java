package com.example.pd.cart.vo;

import com.example.pd.cart.entity.CartEntity;
import com.example.pd.cart.enums.CartUpdateResult;
import com.example.pd.interfaces.IResult;

public class CartUpdateVo extends CartEntity implements IResult<CartUpdateResult> {
    private CartUpdateResult result;
    private String userId;
    private int[] stocks;
    private int[] goodsIndexArr;

    public int[] getGoodsIndexArr() {
		return goodsIndexArr;
	}

	public void setGoodsIndexArr(int[] goodsIndexArr) {
		this.goodsIndexArr = goodsIndexArr;
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
