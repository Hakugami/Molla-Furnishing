package controllers.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import services.JWTService;
import urls.enums.UrlMapping;
import utils.CookiesUtil;

import java.io.IOException;
import java.net.UnknownHostException;

public class AdminFilter implements Filter {
    private JWTService jwtService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            jwtService = JWTService.getInstance();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Cookie jwtCookie = CookiesUtil.getCookie(httpRequest.getCookies(), "Authorization");

        if (jwtCookie != null) {
            processJwtCookie(jwtCookie, httpRequest, httpResponse, chain);
        } else {
            redirectToLogin(httpRequest, httpResponse);
        }
    }

    private void processJwtCookie(Cookie jwtCookie, HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain chain) throws IOException, ServletException {
        String token = jwtCookie.getValue();
        try {
            JwtClaims claims = jwtService.validateToken(token, httpRequest.getRemoteAddr());
            if (isAdmin(claims)) {
                chain.doFilter(httpRequest, httpResponse);
            } else {
                redirectToLogin(httpRequest, httpResponse);
            }
        } catch (InvalidJwtException | UnknownHostException e) {
            redirectToLogin(httpRequest, httpResponse);
        }
    }

    private boolean isAdmin(JwtClaims claims) {
        return "ADMIN".equals(claims.getClaimValue("role"));
    }

    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(UrlMapping.LOGIN.getContextEmbeddedUrl(request.getContextPath()));
    }

}