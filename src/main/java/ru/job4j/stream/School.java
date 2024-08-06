package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {
    public List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<Student> getGroup10A(List<Student> students) {
        Predicate<Student> group10A = student -> student.getScore() >= 70 && student.getScore() <= 100;
        return collect(students, group10A);
    }

    public List<Student> getGroup10B(List<Student> students) {
        Predicate<Student> group10B = student -> student.getScore() >= 50 && student.getScore() < 70;
        return collect(students, group10B);
    }

    public List<Student> getGroup10V(List<Student> students) {
        Predicate<Student> group10V = student -> student.getScore() > 0 && student.getScore() < 50;
        return collect(students, group10V);
    }
}