package controllers.commands.user;

import controllers.commands.FrontCommand;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import urls.enums.UrlMapping;

import java.io.IOException;

public class ResetPasswordChangeCommand extends FrontCommand {
    @Override
    public void process() throws ServletException, IOException {
        System.out.println("resetPasswordChange: processing request");
        ServletContext context = request.getServletContext();
         RequestDispatcher dispatcher = context.getNamedDispatcher(UrlMapping.RESETPASSWORDCHANGE.getServletName());
         dispatcher.forward(request, response);
    }
}
