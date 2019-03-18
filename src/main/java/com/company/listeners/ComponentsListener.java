package com.company.listeners;


import com.company.repositories.*;
import com.company.services.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ComponentsListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("555555554ft");
        dataSource.setUrl("jdbc:postgresql://localhost:5433/for_project_28_12");
        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        ProductsRepository productsRepository = new ProductsRepositoryImpl(dataSource);
        AuthRepository authRepository = new AuthRepositoryImpl(dataSource);
        BasketRepository basketRepository = new BasketRepositoryImpl(dataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository, authRepository, basketRepository);
        ProductsService productService = new ProductsServiceImpl(productsRepository);
        BasketService basketService =(BasketService) new BasketServiceImpl(basketRepository);


        sce.getServletContext().setAttribute("usersService", usersService);
        sce.getServletContext().setAttribute("productService", productService);
        sce.getServletContext().setAttribute("basketService", basketService);


//        sce.getServletContext().setAttribute("usersRepository", usersRepository);
//        sce.getServletContext().setAttribute("basketRepository", basketRepository);
//        sce.getServletContext().setAttribute("productsRepository", productsRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}




