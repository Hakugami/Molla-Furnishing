package controllers.commands;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class RetrieveProductsCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        System.out.println("RetrieveProductsCommand: processing request");
        ServletContext context = request.getServletContext();
        RequestDispatcher rd = context.getNamedDispatcher(UrlMapping.RETRIEVE_PRODUCTS.getServletName());
        rd.forward(request, response);
    }
}
