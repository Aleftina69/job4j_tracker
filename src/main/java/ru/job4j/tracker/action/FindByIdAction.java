package ru.job4j.tracker.action;

import ru.job4j.tracker.*;

import java.time.format.DateTimeFormatter;

public class FindByIdAction implements UserAction {
    private final Output output;

    public FindByIdAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Показать заявку по id";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        output.println("=== Вывод заявки по id ===");
        int id = input.askInt("Введите id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            String createdFormatted = item.getCreated()
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            output.println(String.format("id: %d, name %s, created: %s",
                    item.getId(), item.getName(), createdFormatted));
        } else {
            output.println("Заявка с введенным id: " + id + " не найдена.");
        }
        return true;
    }

}
