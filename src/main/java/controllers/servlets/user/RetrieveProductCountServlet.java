package controllers.servlets.user;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.ProductService;

import java.io.IOException;

public class RetrieveProductCountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Retrieve Product Count Servlet: Processing Request");
        String requestPath = req.getPathInfo();

        if(requestPath==null|| requestPath.equals("/")){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //Fetch Product count from Service
        ProductService productService = ProductService.getInstance();
        long productCount = productService.countProducts();

        //Convert the product count to JSON
        String json = new Gson().toJson(productCount);

        //Write he JSON to the response
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
