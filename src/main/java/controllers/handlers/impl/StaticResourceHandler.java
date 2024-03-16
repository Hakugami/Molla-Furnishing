package controllers.handlers.impl;

import controllers.handlers.ResourceHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class StaticResourceHandler implements ResourceHandler {
    private static final List<String> STATIC_RESOURCE_PREFIXES = Arrays.asList("/css/", "/js/", "/images/", "/audio/", "/video/", "/webfonts/," +
            "/assets/");
    private static final List<String> STATIC_RESOURCE_SUFFIX = Arrays.asList(".html",".js",".css",".scss",".jpg",".png");

    @Override
    public boolean handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path != null && (STATIC_RESOURCE_PREFIXES.stream().anyMatch(path::startsWith) || (STATIC_RESOURCE_SUFFIX.stream().anyMatch(path::endsWith)))) {
            if(path.startsWith("/assets/")){
                path = "/Adminpanel" + path;
            }
            request.getServletContext().getRequestDispatcher(path).forward(request, response);
            return true;
        }

        return false;
    }
}
