package controllers.listeners;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import models.DTOs.UserDto;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import services.JWTService;
import services.UserService;
import utils.CookiesUtil;

import java.net.UnknownHostException;
import java.util.logging.Logger;

public class JWTRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) sre.getServletRequest();

        Cookie cookie = CookiesUtil.getCookie(httpServletRequest.getCookies(), "Authorization");
        httpServletRequest.setAttribute("user", null);
        if (cookie != null) {
            UserService userService = new UserService();
            JWTService jwtService = JWTService.getInstance();
            String token = cookie.getValue();
            try {
                JwtClaims claims = jwtService.validateToken(token, httpServletRequest.getRemoteAddr());

                System.out.println("Claims-----------------------: " + Long.valueOf(claims.getSubject()));
                UserDto user = userService.getUserById(Long.valueOf(claims.getSubject()));
                user.setPassword(null);
                httpServletRequest.setAttribute("user", user);
                System.out.println("User: " + user);
            } catch (InvalidJwtException | UnknownHostException | MalformedClaimException e) {
                System.out.println("Error in JWTRequestListener: " + e.getMessage());
                Logger.getGlobal().severe("Error in JWTRequestListener: " + e.getMessage());
            }
        }
    }
}
