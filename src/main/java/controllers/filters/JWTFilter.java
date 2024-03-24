package controllers.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import services.JWTService;
import services.UserService;
import urls.enums.UrlMapping;
import utils.CookiesUtil;

import java.io.IOException;
import java.net.UnknownHostException;

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

        Cookie jwtCookie = CookiesUtil.getCookie(httpRequest.getCookies(), "Authorization");
        httpRequest.setAttribute("user", null);
        if (jwtCookie != null) {
            String token = jwtCookie.getValue();
            try {
                JwtClaims claims = jwtService.validateToken(token, httpRequest.getRemoteAddr());
                System.out.println("Claims--------------: " + Long.valueOf(claims.getSubject()));
                chain.doFilter(request, response);
            } catch (InvalidJwtException | UnknownHostException e) {
//                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                httpResponse.sendRedirect(UrlMapping.LOGIN.getContextEmbeddedUrl(((HttpServletRequest) request).getContextPath()));
            } catch (MalformedClaimException e) {
                httpResponse.sendRedirect(UrlMapping.LOGIN.getContextEmbeddedUrl(((HttpServletRequest) request).getContextPath()));
//                httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Malformed token");
            }
        } else {
            httpResponse.sendRedirect(UrlMapping.LOGIN.getContextEmbeddedUrl(((HttpServletRequest) request).getContextPath()));
//            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No token found");
        }
    }

}