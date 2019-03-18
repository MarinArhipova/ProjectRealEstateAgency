package com.company.servlets;//package com.company.servlets;
//
//import com.company.dto.ProductDto;
//import com.company.models.Product;
//import com.company.models.User;
//import com.company.services.ProductsService;
//import com.company.services.UsersService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/shop/json")
//public class ShopJsonServlet extends HttpServlet {
//    private ProductsService productService;
//    private UsersService usersService;
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        ServletContext context = config.getServletContext();
//        usersService = (UsersService) context.getAttribute("usersService");
//        productService = (ProductsService) context.getAttribute("productService");
//
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String str2 = request.getParameter("category");
//        Cookie authCookie = authCookie(request.getCookies());
//        String json = null;
//        if (authCookie != null) {
//            User user = usersService.getUserByCookie(authCookie.getValue());
//                List<ProductDto> product = productService.getAll(str2);
//                json = objectMapper.writeValueAsString(product);
////                Product product = productService.getProductById(req.getParameter("category"), Long.parseLong(req.getParameter("id")));
//            response.setContentType("application/json; charset=UTF-8");
//            response.getWriter().write(json);
//        }
//    }
//
////    if (str2.equals("common")) {
////        Common product = productService.getCommonById(type, Long.parseLong(request.getParameter("id")));
////        json = objectMapper.writeValueAsString(product);
////
////    } else if (str2.equals("commerc")) {
////        Commerc product = productService.getCommercById(Long.parseLong(request.getParameter("id")));
////        json = objectMapper.writeValueAsString(product);
////    } else if (str2.equals("cottage")) {
////        Cottage product = productService.getCottageById(Long.parseLong(request.getParameter("id")));
////        json = objectMapper.writeValueAsString(product); //маппит объект в json-файл, rowmapper маппит поля из бд в поля объекта
////    }
//
//    private Cookie authCookie(Cookie[] cookies) {
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
