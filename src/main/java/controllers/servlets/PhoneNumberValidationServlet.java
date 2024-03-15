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

public class PhoneNumberValidationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Phone Number Validation Servlet");
        resp.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();
        String phoneNumber = req.getParameter("phoneNumber");

        Map<String, String> responseMap = new HashMap<>();

        // Validate the phone number
        String phoneNumberError = ValidationUtil.validatePhoneNumber(phoneNumber);
        responseMap.put("message", Objects.requireNonNullElse(phoneNumberError, "Phone number is valid"));

        out.write(gson.toJson(responseMap));
    }
}