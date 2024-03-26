package controllers.servlets.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import services.CartService;
import services.CheckoutService;
import services.JWTService;
import urls.enums.UrlMapping;
import utils.CookiesUtil;

import java.io.IOException;

public class CheckoutServlet extends HttpServlet {
    private CheckoutService checkoutService;
    private CartService cartService;

    @Override
    public void init() throws ServletException {
        checkoutService = new CheckoutService();
        cartService = new CartService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(UrlMapping.CHECKOUT.getPageName()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CheckoutServlet");
        Long addressId = Long.valueOf(req.getParameter("addressId"));
        Cookie cookie = CookiesUtil.getCookie(req.getCookies(), "Authorization");
        if (cookie == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.sendRedirect(UrlMapping.LOGIN.getContextEmbeddedUrl(req.getContextPath()));
        } else {
            try {
                JwtClaims jwtClaims = JWTService.getInstance().validateToken(cookie.getValue(), req.getRemoteAddr());
                System.out.println("CheckoutServlet jwtClaims: " + jwtClaims.getSubject());
                String result = checkoutService.checkout(Long.valueOf(jwtClaims.getSubject()), addressId);
                if (result != null) {
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().write(result);
                    return;
                }
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Checkout successful");
                resp.sendRedirect(UrlMapping.HOME.getContextEmbeddedUrl(req.getContextPath()));
            } catch (InvalidJwtException e) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.sendRedirect(UrlMapping.LOGIN.getContextEmbeddedUrl(req.getContextPath()));

            } catch (MalformedClaimException e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            }
        }
    }
}
