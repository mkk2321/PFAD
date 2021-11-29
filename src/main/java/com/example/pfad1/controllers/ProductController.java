package com.example.pfad1.controllers;

import com.example.pfad1.entities.user.UserEntity;
import com.example.pfad1.enums.ImageDownloadResult;
import com.example.pfad1.enums.ImageUploadResult;
import com.example.pfad1.services.ProductService;
import com.example.pfad1.utils.CryptoUtil;
import com.example.pfad1.vos.ImageDownloadVo;
import com.example.pfad1.vos.ImageUploadVo;
import com.example.pfad1.vos.product.ProductRegisterVo;
import com.example.pfad1.vos.product.ProductVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public String productGet(ProductVo productVo) {
        this.productService.list(productVo);
        return "product/product";
    }

    @RequestMapping(value = "/register",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
        public String registerGet(ProductRegisterVo productRegisterVo) {

        return "product/register";
    }

    @ResponseBody
    @RequestMapping(value = "/upload-image",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String uploadImagePost(@RequestParam(name = "upload") MultipartFile file,
                                  @SessionAttribute(name = "userEntity", required = false) UserEntity userEntity) {
        ImageUploadVo imageUploadVo = new ImageUploadVo();
        imageUploadVo.setFile(file);
        this.productService.uploadImage(imageUploadVo, userEntity);
        JSONObject responseJson = new JSONObject();
        responseJson.put("result", imageUploadVo.getResult().name().toLowerCase());
        if (imageUploadVo.getResult() == ImageUploadResult.SUCCESS) {
            responseJson.put("url", String.format(
                    "http://127.0.0.1/product/download-image/%d", imageUploadVo.getIndex()
            ));
        } else {
            String message;
            switch (imageUploadVo.getResult()) {
                case MIME_INVALID:
                    message = "허용하지 않은 이미지 형식입니다.";
                    break;
                case NOT_ALLOWED:
                    message = "이미지를 업로드 할 권한이 없습니다.";
                    break;
                default:
                    message = "알 수 없는 이유로 이미지를 업로드하지 못하였습니다.";
            }
            JSONObject errorMessageJson = new JSONObject();
            errorMessageJson.put("message", message);
            responseJson.put("error", errorMessageJson);
        }
        return responseJson.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/download-image/{index}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadImageGet(@PathVariable(name = "index") int index,
                                                   HttpServletResponse response) throws IOException {
        ImageDownloadVo imageDownloadVo = new ImageDownloadVo();
        imageDownloadVo.setIndex(index);
        this.productService.downloadImage(imageDownloadVo);
        if (imageDownloadVo.getResult() == ImageDownloadResult.SUCCESS) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", imageDownloadVo.getMime());
            return new ResponseEntity<>(imageDownloadVo.getFile(), headers, HttpStatus.OK);
        } else {
            response.sendError(404);
            return null;
        } // TODO : Product Register Image Processing
    }

}
