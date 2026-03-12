package main.command;

import main.state.GameState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ChangeStateCommand implements Command{
    private final Supplier<GameState> stateSupplier;
    private final Consumer<GameState> changeState;

    public ChangeStateCommand(Supplier<GameState> stateSupplier, Consumer<GameState> changeState) {
        this.stateSupplier = stateSupplier;
        this.changeState = changeState;
    }

    @Override
    public void execute() {
        changeState.accept(stateSupplier.get());
    }

    @Override
    public void stop() {}
}
