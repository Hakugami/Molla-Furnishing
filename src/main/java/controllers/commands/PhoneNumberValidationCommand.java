package controllers.commands;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class PhoneNumberValidationCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        System.out.println("Phone Number Validation Command");
        ServletContext context = request.getServletContext();
        RequestDispatcher dispatcher = context.getNamedDispatcher(UrlMapping.PHONEVALIDATION.getServletName());
        dispatcher.forward(request, response);
    }
}
