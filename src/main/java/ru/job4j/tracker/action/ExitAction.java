package ru.job4j.tracker.action;

import ru.job4j.tracker.*;

public class ExitAction implements UserAction {
    private final Output output;

    public ExitAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Завершить программу";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        output.println("=== Завершение программы ===");
        return false;
    }
}
