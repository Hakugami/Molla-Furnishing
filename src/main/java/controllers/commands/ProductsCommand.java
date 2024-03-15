package controllers.commands;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class ProductsCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        RequestDispatcher requestDispatcher = context.getNamedDispatcher(UrlMapping.PRODUCTS.getServletName());
        requestDispatcher.forward(request, response);

    }
}
