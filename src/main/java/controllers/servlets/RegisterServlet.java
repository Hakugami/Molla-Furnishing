package controllers.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.UserDto;
import services.AuthenticationService;
import urls.enums.UrlMapping;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;


public class RegisterServlet extends HttpServlet {

    private AuthenticationService authService;
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void init() throws ServletException {
        authService = new AuthenticationService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RegisterServlet: processing request");
        // Create an ObjectMapper instance
        UserDto userDto = null;
        try {

            ObjectMapper mapper = new ObjectMapper();

            mapper.setDateFormat(new SimpleDateFormat("yyyy-MMMM-dd")); // Add this line
            // Read the request body and convert it into a UserDto object
            userDto = mapper.readValue(req.getReader(), UserDto.class);

        } catch (Exception e) {
            logger.severe("Error reading user data: " + e.getMessage());
            throw new ServletException(e);
        }

        if (authService.register(userDto)) {
            resp.sendRedirect(UrlMapping.LOGIN.getContextEmbeddedUrl(req.getContextPath()));
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RegisterServlet: processing request");
        req.getRequestDispatcher("/signup.html").forward(req, resp);
    }
}