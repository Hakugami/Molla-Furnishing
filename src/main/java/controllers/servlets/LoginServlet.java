package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AuthenticationService;
import services.JWTService;
import urls.enums.UrlMapping;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class LoginServlet extends HttpServlet {

    private AuthenticationService authService;
    private JWTService jwtService;
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void init() throws ServletException {
        authService = new AuthenticationService();
        try {
            jwtService = JWTService.getInstance();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("LoginServlet: email: " + email);
        System.out.println("LoginServlet: processing request");
        if (authService.login(email, password)) {
            try {
                System.out.println("LoginServlet: creating JWT");
                Map<String, Object> claims = new HashMap<>();
                // Add any claims you want to the JWT here
                String jwt = jwtService.createJWT(claims, email);
                String encodedJwt = URLEncoder.encode("Bearer " + jwt, StandardCharsets.UTF_8.toString());
                Cookie jwtCookie = new Cookie("Authorization", encodedJwt);

                // Create a new cookie and add it to the response
//                Cookie jwtCookie = new Cookie("Authorization", "Bearer " + jwt);
                resp.addCookie(jwtCookie);

                resp.sendRedirect(UrlMapping.HOME.getContextEmbeddedUrl(req.getContextPath()));

            } catch (Exception e) {
                logger.severe("Error creating JWT: " + e.getMessage());
                throw new ServletException(e);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(UrlMapping.LOGIN.getPageName()).forward(req, resp);
    }
}