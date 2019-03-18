package com.company.repositories;

import com.company.dto.ProductDto;

import java.util.List;

public interface ProductsRepository extends CrudRepository<ProductDto> {
    List<ProductDto> findAllProducts(String category);
//    List<Product> findByUserId(Long id);
//    List<ProductDto> findByCountOfRooms(Integer countOfRooms);
    ProductDto findProductById(Long id);
}
