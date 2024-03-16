package controllers.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.UserDto;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import services.JWTService;
import services.UserService;
import urls.enums.UrlMapping;
import utils.CookiesUtil;

import java.io.IOException;
import java.net.UnknownHostException;

public class EmptyURLFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI().substring(req.getContextPath().length());

        if (path.equals("/") || path.isEmpty()) {
           res.sendRedirect(req.getContextPath() + UrlMapping.CONTEXT_DEFAULT.getUrl()+"/");
        } else {
            chain.doFilter(request, response);
        }
    }
}
