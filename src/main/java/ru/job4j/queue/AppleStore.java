package ru.job4j.queue;

import java.util.Queue;

public class AppleStore {
    private final Queue<Customer> queue;

    private final int count;

    public AppleStore(Queue<Customer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    public String getLastHappyCustomer() {
        String name = null;
        for (int i = 0; i < count; i++) {
            Customer result = queue.poll();
            if (result != null) {
            name = result.name();
            }
        }
        return name;
    }

    public String getFirstUpsetCustomer() {
        String nameFirst = null;
        for (int i = 0; i < count; i++) {
            queue.poll();
        }
        Customer result = queue.peek();
        if (result != null) {
            nameFirst = result.name();
        }
        return nameFirst;
    }
}
