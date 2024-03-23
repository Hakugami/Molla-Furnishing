package controllers.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.DTOs.OrderDto;
import models.DTOs.UserDto;
import services.UserService;
import urls.enums.UrlMapping;

import java.io.IOException;
import java.util.List;

public class AdminViewUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AdminViewUserServlet: processing request");

        UserDto user = getUserDto(req);
        req.setAttribute("user", user);

//        OrderDto orders = getOrdersDto(user);
//        req.setAttribute("orders", orders);

        req.getRequestDispatcher(UrlMapping.ADMIN_VIEW_USER.getPageName()).forward(req, resp);
    }

//    private OrderDto getOrdersDto(UserDto user) {
//        OrderService orderService = new OrderService();
//        orderService.getOrdersByUser(user);
//
//    }

    private UserDto getUserDto(HttpServletRequest req) {
        String stringId = req.getParameter("id");
        long id = stringId != null ? Long.parseLong(stringId) : -1;

        if (id != -1) {
            UserService userService = new UserService();
            UserDto user = userService.getUserById(id);
            user.setPassword(null);
            return user;
        }

        return null;
    }
}
