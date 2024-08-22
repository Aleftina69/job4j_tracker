package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> filterName = person -> person.getName().contains(key);
        Predicate<Person> filterSurname = person -> person.getSurname().contains(key);
        Predicate<Person> filterPhone = person -> person.getPhone().contains(key);
        Predicate<Person> filterAddress = person -> person.getAddress().contains(key);
        Predicate<Person> combine = filterName.or(filterSurname).or(filterPhone).or(filterAddress);
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}