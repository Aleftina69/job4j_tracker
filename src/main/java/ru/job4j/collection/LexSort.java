package ru.job4j.collection;

import java.util.Comparator;

public class LexSort  implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        String[] lefts = left.split("\\.");
        String[] rights = right.split("\\.");
        int numLeft = lefts.length > 0 ? Integer.parseInt(lefts[0]) : 0;
        int numRight = rights.length > 0 ? Integer.parseInt(rights[0]) : 0;
        return Integer.compare(numLeft, numRight);
    }
}