package controllers.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ValidationUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PasswordValidationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();
        String password = req.getParameter("password");

        Map<String, String> responseMap = new HashMap<>();

        // Validate the password
        String passwordError = ValidationUtil.validatePassword(password);
        responseMap.put("message", Objects.requireNonNullElse(passwordError, "Password is valid"));

        out.write(gson.toJson(responseMap));
    }
}