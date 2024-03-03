package controllers.commands;

public class CommandFactory {

    private static volatile CommandFactory instance = null;

    private CommandFactory() {
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
        FrontCommand frontCommand = null;
        try {
            Class<?> type = Class.forName(String.format("controllers.commands.%sCommand", command));
            frontCommand = (FrontCommand) type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return frontCommand;
    }
}
