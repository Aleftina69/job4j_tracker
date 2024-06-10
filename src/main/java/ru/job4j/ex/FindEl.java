package ru.job4j.ex;

public class FindEl extends Exception {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        for (int i = 0; i < value.length; i++) {
            if (value[i].equals(key)) {
                return i;
            }
        }
        throw new ElementNotFoundException("Element not found");
    }

    public static void main(String[] args) {
        String[] value = {"Привет, ", "как ", "дела?"};
        try {
            System.out.println(indexOf(value, "как "));
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}