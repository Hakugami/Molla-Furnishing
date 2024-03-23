package controllers.servlets.user;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.ProductDto;
import persistence.repositories.helpers.ProductFilter;
import services.ProductService;

import java.io.IOException;
import java.util.List;

public class RetrieveProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("RetrieveProductsServlet: processing request");
        String requestPath = req.getPathInfo();

        if (requestPath == null || requestPath.equals("/")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        List<ProductDto> products = getProductDTO(req);

        // Convert the list of ProductDto objects to JSON
        String json = new Gson().toJson(products);

        // Write the JSON to the response
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    private List<ProductDto> getProductDTO(HttpServletRequest req) {
        String name = req.getParameter("name");
        String category = req.getParameter("category");
        String minPrice = req.getParameter("minPrice");
        String maxPrice = req.getParameter("maxPrice");
        String minRating = req.getParameter("minRating");
        String maxRating = req.getParameter("maxRating");
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        String sortBy = req.getParameter("sortBy");
        String sortOrder = req.getParameter("sortOrder");


        // Create a ProductFilter object
        ProductFilter filter = new ProductFilter();

        // Set the filter parameters to the ProductFilter object
        if (name != null) {
            filter.setName(name);
        }
        if (category != null) {
            filter.setCategory(category);
        }
        if (minPrice != null) {
            filter.setMinPrice(Double.parseDouble(minPrice));
        }
        if (maxPrice != null) {
            filter.setMaxPrice(Double.parseDouble(maxPrice));
        }
        if (minPrice != null && maxPrice != null) {
            filter.setMinPrice(Double.parseDouble(minPrice));
            filter.setMaxPrice(Double.parseDouble(maxPrice));
        }
        if (minRating != null) {
            filter.setMinRating(Double.parseDouble(minRating));
        }
        if (maxRating != null) {
            filter.setMaxRating(Double.parseDouble(maxRating));
        }
        if (sortBy != null) {
            filter.setSortBy(sortBy);
        }
        if (sortOrder != null) {
            filter.setSortOrder(sortOrder);
        }

        int pageInt = page != null ? Integer.parseInt(page) : 1;
        int limitInt = limit != null ? Integer.parseInt(limit) : 5;
        // Call the getProductByFilter method from the ProductService class
        ProductService productService = ProductService.getInstance();
        List<ProductDto> products = productService.getProductByFilter(pageInt, limitInt, filter);
        return products;
    }
}
