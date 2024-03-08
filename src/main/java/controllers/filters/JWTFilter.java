package controllers.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.UserDto;
import services.JWTService;
import services.UserService;
import utils.CookiesUtil;

import java.io.IOException;

public class JWTFilter implements Filter {
    private JWTService jwtService;
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            jwtService = JWTService.getInstance();
            userService = new UserService();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Cookie cookie = CookiesUtil.getCookie(httpRequest.getCookies(), "Authorization");
        String jwt = cookie != null ? cookie.getValue().startsWith("Bearer ") ? cookie.getValue().substring(7) : null : null;

        if (jwt != null) {
            String email =  httpRequest.getParameter("email");
            UserDto user = userService.getUserByEmail(email);
            Long userId = user.getId();
            try {
                if (jwtService.validateJWT(jwt, String.valueOf(userId))) {
                    httpRequest.setAttribute("user", user);
                    chain.doFilter(request, response);
                } else {
                    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } catch (Exception e) {
                throw new ServletException(e);
            }
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}