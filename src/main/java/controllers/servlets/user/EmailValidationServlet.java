package controllers.servlets.user;

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

public class EmailValidationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("EmailValidationServlet");
        resp.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");

        Map<String, String> responseMap = new HashMap<>();

        // Check if email format is valid
        if (!ValidationUtil.isValidEmailFormat(email)) {
            responseMap.put("message", "Invalid email format");
            out.write(gson.toJson(responseMap));
            return;
        }

        // Check if email already exists in the database
        if (ValidationUtil.isEmailInUse(email)) {
            responseMap.put("message", "Email already in use");
            out.write(gson.toJson(responseMap));
            return;
        }

        // If email is valid and not in use, proceed with your logic
        responseMap.put("message", "Email is valid");
        out.write(gson.toJson(responseMap));
    }
}