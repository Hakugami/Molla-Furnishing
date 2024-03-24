package controllers.servlets.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import urls.enums.UrlMapping;

import java.io.IOException;

public class AddressBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddressBookServlet: doGet: processing request");
        req.getRequestDispatcher(UrlMapping.ADDRESS.getPageName()).forward(req, resp);
    }
}
