package utils;

import models.DTOs.UserDto;
import services.UserService;

import java.util.regex.Pattern;

public class ValidationUtil {

    // Regular expression for email validation
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static boolean isValidEmailFormat(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(email).matches();
    }

    public static boolean isEmailInUse(String email) {
        UserService userService = new UserService();
        UserDto user = userService.getUserByEmail(email);
        return user != null;
    }


    public static String validatePassword(String password) {
        // Check the length of the password
        if (password.length() < 8) {
            return "Password must be at least 8 characters long";
        }

        // Check for uppercase letters
        if (!password.matches(".*[A-Z].*")) {
            return "Password must contain at least one uppercase letter";
        }

        // Check for lowercase letters
        if (!password.matches(".*[a-z].*")) {
            return "Password must contain at least one lowercase letter";
        }

        // Check for numbers
        if (!password.matches(".*[0-9].*")) {
            return "Password must contain at least one number";
        }

        // Check for special characters
        if (!password.matches(".*[!@#$%^&*].*")) {
            return "Password must contain at least one special character (!@#$%^&*)";
        }

        // If all checks pass, return null
        return null;
    }
}