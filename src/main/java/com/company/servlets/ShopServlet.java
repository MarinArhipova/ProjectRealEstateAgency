package com.company.servlets;

import com.company.dto.ProductDto;
import com.company.models.User;
import com.company.repositories.ProductsRepository;
import com.company.services.ProductsService;
import com.company.services.UsersService;

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

//
//@WebServlet("/shop")
//public class ShopServlet extends HttpServlet {
//
//    private ProductsService productService;
//    private UsersService usersService;
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        ServletContext context = config.getServletContext();
//        usersService = (UsersService)context.getAttribute("usersService");
//        productService = (ProductsService)context.getAttribute("productService");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<ProductDto> allProducts = productService.getAll();
//        Cookie authCookie = authCookie(request.getCookies());
//        if(authCookie != null){
//            Basket basket = usersService.getUserByCookie(authCookie.getValue()).getBasket();
//            request.setAttribute("userProducts", basket);
//        }
//        request.setAttribute("products", allProducts);
//        request.getRequestDispatcher("jsp/shop.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Cookie authCookie = authCookie(req.getCookies());
//        if(authCookie != null){
//            User user = usersService.getUserByCookie(authCookie.getValue());
//            Product product = productService.getProductById(Long.parseLong(req.getParameter("id")));
//            usersService.addProduct(user, product);
//            String json = objectMapper.writeValueAsString(product);
//            resp.setContentType("application/json; charset=UTF-8");
//            resp.getWriter().write(json);
//        }
//    }
//
//    private Cookie authCookie(Cookie[] cookies){
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("auth")) {
//                    if (usersService.isExistByCookie(cookie.getValue())) {
//                        return cookie;
//                    }
//                }
//            }
//        }
//        return null;
//    }
//}

@WebServlet(name = "ShopServlet", value = "/shop")
public class ShopServlet extends HttpServlet {
    private ProductsService productService;
    private ProductsRepository productsRepository;
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        usersService = (UsersService) context.getAttribute("usersService");
        productService = (ProductsService) context.getAttribute("productService");
        productsRepository = (ProductsRepository) context.getAttribute("productsRepository");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str2 = request.getParameter("category");

        Cookie authCookie = authCookie(request.getCookies());
        if (authCookie != null) {
            User user = usersService.getUserByCookie(authCookie.getValue());
                List<ProductDto> product = productService.getAll(str2);
        request.setAttribute("products", product);
        request.getRequestDispatcher("front2/shop.ftl").forward(request, response);
    }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private Cookie authCookie(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    if (usersService.isExistByCookie(cookie.getValue())) {
                        return cookie;
                    }
                }
            }
        }
        return null;
    }
}