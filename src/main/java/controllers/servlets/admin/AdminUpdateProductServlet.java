package controllers.servlets.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.ProductDto;
import models.DTOs.UserDto;
import services.ProductService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;

public class AdminUpdateProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Update Product Servlet: Processing Request");
        String requestPath = req.getPathInfo();

        if (requestPath == null || requestPath.equals("/")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        ProductDto productDto;
        try {

            ObjectMapper mapper = new ObjectMapper();

            mapper.setDateFormat(new SimpleDateFormat("yyyy-MMMM-dd"));

            productDto = mapper.readValue(req.getReader(), ProductDto.class);
            System.out.println(productDto.getName() + productDto.getProductId());

        } catch (Exception e) {
            throw new ServletException(e);
        }
        ProductService productService = ProductService.getInstance();

        try {
            productService.updateProduct(productDto);
        } catch (Exception e) {
            System.out.println("Error updating product in Update Product Servlet");
            System.out.println(e.getMessage());
            e.printStackTrace();

        }

    }
}
