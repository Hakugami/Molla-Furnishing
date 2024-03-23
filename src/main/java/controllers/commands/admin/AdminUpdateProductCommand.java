package controllers.commands.admin;

import controllers.commands.FrontCommand;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class AdminUpdateProductCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        System.out.println("Admin Update Product Command: processing request");
        ServletContext context = request.getServletContext();
        RequestDispatcher rd = context.getNamedDispatcher(UrlMapping.ADMINUPDATEPRODUCT.getServletName());
        System.out.println("Forwarding Request to Admin Update Servlet...");
        rd.forward(request, response);
    }
}
