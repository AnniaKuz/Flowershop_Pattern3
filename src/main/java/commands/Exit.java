package commands;

import java.io.IOException;

public class Exit implements Command {

    @Override
    public void execute() throws IOException {
        System.out.println("Exit");
    }
}
