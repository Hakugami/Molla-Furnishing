package controllers.commands;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class PasswordValidationCommand  extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        System.out.println("PasswordValidationCommand");
        ServletContext context = request.getServletContext();
        RequestDispatcher requestDispatcher = context.getNamedDispatcher(UrlMapping.PASSWORDVALIDATION.getServletName());
        requestDispatcher.forward(request, response);

    }
}
