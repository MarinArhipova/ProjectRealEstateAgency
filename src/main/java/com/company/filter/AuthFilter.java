package com.company.filter;

import com.company.services.UsersService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthFilter implements Filter {

    private UsersService usersService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();
        usersService = (UsersService)context.getAttribute("usersService");
    }

      @Override
      public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
          HttpServletRequest request = (HttpServletRequest)servletRequest;
          HttpServletResponse response = (HttpServletResponse)servletResponse;

          Cookie cookies[] = request.getCookies();

          if (cookies != null) {
              for (Cookie cookie : cookies) {
                  if (cookie.getName().equals("auth")) {
                      if (usersService.isExistByCookie(cookie.getValue())) {
                          chain.doFilter(request, response);
                          return;
                      }
                  }
              }
              response.sendRedirect("/signIn");
              return;
          }
          response.sendRedirect("/signIn");
          return;
      }

    @Override
    public void destroy() {

    }

}
