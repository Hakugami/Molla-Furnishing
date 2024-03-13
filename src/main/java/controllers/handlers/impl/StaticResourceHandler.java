package controllers.handlers.impl;

import controllers.handlers.ResourceHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class StaticResourceHandler implements ResourceHandler {
    private static final List<String> STATIC_RESOURCE_PREFIXES = Arrays.asList("/css/", "/js/", "/images/", "/audio/", "/video/", "/webfonts/");
    private static final String STATIC_RESOURCE_SUFFIX = ".html";

    @Override
    public boolean handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path != null && (STATIC_RESOURCE_PREFIXES.stream().anyMatch(path::startsWith) || path.endsWith(STATIC_RESOURCE_SUFFIX))) {
            request.getServletContext().getRequestDispatcher(path).forward(request, response);
            return true;
        }

        return false;
    }
}
