package utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailUtil {
    private static final String SITE_EMAIL = "mollafurniturestore@gmail.com";
    private static final String SITE_EMAIL_PASSWORD = "ybpp dpbu dnhy fuyz";

    private static HtmlEmail getEmail() {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(SITE_EMAIL, SITE_EMAIL_PASSWORD));
        email.setSSLOnConnect(true);
        try {
            email.setFrom(SITE_EMAIL);
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return email;
    }

    public static void sendUserRegistrationEmail(String email, String name) {
        new Thread(() -> {
            HtmlEmail htmlEmail = getEmail();
            try {

                htmlEmail.setSubject("Welcome to Molla Furnishing");
                String htmlMsg = String.format("""
                        <html>
                          <head>
                            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                            <style>
                              body {
                                font-family: sans-serif;
                                margin: 0;
                                padding: 30px;
                              }
                              h1 {
                                font-size: 24px;
                                color: #333;
                                margin-bottom: 15px;
                              }
                              p {
                                font-size: 16px;
                                line-height: 1.5;
                                color: #666;
                                margin-bottom: 15px;
                              }
                              .welcome-message {
                                text-align: center;
                                margin-bottom: 30px;
                              }
                              .benefits-list {
                                list-style: disc;
                                margin-left: 20px;
                                margin-bottom: 30px;
                              }
                              .commitment {
                                font-weight: bold;
                              }
                              .call-to-action {
                                text-align: center;
                                background-color: #f5f5f5;
                                padding: 15px;
                                border-radius: 5px;
                                margin-bottom: 30px;
                              }
                              .footer {
                                font-size: 12px;
                                color: #999;
                                text-align: center;
                                margin-top: 30px;
                              }
                            </style>
                          </head>
                          <body>
                            <div class="welcome-message">
                              <h1>Welcome to Molla Furnishing, <span style="color:#54a054;">%s</span>!</h1>
                            </div>
                            <p>We're thrilled to have you join our community of design enthusiasts. Get ready to experience a world of comfort and style curated just for you.</p>
                            <div class="benefits-list">
                              <p>As a valued member, you'll enjoy exclusive benefits like:</p>
                              <li>Member-only discounts and offers</li>
                              <li>Priority access to new arrivals and sales</li>
                              <li>Personalized recommendations based on your preferences</li>
                              <li>Easy account management and tracking</li>
                            </div>
                            <p class="commitment">We're committed to providing you with an exceptional shopping experience, every step of the way.</p>
                            <div class="call-to-action">
                              <p>Explore our thoughtfully crafted collections and create your dream home today!</p>
                            </div>
                            <div class="footer">
                              <p>Need assistance? Feel free to reach out to our friendly support team.</p>
                              <p>&copy; Molla Furnishing 2024</p>
                            </div>
                          </body>
                        </html>
                        """, name);

                htmlEmail.setHtmlMsg(htmlMsg);
                htmlEmail.addTo(email);
                htmlEmail.send();
            } catch (EmailException e) {
                e.printStackTrace();
                throw new RuntimeException("Error sending email: " + e.getMessage());
            }
        }).start();
    }

    public static void sendOrderConfirmationEmail(String email, String name, String orderNumber) {
        new Thread(() -> {
            HtmlEmail htmlEmail = getEmail();
            try {
                htmlEmail.setSubject("Order Confirmation");
                htmlEmail.setHtmlMsg("<h1>Order Confirmation</h1><p>Dear " + name + ",</p><p>Your order with order number " + orderNumber + " has been confirmed. You will receive a notification once your order has been shipped. Thank you for shopping with Molla Furnishing.</p>");
                htmlEmail.addTo(email);
                htmlEmail.send();
            } catch (EmailException e) {
                e.printStackTrace();
                throw new RuntimeException("Error sending email: " + e.getMessage());
            }
        }).start();
    }

    public static void sendResetPasswordEmail(String email, String name, String token) {
        new Thread(() -> {
            HtmlEmail htmlEmail = getEmail();
            try {
                htmlEmail.setSubject("Reset Password");
                htmlEmail.setHtmlMsg("<h1>Reset Password</h1><p>Dear " + name + ",</p><p>You have requested to reset your password. Please click the link below to reset your password.</p><a href='http://localhost:4545/molla/view/reset-password?token=" + token + "'>Reset Password</a>");
                htmlEmail.addTo(email);
                htmlEmail.send();
            } catch (EmailException e) {
                e.printStackTrace();
                throw new RuntimeException("Error sending email: " + e.getMessage());
            }
        }).start();
    }

    public static void main(String[] args) {
        sendUserRegistrationEmail("islam.ahmed.dev@gmail.com", "Islam Ahmed");
    }

}