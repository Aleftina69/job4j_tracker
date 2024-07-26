package ru.job4j.collection;

import java.util.Comparator;

public class LexSort  implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] lefts = left.split("\\.");
        String[] rights = right.split("\\.");

        int length = Math.max(lefts.length, rights.length);
        for (int i = 0; i < length; i++) {
            int numLeft = i < lefts.length ? Integer.parseInt(lefts[i]) : 0;
            int numRight = i < rights.length ? Integer.parseInt(rights[i]) : 0;
            int compared = Integer.compare(numLeft, numRight);
            if (compared != 0) {
                return compared;
            }
        }
        return 0;
    }
}