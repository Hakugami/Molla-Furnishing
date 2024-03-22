package controllers.servlets.admin;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.UserDto;
import services.UserService;
import urls.enums.UrlMapping;

import java.io.IOException;
import java.util.List;

public class AdminAllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AdminAllUsersServlet: processing request");

        List<UserDto> users = getUsersDto(req);
        req.setAttribute("users", users);

        req.getRequestDispatcher(UrlMapping.ADMINALLUSERS.getPageName()).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AdminAllUsersServlet-doPost: processing request");

        List<UserDto> products = getUsersDto(req);

        // Convert the list of ProductDto objects to JSON
        String json = new Gson().toJson(products);

        // Write the JSON to the response
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    private List<UserDto> getUsersDto(HttpServletRequest req) {

        String page = req.getParameter("currentPage");
        int pageInt = page != null ? Integer.parseInt(page) : 1;
        int size = 10;

        UserService userService = new UserService();
        return userService.getUsers(pageInt, size);
    }
}
