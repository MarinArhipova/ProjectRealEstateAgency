package com.company.services;

import com.company.dto.UserDto;
import com.company.forms.SignInForm;
import com.company.forms.SignUpForm;
import com.company.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    void signUp(SignUpForm form);
    Optional<String> signIn(SignInForm form);
    boolean isExistByCookie(String cookieValue);
    Optional<User> findByEmail(String email);
    User getUserByCookie(String cookie);
    List<UserDto> findAll();
}
