package com.example.pfad1.controllers;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.services.CartService;
import com.example.pfad1.vos.cart.CartReadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @RequestMapping(value = "/cart",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String cartGet(@SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                          CartReadVo cartReadVo) {
        this.cartService.read(cartReadVo, userEntity);
        return "product/cart";
    }
}
