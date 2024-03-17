package controllers.servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.AddressDto;
import models.DTOs.UserDto;
import models.entity.Address;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import services.JWTService;
import utils.CookiesUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class LoadProfileServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(getClass().getName());
    public static final String CLAIM_KEY_EMAIL = "email";
    public static final String CLAIM_KEY_NAME = "name";
    public static final String CLAIM_KEY_PHONE = "phone";
    public static final String CLAIM_KEY_BIRTHDATE = "date of birth";
    public static final String CLAIM_KEY_GENDER = "gender";
    public static final String CLAIM_KEY_ADDRESSES = "addresses";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("LoadProfileServlet");

        Cookie cookie = CookiesUtil.getCookie(req.getCookies(), "Authorization");
        try {
            JwtClaims claims = JWTService.getInstance().validateToken(cookie.getValue(), req.getRemoteAddr());

            UserDto userDto = new UserDto();
            String email = claims.getClaimValue(CLAIM_KEY_EMAIL, String.class);
            String name = claims.getClaimValue(CLAIM_KEY_NAME, String.class);
            String phone = claims.getClaimValue(CLAIM_KEY_PHONE, String.class);
            String birthDate = claims.getClaimValue(CLAIM_KEY_BIRTHDATE, String.class);
            String gender = claims.getClaimValue(CLAIM_KEY_GENDER, String.class);
            List<AddressDto> addresses = claims.getClaimValue(CLAIM_KEY_ADDRESSES, List.class);
            userDto.setEmail(email);
            userDto.setName(name);
            userDto.setPhone(phone);
            userDto.setAddresses(addresses);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(birthDate);
                userDto.setBirthday(date);
            } catch (ParseException e) {
                logger.severe("Error parsing date: " + e.getMessage());
            }
            userDto.setGender(gender);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            Gson gson = new Gson();
            String responseBodyJson = gson.toJson(userDto);
            logger.info("LoadProfileServlet: sending response" + responseBodyJson);
            resp.getWriter().write(responseBodyJson);
        } catch (InvalidJwtException | MalformedClaimException e) {
            logger.severe("Error validating JWT: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        }
    }
}