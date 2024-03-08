package controllers.servlets;

import controllers.commands.CommandFactory;
import controllers.commands.FrontCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


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
        String pathInfo = req.getPathInfo(); // /{value}/test
        System.out.println("PathInfo: " + pathInfo);
        String[] pathParts = pathInfo.split("/");
        String commandKey = pathParts[1];
        FrontCommand commandInstance = CommandFactory.getInstance().getCommand(commandKey);
        commandInstance.init(getServletContext(), req, resp);
        commandInstance.process();
    }
}
