package ru.job4j.tracker;

import ru.job4j.tracker.action.UserAction;

public class CreateManyItems implements UserAction {
    private final Output out;

    public CreateManyItems(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Создать несколько заявок";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Создать несколько заявок ===");
        int count = input.askInt("Введите кол-во заявок ");
        for (int i = 0; i < count; i++) {
            tracker.add(new Item("Заявка № " + i));
        }
        out.println("Добавлено заявок: " + count);
        return true;
    }
}