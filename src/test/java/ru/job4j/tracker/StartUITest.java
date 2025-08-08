package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.action.*;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StartUITest {
    @Test
    void whenCreateItem() {
        Output output = new StubOutput();
        Input input = new MockInput(
                new String[]{"0", "Item name", "1"}
        );
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = Arrays.asList(
                new CreateAction(output),
                new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
        Item item = tracker.findAll().get(0);
        assertThat(output.toString()).isEqualTo("Меню:" + System.lineSeparator()
                + "0. Добавить новую заявку" + System.lineSeparator()
                + "1. Завершить программу" + System.lineSeparator()
                + "=== Создание новой заявки ===" + System.lineSeparator()
                + "Добавленная заявка: " + item + System.lineSeparator()
                + "Меню:" + System.lineSeparator()
                + "0. Добавить новую заявку" + System.lineSeparator()
                + "1. Завершить программу" + System.lineSeparator()
                + "=== Завершение программы ===" + System.lineSeparator());

    }

    @Test
    void whenReplaceItem() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input input = new MockInput(
                new String[]{"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new ReplaceAction(output),
                new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo(replacedName);
    }

    @Test
    void whenDeleteItem() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input input = new MockInput(
                new String[]{"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new DeleteAction(output),
                new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    void whenFindAllItem() {
        Output output = new StubOutput();
        Input input = new MockInput(
                new String[]{"0", "1"}
        );
        MemTracker tracker = new MemTracker();
        Item item1 = tracker.add(new Item("test1"));
        Item item2 = tracker.add(new Item("test2"));
        List<UserAction> actions = Arrays.asList(
                new FindAllAction(output),
                new ExitAction(output));
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
        MemTracker tracker = new MemTracker();
        Item item1 = tracker.add(new Item("test1"));
        Input input = new MockInput(
                new String[]{"0", replaceName, "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new FindByNameAction(output),
                new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                String.format("Меню:" + ln
                                + "0. Показать заявки по имени" + ln
                                + "1. Завершить программу" + ln
                                + "=== Вывод заявок по имени ===" + ln
                                + "id: %d, name %s, created: %s%n",
                        item1.getId(), item1.getName(), item1.getCreated().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                        + "Меню:" + ln
                        + "0. Показать заявки по имени" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln);
    }

    @Test
    void whenFindByIdItem() {
        Output output = new StubOutput();
        int id = 1;
        MemTracker tracker = new MemTracker();
        Item item1 = tracker.add(new Item());
        Input input = new MockInput(
                new String[]{"0", String.valueOf(id), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new FindByIdAction(output),
                new ExitAction(output));
        new StartUI(output).init(input, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                String.format("Меню:" + ln
                                + "0. Показать заявку по id" + ln
                                + "1. Завершить программу" + ln
                                + "=== Вывод заявки по id ===" + ln
                                + "id: %d, name %s, created: %s%n",
                        item1.getId(), item1.getName(), item1.getCreated().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                        + "Меню:" + ln
                        + "0. Показать заявку по id" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln);
    }

    @Test
    void whenInvalidExit() {
        Output output = new StubOutput();
        Input input = new MockInput(
                new String[]{"7", "0"}
        );
        MemTracker tracker = new MemTracker();
        List<UserAction> actions = Arrays.asList(
                new ExitAction(output));
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