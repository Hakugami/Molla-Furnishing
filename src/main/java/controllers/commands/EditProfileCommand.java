package controllers.commands;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class EditProfileCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        System.out.println("EditProfileCommand: process");
        ServletContext context = request.getServletContext();
        RequestDispatcher requestDispatcher = context.getNamedDispatcher(UrlMapping.EDITPROFILE.getServletName());
        requestDispatcher.forward(request, response);

    }
}
