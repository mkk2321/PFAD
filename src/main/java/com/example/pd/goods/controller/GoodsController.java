package com.example.pd.goods.controller;

import com.example.pd.cart.enums.CartAddResult;
import com.example.pd.cart.vo.CartAddVo;
import com.example.pd.goods.enums.GoodsRegisterResult;
import com.example.pd.goods.service.GoodsService;
import com.example.pd.goods.vo.GoodsDeleteVo;
import com.example.pd.goods.vo.GoodsModifyVo;
import com.example.pd.goods.vo.GoodsReadVo;
import com.example.pd.goods.vo.GoodsRegisterVo;
import com.example.pd.goods.vo.GoodsVo;
import com.example.pd.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {
    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String goodsGet(GoodsVo goodsVo,
                             Model model) {
        this.goodsService.list(goodsVo);
        model.addAttribute("productVo", goodsVo);
        return "goods/goods";
    }

    @RequestMapping(value = "/register",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String registerGet(GoodsRegisterVo goodsRegisterVo,
                              @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                              Model model) {
        if(userEntity == null || !userEntity.isAdmin()) {
            goodsRegisterVo.setResult(GoodsRegisterResult.NOT_ALLOWED);
        }
        model.addAttribute("productRegisterVo", goodsRegisterVo);
        return "goods/register";
    }

    @RequestMapping(value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String registerPost(GoodsRegisterVo goodsRegisterVo,
                               @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                               Model model) {
        this.goodsService.registerByPost(goodsRegisterVo, userEntity);
        model.addAttribute("productRegisterVo", goodsRegisterVo);
        return "redirect:/goods";
    }

    @RequestMapping(value = "/read/{index}",
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public String readGet(@PathVariable(name = "index") int index,
                          GoodsReadVo goodsReadVo,
                          Model model) {
        goodsReadVo.setIndex(index);
        this.goodsService.read(goodsReadVo);
        model.addAttribute("productReadVo", goodsReadVo);
        System.out.println(goodsReadVo.getResult());
        return "goods/read";
    }

    @RequestMapping(value = "/read/{index}",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String readPOST(@PathVariable(name = "index") int index,
                          @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                          CartAddVo cartAddVo,
                          Model model) {
        cartAddVo.setProductIndex(index);
        if(userEntity == null) {
            cartAddVo.setResult(CartAddResult.NOT_ALLOWED);
            return "goods/read";
        }
        this.goodsService.addCart(cartAddVo, userEntity);
        model.addAttribute("cartAddVo", cartAddVo);
        return "goods/read";

    }

    @RequestMapping(value = "/delete/{index}",
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public String deleteGet(@PathVariable(name = "index") int index,
                          @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity,
                          GoodsDeleteVo goodsDeleteVo,
                          Model model) {
        goodsDeleteVo.setIndex(index);
        this.goodsService.delete(goodsDeleteVo, userEntity);
        model.addAttribute("productDeleteVo", goodsDeleteVo);
        return "goods/delete";
    }

    @RequestMapping(value = "/modify/{index}",
    method = RequestMethod.GET,
    produces = MediaType.TEXT_HTML_VALUE)
    public String modifyGet(@PathVariable(name = "index")int index,
                            @SessionAttribute(name = "userEntity", required = false)UserEntity userEntity,
                            GoodsModifyVo goodsModifyVo,
                            Model model) {
        goodsModifyVo.setIndex(index);
        this.goodsService.modifyByGet(goodsModifyVo, userEntity);
        model.addAttribute("productModifyVo", goodsModifyVo);
        return "goods/modify";
    }

    @RequestMapping(value = "/modify/{index}",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String modifyPost(@PathVariable(name = "index")int index,
                            @SessionAttribute(name = "userEntity")UserEntity userEntity,
                            GoodsModifyVo goodsModifyVo,
                            Model model) {
        goodsModifyVo.setIndex(index);
        this.goodsService.modifyByPost(goodsModifyVo, userEntity);
        model.addAttribute("productModifyVo", goodsModifyVo);
        System.out.println(goodsModifyVo.getResult());
        return "redirect:/goods/read/" + goodsModifyVo.getIndex();
    }
}
