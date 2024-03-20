package controllers.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AuthenticationService;
import urls.enums.UrlMapping;

import java.io.IOException;
import java.util.logging.Logger;


public class LoginServlet extends HttpServlet {

    private AuthenticationService authService;
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void init() throws ServletException {
        authService = new AuthenticationService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Gson gson = new Gson();
        String jwt = authService.loginAndReturnToken(email, password, req.getRemoteAddr(), resp);
        if (jwt != null) {
            try {
                System.out.println("LoginServlet: creating JWT");
                resp.setContentType("application/json");
                Cookie jwtCookie = new Cookie("Authorization", jwt);


                resp.setStatus(HttpServletResponse.SC_OK);
                resp.addCookie(jwtCookie);
                JsonObject responseBody = new JsonObject();
                responseBody.addProperty("message", "Logged in successfully");
                String responseBodyJson = gson.toJson(responseBody);
                resp.getWriter().println(responseBodyJson);

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