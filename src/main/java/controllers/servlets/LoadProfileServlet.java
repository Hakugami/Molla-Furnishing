package controllers.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.UserDto;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import services.JWTService;
import utils.CookiesUtil;

import java.io.IOException;

public class LoadProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Cookie cookie = CookiesUtil.getCookie(req.getCookies(),"Authorization");
        try {
            JwtClaims claims = JWTService.getInstance().validateToken(cookie.getValue(), req.getRemoteAddr());
            UserDto userDto = claims.getClaimValue("user", UserDto.class);
            Gson gson = new Gson();
            String responseBodyJson = gson.toJson(userDto);
            resp.getWriter().println(responseBodyJson);
        } catch (InvalidJwtException | MalformedClaimException e) {
            throw new RuntimeException(e);
        }


    }
}
