package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import urls.enums.UrlMapping;
import utils.CookiesUtil;

import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = CookiesUtil.getCookie(req.getCookies(), "Authorization");
        cookie.setValue("");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        resp.sendRedirect(UrlMapping.HOME.getContextEmbeddedUrl(req.getContextPath()));

    }
}
