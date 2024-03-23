package controllers.commands.admin;

import controllers.commands.FrontCommand;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class AdminAllProductsCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        System.out.println("Admin All Products Command: processing request");
        ServletContext context = request.getServletContext();
        RequestDispatcher rd = context.getNamedDispatcher(UrlMapping.ADMIN_ALL_PRODUCTS.getServletName());
        System.out.println("Forwarding Request to Admin All Products Page...");
        rd.forward(request, response);
    }
}
