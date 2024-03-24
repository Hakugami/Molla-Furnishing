package controllers.filters;

import controllers.handlers.ResourceHandler;
import controllers.handlers.impl.StaticResourceHandler;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class StaticResourceFilter implements Filter {

    private final List<ResourceHandler> resourceHandlers = List.of(new StaticResourceHandler());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic here
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        for (ResourceHandler handler : resourceHandlers) {
            if (handler.handle(req, res)) {
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup logic here
    }
}