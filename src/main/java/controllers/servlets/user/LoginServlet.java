package controllers.servlets.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.entity.User;
import models.enums.UserRole;
import services.AuthenticationService;
import urls.enums.UrlMapping;

import java.io.IOException;
import java.util.logging.Logger;


public class LoginServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(getClass().getName());
    private AuthenticationService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthenticationService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = authenticateUser(req);
            if (user != null) {
                processUserLogin(user, req, resp);
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (Exception e) {
            logger.severe("Error processing login: " + e.getMessage());
            throw new ServletException(e);
        }
    }

    private User authenticateUser(HttpServletRequest req) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        return authService.login(email, password);
    }

    private void processUserLogin(User user, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (isAdmin(user)) {
            sendAdminResponse(resp);
        } else {
            String jwt = authService.returnToken(user, req.getRemoteAddr());
            if (jwt != null) {
                sendUserResponse(jwt, resp);
            }
        }
    }

    private boolean isAdmin(User user) {
        return UserRole.ADMIN.equals(user.getRole());
    }

    private void sendAdminResponse(HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        JsonObject responseBody = new JsonObject();
        responseBody.addProperty("message", "admin");
        String responseBodyJson = new Gson().toJson(responseBody);
        resp.getWriter().println(responseBodyJson);
    }

    private void sendUserResponse(String jwt, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        Cookie jwtCookie = new Cookie("Authorization", jwt);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.addCookie(jwtCookie);
        JsonObject responseBody = new JsonObject();
        responseBody.addProperty("message", "Logged in successfully");
        String responseBodyJson = new Gson().toJson(responseBody);
        resp.getWriter().println(responseBodyJson);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(UrlMapping.LOGIN.getPageName()).forward(req, resp);
    }
}