package controllers.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import models.DTOs.UserDto;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import services.JWTService;
import services.UserService;
import utils.CookiesUtil;

import java.io.IOException;
import java.net.UnknownHostException;

public class HeaderFilter implements Filter {
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
        System.out.println("<--HeaderFilter: processing request");
        System.out.println("path: " + ((HttpServletRequest) request).getRequestURI());

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        Cookie jwtCookie = CookiesUtil.getCookie(httpRequest.getCookies(), "Authorization");
        if (jwtCookie != null) {
            String token = jwtCookie.getValue();
            try {
                JwtClaims claims = jwtService.validateToken(token, httpRequest.getRemoteAddr());

                System.out.println("User found.");

                String name = claims.getClaimValue("name", String.class);

                httpRequest.setAttribute("name", name);
            } catch (InvalidJwtException | UnknownHostException | MalformedClaimException e) {
//                e.printStackTrace();
                System.out.println("No user found.");
            }
        } else {
            System.out.println("No user found.");
        }

        System.out.println("--/HeaderFilter>");
        chain.doFilter(request, response);
    }

}