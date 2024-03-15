package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import urls.enums.UrlMapping;

import java.io.IOException;

public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ProfileServlet: processing request");
        req.getRequestDispatcher(UrlMapping.PROFILE.getPageName()).forward(req, resp);
    }
}
