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
import services.UserService;
import utils.CookiesUtil;

import java.io.IOException;
import java.util.logging.Logger;

public class LoadProfileServlet extends HttpServlet {
    private UserService userService;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("LoadProfileServlet");

        Cookie cookie = CookiesUtil.getCookie(req.getCookies(), "Authorization");
        try {
            JwtClaims claims = JWTService.getInstance().validateToken(cookie.getValue(), req.getRemoteAddr());

            // Extract the user's ID from the JWT claims
            Long userId = Long.parseLong(claims.getSubject());

            // Retrieve the user from the database
            UserDto userDto = userService.getUserById(userId);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            Gson gson = new Gson();
            String responseBodyJson = gson.toJson(userDto);
            logger.info("LoadProfileServlet: sending response" + responseBodyJson);
            resp.getWriter().write(responseBodyJson);
        } catch (InvalidJwtException | MalformedClaimException e) {
            logger.severe("Error validating JWT: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        }
    }
}