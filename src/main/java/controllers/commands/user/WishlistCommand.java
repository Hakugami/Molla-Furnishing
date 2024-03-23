package controllers.commands.user;

import controllers.commands.FrontCommand;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class WishlistCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        System.out.println("WishlistCommand: processing request");
        ServletContext context = request.getServletContext();
        RequestDispatcher dispatcher = context.getNamedDispatcher(UrlMapping.WISHLIST.getServletName());
        dispatcher.forward(request, response);
    }
}
