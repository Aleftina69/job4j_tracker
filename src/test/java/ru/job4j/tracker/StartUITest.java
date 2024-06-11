package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.action.*;

import static org.assertj.core.api.Assertions.assertThat;

class StartUITest {
    @Test
    void whenCreateItem() {
        Output output = new StubOutput();
        Input input = new MockInput(
                new String[]{"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(output),
                new ExitAction(output)
        };
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString()).isEqualTo("Меню:" + System.lineSeparator()
                + "0. Добавить новую заявку" + System.lineSeparator()
                + "1. Завершить программу" + System.lineSeparator()
                + "=== Создание новой заявки ===" + System.lineSeparator()
                + "Добавленная заявка: " + tracker.findAll()[0] + System.lineSeparator()
                + "Меню:" + System.lineSeparator()
                + "0. Добавить новую заявку" + System.lineSeparator()
                + "1. Завершить программу" + System.lineSeparator()
                + "=== Завершение программы ===" + System.lineSeparator());

    }

    @Test
    void whenReplaceItem() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input input = new MockInput(
                new String[]{"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(output),
                new ExitAction(output)
        };
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo(replacedName);
    }

    @Test
    void whenDeleteItem() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input input = new MockInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new DeleteAction(output),
                new ExitAction(output)
        };
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    void whenFindAllItem() {
        Output output = new StubOutput();
        Input input = new MockInput(
                new String[]{"0", "1"}
        );
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test1"));
        Item item2 = tracker.add(new Item("test2"));
        UserAction[] actions = {
                new FindAllAction(output),
                new ExitAction(output)
        };
        new StartUI(output).init(input, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo("Меню:" + ln
                + "0. Показать все заявки" + ln
                + "1. Завершить программу" + ln
                + "=== Вывод всех заявок ===" + ln
                + item1 + ln
                + item2 + ln
                + "Меню:" + ln
                + "0. Показать все заявки" + ln
                + "1. Завершить программу" + ln
                + "=== Завершение программы ===" + ln);
    }

    @Test
    void whenFindByNameItem() {
        Output output = new StubOutput();
        String replaceName = "test1";
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test1"));
        Input input = new MockInput(
                new String[]{"0", replaceName, "1"}
        );
        UserAction[] actions = {
                new FindByNameAction(output),
                new ExitAction(output)
        };
        new StartUI(output).init(input, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo("Меню:" + ln
                + "0. Показать заявки по имени" + ln
                + "1. Завершить программу" + ln
                + "=== Вывод заявок по имени ===" + ln
                + item1 + ln
                + "Меню:" + ln
                + "0. Показать заявки по имени" + ln
                + "1. Завершить программу" + ln
                + "=== Завершение программы ===" + ln);
    }

    @Test
    void whenFindByIdItem() {
        Output output = new StubOutput();
        int id = 1;
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item());
        Input input = new MockInput(
                new String[]{"0", String.valueOf(id), "1"}
        );
        UserAction[] actions = {
                new FindByIdAction(output),
                new ExitAction(output)
        };
        new StartUI(output).init(input, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo("Меню:" + ln
                + "0. Показать заявку по id" + ln
                + "1. Завершить программу" + ln
                + "=== Вывод заявки по id ===" + ln
                + item1 + ln
                + "Меню:" + ln
                + "0. Показать заявку по id" + ln
                + "1. Завершить программу" + ln
                + "=== Завершение программы ===" + ln);
    }

    @Test
    void whenInvalidExit() {
        Output output = new StubOutput();
        Input input = new MockInput(
                new String[] {"7", "0"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = new UserAction[]{
                new ExitAction(output)
        };
        new StartUI(output).init(input, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Завершить программу" + ln
                        + "Неверный ввод, вы можете выбрать: 0 .. 0" + ln
                        + "Меню:" + ln
                        + "0. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }
}