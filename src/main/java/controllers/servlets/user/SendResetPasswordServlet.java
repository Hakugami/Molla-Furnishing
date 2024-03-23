package controllers.servlets.user;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AuthenticationService;
import urls.enums.UrlMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SendResetPasswordServlet extends HttpServlet {

    AuthenticationService authenticationService;

    @Override
    public void init() throws ServletException {
        authenticationService = new AuthenticationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SendResetPasswordServlet: processing request DO-GET");
        req.getRequestDispatcher(UrlMapping.RESETPASSWORD.getPageName()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SendResetPasswordServlet: processing request");
        String email = req.getParameter("email");
        try {
            String token = authenticationService.sendResetPasswordEmail(email);
            System.out.println("Reset Password Token: " + token
                    + " for email: " + email);
            addResetPasswordIdToApplicationContext(email, token);

            resp.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.sendRedirect(UrlMapping.RESETPASSWORD.getContextEmbeddedUrl(req.getContextPath()));
        }


    }

    private void addResetPasswordIdToApplicationContext(String email, String resetPasswordId) {
        ServletContext application = getServletConfig().getServletContext();
        Map<String, String> emailPasswordResetIdMap = (Map<String, String>) application.getAttribute("emailPasswordResetIdMap");

        if (emailPasswordResetIdMap == null) {
            emailPasswordResetIdMap = new HashMap<>();
        }

        emailPasswordResetIdMap.put(email, resetPasswordId);
        application.setAttribute("emailPasswordResetIdMap", emailPasswordResetIdMap);
    }
}
