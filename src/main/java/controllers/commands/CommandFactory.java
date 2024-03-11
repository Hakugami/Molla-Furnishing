package controllers.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CommandFactory {

    private static volatile CommandFactory instance = null;
    private final Map<String, Supplier<FrontCommand>> commandMap;

    private CommandFactory() {
        commandMap = new HashMap<>();
        commandMap.put("login", LoginCommand::new);
        commandMap.put("register", RegisterCommand::new);
        commandMap.put("RetrieveProducts", RetrieveProductsCommand::new);
        commandMap.put("home", HomeCommand::new);
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