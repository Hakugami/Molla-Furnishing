package controllers.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.ProductDto;
import services.ProductService;
import urls.enums.UrlMapping;

import java.io.IOException;

public class AdminViewProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long productId = Long.parseLong(req.getParameter("action"));
        ProductService productService = ProductService.getInstance();
        ProductDto product = productService.getProductById(productId);


        req.getRequestDispatcher(UrlMapping.ADMINVIEWPRODUCT.getPageName()).forward(req, resp);
    }
}
