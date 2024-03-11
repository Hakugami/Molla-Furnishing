package controllers.commands;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class HomeCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        System.out.println("HomeCommand: processing request");
        ServletContext context = request.getServletContext();
        RequestDispatcher rd = context.getNamedDispatcher("HomeServlet");
        rd.forward(request, response);
    }
}
