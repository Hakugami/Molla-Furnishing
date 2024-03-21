package controllers.commands;

import org.bouncycastle.oer.its.etsi102941.Url;
import controllers.commands.admin.*;
import urls.enums.UrlMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CommandFactory {

    private static volatile CommandFactory instance = null;
    private final Map<String, Supplier<FrontCommand>> commandMap;

    private CommandFactory() {
        commandMap = new HashMap<>();
        commandMap.put(UrlMapping.LOGIN.getCommand(), LoginCommand::new);
        commandMap.put(UrlMapping.REGISTER.getCommand(), RegisterCommand::new);
        commandMap.put(UrlMapping.RETRIEVE_PRODUCTS.getCommand(), RetrieveProductsCommand::new);
        commandMap.put(UrlMapping.RETRIEVE_PRODUCT_COUNT.getCommand(), RetrieveProductCountCommand::new);
        commandMap.put(UrlMapping.HOME.getCommand(), HomeCommand::new);
        commandMap.put(UrlMapping.PROFILE.getCommand(), ProfileCommand::new);
        commandMap.put(UrlMapping.PRODUCTS.getCommand(), ProductsCommand::new);
        commandMap.put(UrlMapping.PRODUCTPAGE.getCommand(), ProductPageCommand::new);
        commandMap.put(UrlMapping.REVIEW.getCommand(), ReviewCommand::new);
        commandMap.put(UrlMapping.EDITPROFILE.getCommand(), EditProfileCommand::new);
        commandMap.put(UrlMapping.EMAILVALIDATION.getCommand(), EmailValidationCommand::new);
        commandMap.put(UrlMapping.PASSWORDVALIDATION.getCommand(), PasswordValidationCommand::new);
        commandMap.put(UrlMapping.PHONEVALIDATION.getCommand(), PhoneNumberValidationCommand::new);
        commandMap.put(UrlMapping.ABOUT.getCommand(), AboutCommand::new);
        commandMap.put(UrlMapping.WISHLIST.getCommand(), WishlistCommand::new);
        commandMap.put(UrlMapping.CART.getCommand(), CartCommand::new);
        commandMap.put(UrlMapping.LOADPROFILE.getCommand(), LoadProfileCommand::new);
        commandMap.put(UrlMapping.LOGOUT.getCommand(), LogoutCommand::new);
        commandMap.put(UrlMapping.MYPROFILE.getCommand(), MyProfileCommand::new);
        commandMap.put(UrlMapping.ADDRESSOPERATION.getCommand(), AddressCommand::new);
        commandMap.put(UrlMapping.ABOUT.getCommand(), AboutCommand::new);
        commandMap.put(UrlMapping.WISHLIST.getCommand(), WishlistCommand::new);
        commandMap.put(UrlMapping.CART.getCommand(), CartCommand::new);
        //Admin Commands
        commandMap.put(UrlMapping.ADMINHOME.getCommand(), AdminHomeCommand::new);
        commandMap.put(UrlMapping.ADMINLOGIN.getCommand(), AdminLoginCommand::new);
        commandMap.put(UrlMapping.ADMINVIEWUSER.getCommand(), AdminViewUserCommand::new);
        commandMap.put(UrlMapping.ADMINADDUSER.getCommand(), AdminAddUserCommand::new);
        commandMap.put(UrlMapping.ADMINALLUSERS.getCommand(), AdminAllUsersCommand::new);
        commandMap.put(UrlMapping.ADMINVIEWPRODUCT.getCommand(), AdminViewProductCommand::new);
        commandMap.put(UrlMapping.ADMINALLPRODUCTS.getCommand(), AdminAllProductsCommand::new);
        commandMap.put(UrlMapping.ADMINADDPRODUCT.getCommand(), AdminAddProductCommand::new);
    }

    public static CommandFactory getInstance() {
        if (instance == null) {
            synchronized (CommandFactory.class) {
                if (instance == null) {
                    instance = new CommandFactory();
                }
            }
        }
        return instance;
    }

    public FrontCommand getCommand(String command) {
        Supplier<FrontCommand> commandSupplier = commandMap.get(command);
        return (commandSupplier != null) ? commandSupplier.get() : new UnknownCommand();
    }
}