package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ItemAscByNameTest {

    @Test
    public void whenSortByNameAscending() {
        List<Item> items = Arrays.asList(
                new Item("Jon"),
                new Item("Pol"),
                new Item("Bob")
        );

        Collections.sort(items, new ItemAscByName());
        List<Item> expected = Arrays.asList(
                new Item("Bob"),
                new Item("Jon"),
                new Item("Pol")
        );

        assertEquals(expected, items);
    }
}


