package ru.job4j.tracker.action;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.*;

import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByIdActionTest {

    @Test
    public void whenItemFindByIdSuccessfully() {

        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("item");
        tracker.add(item);
        FindByIdAction findByIdAction = new FindByIdAction(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);
        findByIdAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                String.format(
                "=== Вывод заявки по id ===" + ln
                + "id: %d, name %s, created: %s%n",
                item.getId(), item.getName(), item.getCreated().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
        );
    }
}
