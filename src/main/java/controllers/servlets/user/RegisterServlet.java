package controllers.servlets.user;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final Logger logger = Logger.getLogger(getClass().getName());
    private AuthenticationService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthenticationService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RegisterServlet: processing request");

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MMMM-dd"));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JsonNode rootNode = null;
        UserDto userDto = null;
        String password = null;
        try {
            rootNode = mapper.readTree(req.getReader());
            userDto = mapper.treeToValue(rootNode, UserDto.class);
            password = rootNode.get("password").asText();
        } catch (Exception e) {
            logger.severe("Error reading user data: " + e.getMessage());
            throw new ServletException(e);
        }

        if (authService.register(userDto, password)) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.sendRedirect(UrlMapping.LOGIN.getContextEmbeddedUrl(req.getContextPath()));
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RegisterServlet: processing request");
        req.getRequestDispatcher(UrlMapping.REGISTER.getPageName()).forward(req, resp);
    }
}