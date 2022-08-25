package com.example.pd.cart.vo;

import com.example.pd.cart.entity.CartEntity;
import com.example.pd.cart.enums.CartReadResult;
import com.example.pd.interfaces.IResult;

public class CartReadVo extends CartEntity implements IResult<CartReadResult> {
    protected CartReadResult result;
    protected CartReadVo[] cartReadVos;
    protected String goodsName;
    protected int price;
    protected String thumbnail;
    protected int cartCount;

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CartReadVo[] getCartReadVos() {
        return cartReadVos;
    }

    public void setCartReadVos(CartReadVo[] cartReadVos) {
        this.cartReadVos = cartReadVos;
    }

    @Override
    public CartReadResult getResult() {
        return result;
    }

    @Override
    public void setResult(CartReadResult result) {
        this.result = result;
    }
}
