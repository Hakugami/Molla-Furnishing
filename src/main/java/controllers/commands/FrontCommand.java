package controllers.commands;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class FrontCommand {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ServletContext context;

    public void init(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.context = context;
    }

    public abstract void process() throws ServletException, IOException;
}
