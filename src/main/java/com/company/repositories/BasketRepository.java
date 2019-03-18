package com.company.repositories;

import com.company.dto.ProductDto;
import com.company.models.Basket;
import com.company.models.User;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends CrudRepository<Basket> {
    void save(Basket model);
    Optional<Basket> findOne(Long id);
    List<ProductDto> findAllProductsByUserID(User user);

    void addProductToBasket(long productId, long basketId);
    Basket getBasketByUserId(long userId);
    void deleteProductsByUserID(Long id);
    List<Basket> findAll();
}
