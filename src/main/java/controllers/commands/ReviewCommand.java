package controllers.commands;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class ReviewCommand extends FrontCommand{
    @Override
    public void process() throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        RequestDispatcher dispatcher = context.getNamedDispatcher(UrlMapping.REVIEW.getServletName());
        dispatcher.forward(request, response);
    }
}
