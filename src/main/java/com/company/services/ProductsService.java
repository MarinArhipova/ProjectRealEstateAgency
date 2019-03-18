package com.company.services;

import com.company.dto.ProductDto;

import java.util.List;

public interface ProductsService {
//    List<Product> addProductToUserBasket(String cookieValue, Long productId);
//    List<Product> findByCountOfRooms(Integer countOfRooms);
    List<ProductDto> getAll(String str2);
    List<ProductDto> forTable();
//    Product getProductById(Long id);
}
