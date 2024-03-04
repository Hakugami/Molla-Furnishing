package controllers.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static volatile CommandFactory instance = null;
    private final Map<String, FrontCommand> commandMap ;
    private CommandFactory() {
        commandMap = new HashMap<>();
        commandMap.put("login", new LoginCommand());


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
        return commandMap.getOrDefault(command, new UnknownCommand());
    }
}
