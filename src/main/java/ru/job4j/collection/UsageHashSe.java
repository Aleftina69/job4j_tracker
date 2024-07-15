package ru.job4j.collection;

import java.util.HashSet;

public class UsageHashSe {
    public static void main(String[] args) {
        HashSet<String> autos = new HashSet<>();
        autos.add("Lada");
        autos.add("BMW");
        autos.add("Volvo");
        autos.add("Toyota");
        for (String brand : autos) {
            System.out.println(brand);
        }
    }
}
