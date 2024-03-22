package controllers.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AuthenticationService;
import urls.enums.UrlMapping;

import java.io.IOException;
import java.util.Map;

public class ResetPasswordChangeServlet extends HttpServlet {
    AuthenticationService authenticationService;

    @Override
    public void init() throws ServletException {
        authenticationService = new AuthenticationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ResetPasswordChangeServlet: processing request DO-GET");
        req.getRequestDispatcher(UrlMapping.RESETPASSWORDCHANGE.getPageName()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ResetPasswordChangeServlet: processing request");
        String requestEmail = req.getParameter("email");
        String requestConfirmationId = req.getParameter("confirmationToken");
        String requestNewPassword = req.getParameter("newPassword");
        try {
            ServletContext application = getServletConfig().getServletContext();

            Map<String, String> emailPasswordResetIdMap = (Map<String, String>) application.getAttribute("emailPasswordResetIdMap");
            System.out.println("email: " + requestEmail);
            System.out.println("Email Password Reset ID Map: " + emailPasswordResetIdMap);
            emailPasswordResetIdMap.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));
            String confirmationId = emailPasswordResetIdMap.get(requestEmail);

            if (confirmationId.equals(requestConfirmationId)) {
                System.out.println("Reset Password Token: " + requestConfirmationId
                        + " for email: " + requestEmail);

                authenticationService.resetPassword(requestEmail, requestNewPassword);

                emailPasswordResetIdMap.remove(requestEmail);

                resp.sendRedirect(UrlMapping.LOGIN.getContextEmbeddedUrl(req.getContextPath()));
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.sendRedirect(UrlMapping.RESETPASSWORDCHANGE.getContextEmbeddedUrl(req.getContextPath()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.sendRedirect(UrlMapping.RESETPASSWORDCHANGE.getContextEmbeddedUrl(req.getContextPath()));
        }
    }
}
