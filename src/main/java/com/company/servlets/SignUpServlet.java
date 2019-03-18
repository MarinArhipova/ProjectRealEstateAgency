package com.company.servlets;

import com.company.forms.SignUpForm;
import com.company.services.UsersService;
import lombok.SneakyThrows;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private UsersService usersService;

    @SneakyThrows
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        usersService = (UsersService)context.getAttribute("usersService");
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("front2/signUp.ftl").forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        SignUpForm form = SignUpForm.builder()
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .patronymic(request.getParameter("patronymic"))
                .email(request.getParameter("email"))
                .phoneNumber(request.getParameter("phoneNumber"))
                .hashPassword(request.getParameter("hashPassword"))
                .build();

        usersService.signUp(form);
        response.sendRedirect("/signIn");
    }
}
