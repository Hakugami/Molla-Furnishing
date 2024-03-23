package controllers.commands.user;

import controllers.commands.FrontCommand;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class RetrieveProductByIDCommand  extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        System.out.println("RetrieveProductByID Command: processing request");
        ServletContext context = request.getServletContext();
        RequestDispatcher rd = context.getNamedDispatcher(UrlMapping.RETRIEVE_PRODUCT_BY_ID.getServletName());
        rd.forward(request, response);
    }
}
