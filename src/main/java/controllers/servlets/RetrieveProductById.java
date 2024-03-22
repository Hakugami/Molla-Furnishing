package controllers.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.ProductDto;
import services.ProductService;

import java.io.IOException;

public class RetrieveProductById extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Retrieve Product by ID Servlet: Processing Request");
        String requestPath = req.getPathInfo();

        if (requestPath == null || requestPath.equals("/")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        try {
            System.out.println(req.getParameter("productId"));
            long productIdParam = Long.parseLong(req.getParameter("productId"));

            ProductService productService = ProductService.getInstance();
            ProductDto product = productService.getProductById(productIdParam);

            // Convert the list of ProductDto objects to JSON
            String json = new Gson().toJson(product);

            // Write the JSON to the response
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json);

        }catch (NumberFormatException e){
            System.out.println("Number format exception when parsing productID parameter in RetrieveProductbyID Servlet");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID parameter is missing");
        }

    }
}
