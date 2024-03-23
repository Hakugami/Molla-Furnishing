package controllers.servlets.user;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.repositories.helpers.ProductFilter;
import services.ProductService;
import urls.enums.UrlMapping;

import java.io.IOException;

public class ProductPageServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ProductPageServlet: processing request");

        String requestPath = req.getPathInfo();

        System.out.println("Request path: " + requestPath);

        if (requestPath == null || requestPath.equals("/")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        req.getRequestDispatcher(UrlMapping.PRODUCTPAGE.getPageName()).forward(req, resp);

    }
}
