package com.example.pfad1.controllers;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.cart.CartDeleteResult;
import com.example.pfad1.enums.cart.CartReadResult;
import com.example.pfad1.enums.cart.OrderDeleteResult;
import com.example.pfad1.enums.cart.OrderListResult;
import com.example.pfad1.services.CartService;
import com.example.pfad1.vos.cart.*;
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
        if (userEntity == null) {
            cartReadVo.setResult(CartReadResult.NOT_ALLOWED);
            return "cart/cart";
        }
        this.cartService.read(cartReadVo, userEntity);
        model.addAttribute("cartReadVo", cartReadVo);
        return "cart/cart";
    }

    @RequestMapping(value = "/cart",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String cartPost(@SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                           CartUpdateVo cartUpdateVo,
                           Model model) {
        this.cartService.update(cartUpdateVo, userEntity);
        model.addAttribute("cartUpdateVo", cartUpdateVo);
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
        if(userEntity == null) {
            cartDeleteVo.setResult(CartDeleteResult.NOT_ALLOWED);
            return "cart/delete";
        }
        cartDeleteVo.setProductIndex(index);
        this.cartService.delete(cartDeleteVo, userEntity);
        model.addAttribute("cartDeleteVo", cartDeleteVo);
        return "cart/delete";
    }

    @RequestMapping(value = "/cart/delete/all",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String deleteAllGet(@SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                               CartDeleteVo cartDeleteVo) {
        if(userEntity == null) {
            cartDeleteVo.setResult(CartDeleteResult.NOT_ALLOWED);
            return "cart/delete";
        }
        this.cartService.deleteAll(cartDeleteVo, userEntity);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/order",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String orderGet(@SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                           CartReadVo cartReadVo,
                           Model model) {
        if(userEntity == null) {
            cartReadVo.setResult(CartReadResult.NOT_ALLOWED);
            return "cart/order";
        }
        this.cartService.read(cartReadVo, userEntity);
        model.addAttribute("cartReadVo", cartReadVo);
        System.out.println(cartReadVo.getResult());
        return "cart/order";
    }

    @RequestMapping(value = "/order",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String orderPost(@SessionAttribute(name = "userEntity") UserEntity userEntity,
                            OrderByCartVo orderByCartVo,
                            Model model) {
        this.cartService.order(orderByCartVo, userEntity);
        model.addAttribute("orderByCartVo", orderByCartVo);
        return "cart/order";
    }

    @RequestMapping(value = "/orderComplete/{orderCode}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String orderCompleteGet(@SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                                   @PathVariable(name = "orderCode") String orderCode,
                                   OrderCompleteVo orderCompleteVo,
                                   Model model) {
        if(userEntity == null) {
            return "redirect:/login";
        }
        orderCompleteVo.setOrderCode(orderCode);
        this.cartService.orderComplete(orderCompleteVo, userEntity);
        model.addAttribute("orderCompleteVo", orderCompleteVo);
        return "cart/orderComplete";
    }

    @RequestMapping(value = "/order-list",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String orderList(@SessionAttribute(name = "userEntity",required = false) UserEntity userEntity,
                            OrderListVo orderListVo,
                            Model model) {
        if(userEntity == null) {
            orderListVo.setResult(OrderListResult.NOT_ALLOWED);
            return "redirect:/login";
        }
        this.cartService.orderList(orderListVo, userEntity);
        model.addAttribute("orderListVo", orderListVo);
        return "cart/orderList";
    }

    @RequestMapping(value = "/order-list/delete/{productIndex}/{orderCode}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String orderListDeleteGet(@SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                                     @PathVariable(name = "productIndex") int productIndex,
                                     @PathVariable(name = "orderCode") String orderCode,
                                     OrderDeleteVo orderDeleteVo,
                                     Model model) {
        if(userEntity == null) {
            orderDeleteVo.setResult(OrderDeleteResult.NOT_ALLOWED);
            return "cart/orderDelete";
        }
        orderDeleteVo.setProductIndex(productIndex);
        orderDeleteVo.setOrderCode(orderCode);
        this.cartService.orderDelete(orderDeleteVo, userEntity);
        model.addAttribute("orderDeleteVo", orderDeleteVo);
        return "cart/orderDelete";

    }
}
