package controllers.servlets;

import controllers.commands.factory.CommandFactory;
import controllers.commands.FrontCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import urls.enums.UrlMapping;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FrontController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontController: processing request");
        String pathInfo = req.getPathInfo(); // /{value}/test
        System.out.println("PathInfo: " + pathInfo);

        //Redirects home if path is empty "/"
        if ("/".equals(pathInfo)) {
            pathInfo = UrlMapping.HOME.getUrl();
        }

        if (pathInfo.startsWith("/admin")) {

            // Use a regular expression to extract the command key
            Pattern pattern = Pattern.compile(".*/([a-zA-Z]+)/?.*");
            Matcher matcher = pattern.matcher(pathInfo);
            if (matcher.find()) {

                System.out.println("Matcher "+matcher.group(1));
                FrontCommand commandInstance = CommandFactory.getInstance().getCommand("admin" + matcher.group(1));
                commandInstance.init(getServletContext(), req, resp);
                commandInstance.process();
            } else {
                // Handle the case where the pathInfo does not match the expected format
                System.out.println("Invalid pathInfo format: " + pathInfo);
            }

        } else {

            // Use a regular expression to extract the command key
            Pattern pattern = Pattern.compile(".*/([a-zA-Z]+)/?.*");
            Matcher matcher = pattern.matcher(pathInfo);
            if (matcher.find()) {
                String commandKey = matcher.group(1);

                FrontCommand commandInstance = CommandFactory.getInstance().getCommand(commandKey);
                commandInstance.init(getServletContext(), req, resp);
                commandInstance.process();
            } else {
                // Handle the case where the pathInfo does not match the expected format
                System.out.println("Invalid pathInfo format: " + pathInfo);
            }
        }
    }
}
