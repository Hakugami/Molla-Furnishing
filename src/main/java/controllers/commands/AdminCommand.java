package controllers.commands;

import controllers.commands.FrontCommand;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class AdminCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        System.out.println("AdminCommand: processing request");
        ServletContext context = request.getServletContext();
        RequestDispatcher rd = context.getNamedDispatcher(UrlMapping.ADMIN.getServletName());
        rd.forward(request, response);
    }
}
