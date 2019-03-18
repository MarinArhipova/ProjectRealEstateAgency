package com.company.repositories;

import com.company.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByEmail(String email);
    User findByCookie(String cookieValue);
//    boolean addProduct(User user, Product product);
}
