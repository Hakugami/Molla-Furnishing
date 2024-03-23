package controllers.commands.user;

import controllers.commands.FrontCommand;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class EmailValidationCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        System.out.println("EmailValidationCommand");
        ServletContext context = request.getServletContext();
        RequestDispatcher requestDispatcher = context.getNamedDispatcher(UrlMapping.EMAILVALIDATION.getServletName());
        requestDispatcher.forward(request, response);
    }
}
