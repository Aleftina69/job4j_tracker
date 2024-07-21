package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("okladno94@gmail.com", "Тишакова Алефтина");
        map.put("parsentev@yandex.ru", "Petr Arsentev");
        map.put("qwerty100@mail.ru", "Ivan Ivanov");
        map.put("asddfghj67@mail.ru", "Сергей Петров");
        map.put("okladno94@gmail.com", "Aleftina Tishakova");
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(key + " = " + value);
        }
    }
}