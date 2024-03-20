package controllers.commands;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class AddressCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        System.out.println("AddressCommand: processing request");
        ServletContext context = request.getServletContext();
        RequestDispatcher requestDispatcher = context.getNamedDispatcher(UrlMapping.ADDRESSOPERATION.getServletName());
        requestDispatcher.forward(request, response);
    }
}
