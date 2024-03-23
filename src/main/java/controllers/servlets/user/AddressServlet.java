package controllers.servlets.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.AddressDto;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import services.AuthenticationService;
import services.JWTService;
import services.UserService;
import urls.enums.UrlMapping;
import utils.CookiesUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AddressServlet extends HttpServlet {

    UserService userService;
    Logger logger = Logger.getLogger(AddressServlet.class.getName());

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(UrlMapping.ADDRESSOPERATION.getPageName()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddressServlet: doPost: processing request");
        String operation = req.getParameter("operation");

        Cookie jwtCookie = CookiesUtil.getCookie(req.getCookies(), "Authorization");
        JwtClaims claims = null;
        String subject = null;
        try {
            claims = JWTService.getInstance().validateToken(jwtCookie.getValue(), req.getRemoteAddr());
            subject = claims.getSubject();
            System.out.println("AddressServlet: doPost: subject: " + subject);
        } catch (InvalidJwtException e) {
            logger.severe("AddressServlet: doPost: InvalidJwtException: " + e.getMessage());
        } catch (MalformedClaimException e) {

        }


        if (claims != null) {
            switch (operation) {
                case "add":
                    String street = req.getParameter("address-street");
                    String city = req.getParameter("address-city");
                    String country = req.getParameter("country");
                    String postalCode = req.getParameter("address-postal");
                    String state = req.getParameter("state");

                    AddressDto addressDto = new AddressDto();
                    addressDto.setStreet(street);
                    addressDto.setCity(city);
                    addressDto.setZipCode(postalCode);
                    addressDto.setCountry(country);
                    addressDto.setState(state);
                    userService.addAddress(Long.parseLong(subject), addressDto);
                    break;
                case "remove":
                    System.out.println("AddressServlet: doPost: remove" + req.getParameter("addressId"));
                    long addressId = Long.parseLong(req.getParameter("addressId"));
                    System.out.println("AddressServlet: doPost: remove: " + addressId);
                    AddressDto addressDto1 = new AddressDto();
                    addressDto1.setId(addressId);
                    userService.removeAddress(Long.parseLong(subject), addressDto1);
                    break;
                case null, default:
                    logger.severe("AddressServlet: doPost: operation is null or default");
                    break;
            }
        }

    }
}
