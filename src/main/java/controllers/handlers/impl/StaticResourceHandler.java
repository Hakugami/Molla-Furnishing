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
        System.out.println("StaticResourceHandler: processing request");
        System.out.println("PathInfo: " + path);

        if (path != null) {
            String modifiedPath = path.replace("/ProductPage", "");
            System.out.println("Modified PathInfo: " + modifiedPath);


            if ((STATIC_RESOURCE_PREFIXES.stream().anyMatch(modifiedPath::startsWith) || path.endsWith(STATIC_RESOURCE_SUFFIX))) {
                request.getServletContext().getRequestDispatcher(modifiedPath).forward(request, response);
                return true;
            }
        }


        return false;
    }
}
