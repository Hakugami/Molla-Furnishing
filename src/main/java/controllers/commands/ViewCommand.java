package controllers.commands;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class ViewCommand extends FrontCommand{
    @Override
    public void process(String viewName) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        String page = processView(viewName);
        context.getNamedDispatcher(page).forward(request, response);
    }

    private String processView(String viewName) {
        return UrlMapping.getServletNameForCommand(viewName);
    }
}
