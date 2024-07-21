package ru.job4j.queue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ReconstructPhrase {
    private final Deque<Character> descendingElements;

    private final Deque<Character> evenElements;

    public ReconstructPhrase(Deque<Character> descendingElements, Deque<Character> evenElements) {
        this.descendingElements = descendingElements;
        this.evenElements = evenElements;
    }

    private String getEvenElements() {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (Character even : evenElements) {
            if (i % 2 == 0) {
              result.append(even);
            }
            i++;
        }
        return result.toString();
    }

    private String getDescendingElements() {
        StringBuilder result = new StringBuilder();
        for (int i = descendingElements.size(); i > 0; i--) {
            result.append(descendingElements.pollLast());
        }
        return result.toString();
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}
