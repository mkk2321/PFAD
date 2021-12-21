package com.example.pfad1.vos.cart;

import com.example.pfad1.entities.cart.CartEntity;
import com.example.pfad1.enums.cart.CartReadResult;
import com.example.pfad1.interfaces.IResult;

public class CartReadVo extends CartEntity implements IResult<CartReadResult> {
    protected CartReadResult result;
    protected CartReadVo[] cartReadVos;
    protected String productName;
    protected int stock;
    protected int price;
    protected String thumbnail;
    protected int cartCount;

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public int getStock() {
        return stock;
    }

    @Override
    public void setStock(int stock) {
        this.stock = stock;
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
