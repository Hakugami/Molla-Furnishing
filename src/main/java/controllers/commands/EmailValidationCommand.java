package controllers.commands;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class EmailValidationCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        RequestDispatcher requestDispatcher = context.getNamedDispatcher("EmailValidationServlet");
    }
}
