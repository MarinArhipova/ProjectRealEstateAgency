package com.company.services;

import com.company.repositories.BasketRepository;

public class BasketServiceImpl implements BasketService{

    private BasketRepository basketRepository;

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public void addProductInBasket(Long productId, Long basketId) {
        basketRepository.addProductToBasket(productId, basketId);
    }
}
