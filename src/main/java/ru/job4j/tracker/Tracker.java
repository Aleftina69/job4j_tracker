package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findAll() {
        Item[] result = new Item[items.length];
        int size = 0;
        for (int index = 0; index < items.length; index++) {
            Item item = items[index];
            if (item != null) {
                result[size] = item;
                size++;
            }
        }
        return Arrays.copyOf(result, size);
    }

    public Item findById(int id) {
        Item result = null;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getId() == id) {
                result = item;
                break;
            }
        }
        return result;
    }

    public Item[] findByName(String key) {
        Item[] result = new Item[size];
        int resultIndex = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].getName().equals(key)) {
                result[resultIndex] = items[i];
                resultIndex++;
            }
        }
        return Arrays.copyOf(result, resultIndex);
    }
}