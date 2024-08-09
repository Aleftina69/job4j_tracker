package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        var filterName = (Predicate<Person>) person -> person.getName().contains(key);
        var filterSurname = (Predicate<Person>) person -> person.getSurname().contains(key);
        var filterPhone = (Predicate<Person>) person -> person.getPhone().contains(key);
        var filterAddress = (Predicate<Person>) person -> person.getAddress().contains(key);
        var combine = (Predicate<Person>) filterName.or(filterSurname).or(filterPhone).or(filterAddress);
        var result = new ArrayList<Person>();
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}