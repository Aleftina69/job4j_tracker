package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("Тишакова Алефтина Андреевна ");
        student.setGroup("Группа № 5 ");
        student.setAdmission(new Date(110, 8, 01));
        System.out.println(student.getName() + student.getGroup() + student.getAdmission());
    }
}