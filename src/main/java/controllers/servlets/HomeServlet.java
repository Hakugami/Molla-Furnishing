package controllers.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.ProductDto;
import persistence.repositories.helpers.ProductFilter;
import services.ProductService;
import urls.enums.UrlMapping;

import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HomeServlet: processing request");
        List<ProductDto> products = getProductsDTO();
        req.setAttribute("products", products);
        System.out.println(products);
        req.getRequestDispatcher(UrlMapping.HOME.getPageName()).forward(req, resp);
    }

    private List<ProductDto> getProductsDTO() {

        // Create a ProductFilter object
        ProductFilter filter = new ProductFilter();

        // Set Filters
        filter.setSortBy("dateAdded");
        filter.setSortOrder("desc");

        int pageInt = 1;
        int limitInt = 8
                ;
        // Call the getProductByFilter method from the ProductService class
        ProductService productService = ProductService.getInstance();

        return productService.getProductByFilter(pageInt, limitInt, filter);
    }
}
