package controllers.servlets.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import services.UserService;
import urls.enums.UrlMapping;
import utils.CookiesUtil;

import java.io.IOException;

public class EditProfileServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(UrlMapping.EDITPROFILE.getPageName()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("EditProfileServlet: doPost");
        Cookie jwtCookie = CookiesUtil.getCookie(req.getCookies(), "Authorization");
        if (jwtCookie != null) {
            System.out.println("EditProfileServlet: jwtCookie: " + jwtCookie.getValue());
            String jwt = jwtCookie.getValue();
            try {
                if (userService.updateUser(req, jwt)) {
                    System.out.println("EditProfileServlet: doPost: user updated    ");
                    resp.sendRedirect(UrlMapping.PROFILE.getContextEmbeddedUrl(req.getContextPath()));
                } else {
                    System.out.println("EditProfileServlet: doPost: user not updated");
                    resp.sendRedirect(UrlMapping.PROFILE.getContextEmbeddedUrl(req.getContextPath()));
                }
            } catch (InvalidJwtException | MalformedClaimException e) {
                System.out.println("EditProfileServlet: doPost: InvalidJwtException | MalformedClaimException: " + e.getMessage());
                resp.sendRedirect(UrlMapping.PROFILE.getContextEmbeddedUrl(req.getContextPath()));
            }
        } else {
            System.out.println("EditProfileServlet: doPost: jwtCookie is null");
            resp.sendRedirect(UrlMapping.PROFILE.getContextEmbeddedUrl(req.getContextPath()));

        }

    }
}
