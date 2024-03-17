package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import urls.enums.UrlMapping;

import java.io.IOException;

public class AboutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AboutServlet: processing request");
        req.getRequestDispatcher(UrlMapping.ABOUT.getPageName()).forward(req, resp);
    }
}