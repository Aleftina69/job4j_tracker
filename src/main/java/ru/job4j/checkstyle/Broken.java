package ru.job4j.checkstyle;

public class Broken {
    private static final String NEW_VALUE = "";
    private int sizeOfEmpty = 10;
    private String surname;
    private String name;

    Broken() { }

    void echo() { }

    void media(Object obj) {
        if (obj != null) {
            System.out.println(obj);
        }
    }

    void method(int a, int b, int c) { }
}