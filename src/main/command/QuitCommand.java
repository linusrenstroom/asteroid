package main.command;

public class QuitCommand implements Command {

    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public void stop() {
    }
}


