package com.example.pfad1.controllers;

import com.example.pfad1.entities.product.ProductEntity;
import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.board.ImageDownloadResult;
import com.example.pfad1.enums.board.ImageUploadResult;
import com.example.pfad1.enums.product.ProductModifyResult;
import com.example.pfad1.enums.product.ProductRegisterResult;
import com.example.pfad1.services.ProductService;
import com.example.pfad1.vos.board.ImageDownloadVo;
import com.example.pfad1.vos.board.ImageUploadVo;
import com.example.pfad1.vos.product.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String productGet(ProductVo productVo,
                             Model model) {
        this.productService.list(productVo);
        model.addAttribute("productVo", productVo);
        return "product/product";
    }

    @RequestMapping(value = "/register",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String registerGet(ProductRegisterVo productRegisterVo,
                              @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                              Model model) {
        if(userEntity == null || !userEntity.isAdmin()) {
            productRegisterVo.setResult(ProductRegisterResult.NOT_ALLOWED);
        }
        model.addAttribute("productRegisterVo", productRegisterVo);
        return "product/register";
    }

    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String registerPost(ProductRegisterVo productRegisterVo,
                               @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                               Model model) {
        this.productService.registerByPost(productRegisterVo, userEntity);
        model.addAttribute("productRegisterVo", productRegisterVo);
        return "redirect:/product";
    }

    @RequestMapping(value = "/read/{index}",
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public String readGet(@PathVariable(name = "index") int index,
                          ProductReadVo productReadVo,
                          Model model) {
        productReadVo.setIndex(index);
        this.productService.read(productReadVo);
        model.addAttribute("productReadVo", productReadVo);
        return "product/read";
    }

    @RequestMapping(value = "/delete/{index}",
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public String deleteGet(@PathVariable(name = "index") int index,
                          @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                          ProductDeleteVo productDeleteVo,
                          Model model) {
        productDeleteVo.setIndex(index);
        this.productService.delete(productDeleteVo, userEntity);
        model.addAttribute("productDeleteVo", productDeleteVo);
        return "product/delete";
    }

    @RequestMapping(value = "/modify/{index}",
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public String modifyGet(@PathVariable(name = "index")int index,
                            @SessionAttribute(name = "userEntity")UserEntity userEntity,
                            ProductModifyVo productModifyVo,
                            Model model) {
        productModifyVo.setIndex(index);
        this.productService.modifyByGet(productModifyVo, userEntity);
        model.addAttribute("productModifyVo", productModifyVo);
        return "product/modify";
    }

    @RequestMapping(value = "/modify/{index}",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String modifyPost(@PathVariable(name = "index")int index,
                            @SessionAttribute(name = "userEntity")UserEntity userEntity,
                            ProductModifyVo productModifyVo,
                            Model model) {
        productModifyVo.setIndex(index);
        this.productService.modifyByPost(productModifyVo, userEntity);
        model.addAttribute("productModifyVo", productModifyVo);
        return "redirect:/product/read/" + productModifyVo.getIndex();
    }

}
