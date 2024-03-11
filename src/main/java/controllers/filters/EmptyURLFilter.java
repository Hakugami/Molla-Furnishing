package controllers.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import urls.enums.UrlMapping;

import java.io.IOException;

public class EmptyURLFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI().substring(req.getContextPath().length());

        if (path.equals("/") || path.isEmpty()) {
           res.sendRedirect(req.getContextPath() + UrlMapping.CONTEXT_DEFAULT.getUrl()+"/");
        } else {
            chain.doFilter(request, response);
        }
    }
}
