package controllers.servlets;

import controllers.commands.ViewCommand;
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
        String pathInfo = req.getPathInfo();

        if ("/".equals(pathInfo)) {
            pathInfo = UrlMapping.HOME.getUrl();
        }

        if (pathInfo.startsWith("/admin")) {
            processPathInfo(pathInfo, "admin", req, resp);
        } else {
            processPathInfo(pathInfo, "", req, resp);
        }
    }

    private void processPathInfo(String pathInfo, String prefix, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pattern pattern = Pattern.compile(".*/([a-zA-Z]+)/?.*");
        Matcher matcher = pattern.matcher(pathInfo);
        if (matcher.find()) {
            String commandKey = matcher.group(1);
            FrontCommand commandInstance = createCommandInstance();
            commandInstance.init(getServletContext(), req, resp);
            commandInstance.process(String.format("%s%s", prefix, commandKey));
        } else {
            System.out.println("Invalid pathInfo format: " + pathInfo);
        }
    }

    private FrontCommand createCommandInstance() {
        return new ViewCommand();
    }
}