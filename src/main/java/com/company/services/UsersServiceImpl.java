package com.company.services;

import com.company.dto.UserDto;
import com.company.forms.SignInForm;
import com.company.forms.SignUpForm;
import com.company.models.Auth;
import com.company.models.Basket;
import com.company.models.User;
import com.company.repositories.AuthRepository;
import com.company.repositories.BasketRepository;
import com.company.repositories.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UsersServiceImpl implements UsersService {

    private PasswordEncoder encoder;

    private UsersRepository usersRepository;
    private AuthRepository authRepository;
    private BasketRepository basketRepository;

    public UsersServiceImpl(UsersRepository usersRepository, AuthRepository authRepository, BasketRepository basketRepository) {
        this.usersRepository = usersRepository;
        this.authRepository = authRepository;
        this.basketRepository = basketRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public void signUp(SignUpForm form) {
        User user = User.builder()
        .firstName(form.getFirstName())
        .lastName(form.getLastName())
                .patronymic(form.getPatronymic())
                .phoneNumber(form.getPhoneNumber())
        .email(form.getEmail())
        .hashPassword(encoder.encode(form.getHashPassword()))
                .build();

        usersRepository.save(user);
        Basket basket = Basket.builder()
                .userID(user.getUserID())
                .basketID(user.getUserID())
                .build();
        basketRepository.save(basket);
    }

    @Override
    public Optional<String> signIn(SignInForm form) {
        Optional<User> userCandidate = usersRepository.findByEmail(form.getEmail());

        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            if (user !=null&&encoder.matches(form.getHashPassword(), user.getHashPassword())) {
                String cookieValue = UUID.randomUUID().toString();

                Auth auth = Auth.builder()
                        .user(user)
                        .cookieValue(cookieValue)
                        .build();

                authRepository.save(auth);
                return Optional.of(cookieValue);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean isExistByCookie(String cookieValue) {
        if (authRepository.findByCookieValue(cookieValue) != null) {
            return true;
        }
        return false;
    }


    @Override
    public Optional<User> findByEmail(String email){
        return usersRepository.findByEmail(email);
    }



    @Override
    public List<UserDto> findAll() {
        return UserDto.from(usersRepository.findAll());
    }

    @Override
    public User getUserByCookie(String cookie) {
        return usersRepository.findByCookie(cookie);
    }





}
