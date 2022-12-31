package commands;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SwitchCommand {
    private Map<String, Command> commandMap;

    {
        commandMap = new HashMap<>();
    }

    public void registerCommand(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public void execute(String commandName) {
        if (commandMap.get(commandName) == null) {
            throw new IllegalArgumentException("Command is not registered");
        }
        try {
            commandMap.get(commandName).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
