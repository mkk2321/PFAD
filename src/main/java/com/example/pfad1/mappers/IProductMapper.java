package com.example.pfad1.mappers;

import com.example.pfad1.entities.board.ImageEntity;
import com.example.pfad1.entities.cart.CartEntity;
import com.example.pfad1.entities.product.ProductEntity;
import com.example.pfad1.vos.board.ImageDownloadVo;
import com.example.pfad1.vos.board.ImageUploadVo;
import com.example.pfad1.vos.product.ProductRegisterVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IProductMapper {
    int deleteProduct(ProductEntity productEntity);

    ProductEntity[] selectProducts(ProductEntity productEntity);

    ProductEntity selectProduct(ProductEntity productEntity);

    int insertProduct(ProductRegisterVo productRegisterVo);

    int updateProduct(ProductEntity productEntity);

    int insertCart(CartEntity cartEntity);

    int selectCountCart(CartEntity cartEntity);

    int updateCart(CartEntity cartEntity);
}
