package controllers.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.UserService;
import models.DTOs.UserDto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class EmailValidationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        UserService userService = new UserService();
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");

        // Regular expression for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);

        // Check if email format is valid
        if (!pattern.matcher(email).matches()) {
            out.write(gson.toJson("Invalid email format"));
            return;
        }

        // Check if email already exists in the database
        UserDto user = userService.getUserByEmail(email);
        if (user != null) {
            out.write(gson.toJson("Email already in use"));
            return;
        }

        // If email is valid and not in use, proceed with your logic
        out.write(gson.toJson("Email is valid"));
    }
}