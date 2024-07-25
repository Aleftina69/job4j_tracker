package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDescByNameTest {
    @Test
    public void whenDescendingSortByNameAscending() {
        List<Item> items = Arrays.asList(
                new Item("Jon"),
                new Item("Pol"),
                new Item("Bob")
        );
        Collections.sort(items, new ItemDescByName());
        List<Item> expected = Arrays.asList(
                new Item("Pol"),
                new Item("Jon"),
                new Item("Bob")
        );

        assertEquals(expected, items);
    }
}