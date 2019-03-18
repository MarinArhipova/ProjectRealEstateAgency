package com.company.servlets;


import com.company.dto.ProductDto;
import com.company.models.Basket;
import com.company.models.User;
import com.company.repositories.BasketRepository;
import com.company.repositories.UsersRepository;
import com.company.services.ProductsService;
import com.company.services.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {


    private ProductsService productService;
    private UsersService usersService;
    private UsersRepository usersRepository;
    private BasketRepository basketRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
        productService = (ProductsService) context.getAttribute("productService");
        usersRepository = (UsersRepository) context.getAttribute("usersRepository");
        basketRepository = (BasketRepository) context.getAttribute("basketRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String cookieValue = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth")) {
                cookieValue = cookie.getValue();
            }
        }
        User user = usersRepository.findByCookie(cookieValue);

        List<ProductDto> products = basketRepository.findAllProductsByUserID(user);
        request.setAttribute("products", products);
        request.getRequestDispatcher("front2/basket.ftl").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        Cookie[] cookies = request.getCookies();
        String cookieValue = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth")) {
                cookieValue = cookie.getValue();
            }
        }
        User user = usersRepository.findByCookie(cookieValue);
        Basket usersBasket = basketRepository.getBasketByUserId(user.getUserID());

        basketRepository.addProductToBasket(usersBasket.getBasketID(), productId);
    }


}
