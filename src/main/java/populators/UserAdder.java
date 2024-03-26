package populators;

import models.DTOs.AddressDto;
import models.DTOs.OrderDto;
import models.DTOs.OrderItemDto;
import models.DTOs.UserDto;
import models.entity.Address;
import models.entity.Order;
import models.entity.User;
import models.enums.UserRole;
import persistence.repositories.impl.UserRepository;
import services.AuthenticationService;
import services.OrderService;
import services.UserService;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class UserAdder {
    private static AuthenticationService authService;
    private static final Logger logger = Logger.getLogger(UserAdder.class.getName());

    private static UserService userService;

    public static void addUserAddressTest(){
        userService = new UserService();
        AddressDto address = new AddressDto();
        address.setCity("Gizaaaaaaaaaaa");
        address.setCountry("Egyptaaaaaaa");
        address.setState("El-Haramaaaaaaaaaaaaa");
        address.setStreet("El-Mollas St.aaaaaaaaaaaa");
        address.setZipCode("12345aaaaaaaaaaaaaaaaaaa");
        userService.addAddress(1L, address);
    }

    public static void printUserAddresses(){
        userService = new UserService();
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        User user = new UserRepository().read(1L).orElse(null);
        System.out.println(user.getAddresses());


    }

    public static void removeUserAddressTest(){
        userService = new UserService();
        AddressDto address = new AddressDto();
        address.setId(1L);
        address.setCity("Giza");
        address.setCountry("Egypt");
        address.setState("El-Haram");
        address.setStreet("El-Molla St.");
        address.setZipCode("12345");
        userService.removeAddress(1L, address);
    }

    public static void main(String[] args)  {

       addUserAddressTest();

    }

    public static void printOrders() {
        OrderService orderService = new OrderService();
        List<OrderDto> orderDtos = orderService.retrieveOrdersByUserId(1L);
        System.out.println("Number of Orders: " + orderDtos.size());
        for(OrderDto o : orderDtos) {
            List<OrderItemDto> orderItems = o.getOrderItems();
            System.out.println("Number of Item in This order: " + orderItems.size());
            for (OrderItemDto orderItem : orderItems) {
                System.out.println(orderItem.getProduct().getProductId() + " " + orderItem.getProduct().getName()
                                        + " "  + orderItem.getQuantity() + " " + orderItem.getProduct().getPrice());

            }

        }


    }
}
