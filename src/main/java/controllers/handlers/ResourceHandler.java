package controllers.handlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ResourceHandler {
    boolean handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
