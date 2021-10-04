package com.example.pfad1.controllers;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.services.ProductService;
import com.example.pfad1.vos.product.ProductRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/product",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String productGet() {
        return "product/product";
    }

    @RequestMapping(value = "/product/productRegister",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String productRegisterGet(@SessionAttribute(value = "userEntity", required = false)UserEntity userEntity) {
//        if(userEntity == null || !userEntity.isAdmin()) {
//            return "redirect:/";
//        }
        return "product/productRegister";
    }

    @RequestMapping(value = "/product/productRegister",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String productRegisterPost(ProductRegisterVo productRegisterVo, @SessionAttribute(value = "userEntity", required = false)UserEntity userEntity) {
        this.productService.register(productRegisterVo, userEntity);
        return "product/productRegister";
    }
}
