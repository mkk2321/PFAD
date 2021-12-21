package com.example.pfad1.controllers;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.services.CartService;
import com.example.pfad1.vos.cart.CartDeleteVo;
import com.example.pfad1.vos.cart.CartReadVo;
import com.example.pfad1.vos.cart.CartUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
                          CartReadVo cartReadVo,
                          Model model) {
        this.cartService.read(cartReadVo, userEntity);
        model.addAttribute("cartReadVo", cartReadVo);
        return "cart/cart";
    }

    @RequestMapping(
                    value = {"/cart/delete/{index}", "/cart/delete"},
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String deleteGet(@PathVariable(name = "index") int index,
                            @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                            CartDeleteVo cartDeleteVo,
                            Model model) {
        cartDeleteVo.setProductIndex(index);
        this.cartService.delete(cartDeleteVo, userEntity);
        model.addAttribute("cartDeleteVo", cartDeleteVo);
        return "cart/delete";
    }

    @RequestMapping(value = "/cart/update/{index}/{stock}",
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public String updateGet(@PathVariable(name = "index")int index,
                            @PathVariable(name = "stock")int stock,
                            @SessionAttribute(name = "userEntity")UserEntity userEntity,
                            CartUpdateVo cartUpdateVo) {
        cartUpdateVo.setProductIndex(index);
        cartUpdateVo.setStock(stock);
        this.cartService.update(cartUpdateVo, userEntity);
        System.out.println(cartUpdateVo.getStock());
        return "redirect:/cart";
    }
}
