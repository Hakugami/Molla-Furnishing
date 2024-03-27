package controllers.servlets.user;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.OrderDto;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import services.JWTService;
import services.UserService;
import urls.enums.UrlMapping;
import utils.CookiesUtil;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class GetUserOrdersServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = CookiesUtil.getCookie(req.getCookies(), "Authorization");
        if (cookie != null) {
            try {
                JwtClaims jwtClaims = JWTService.getInstance().validateToken(cookie.getValue(), req.getRemoteAddr());
                List<OrderDto> orders = userService.getOrdersByUserId(Long.valueOf(jwtClaims.getSubject()));
                Gson gson = new Gson();
                String json = gson.toJson(orders);
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(json);
            } catch (InvalidJwtException | MalformedClaimException e) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.sendRedirect(UrlMapping.LOGIN.getContextEmbeddedUrl(req.getContextPath()));
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.sendRedirect(UrlMapping.LOGIN.getContextEmbeddedUrl(req.getContextPath()));
        }
    }
}
