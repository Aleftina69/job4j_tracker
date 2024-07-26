package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int minLenght = Math.min(left.length(), right.length());
        for (int i = 0; i < minLenght; i++) {
            char strLeft = left.charAt(i);
            char strRight = right.charAt(i);
            if (strLeft != strRight) {
                return Character.compare(strLeft, strRight);
            }
        }
        return Integer.compare(left.length(), right.length());
    }
}
