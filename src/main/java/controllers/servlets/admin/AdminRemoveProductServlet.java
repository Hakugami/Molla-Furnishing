package controllers.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.ProductDto;
import persistence.repositories.impl.ProductRepository;
import services.ProductService;

import java.io.IOException;

public class AdminRemoveProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Admin Delete Product Servlet: Processing Request");
        String requestPath = req.getPathInfo();

        if (requestPath == null || requestPath.equals("/")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        long productId = Long.parseLong(req.getParameter("productId"));
        ProductService productService = ProductService.getInstance();

        try {
            productService.deleteProduct(productId);
            resp.setStatus(HttpServletResponse.SC_OK); // 200 OK
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete product."); // 500 Internal Server Error
        }

    }
}
