package ru.job4j.collection;

import java.util.Comparator;

public class DepartmentsDescComparator implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] lefts = left.split("/");
        String[] rights = right.split("/");
        int rslt = rights[0].compareTo(lefts[0]);
        if (rslt == 0) {
            rslt = left.compareTo(right);
        }
        return rslt;
    }
}
