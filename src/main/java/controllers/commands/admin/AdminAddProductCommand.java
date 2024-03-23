package controllers.commands.admin;

import controllers.commands.FrontCommand;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class AdminAddProductCommand  extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        System.out.println("Admin Add Product Command: processing request");
        ServletContext context = request.getServletContext();
        RequestDispatcher rd = context.getNamedDispatcher(UrlMapping.ADMIN_ADD_PRODUCT.getServletName());
        System.out.println("Forwarding Request to Add Product Page...");
        rd.forward(request, response);
    }
}
