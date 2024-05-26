package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book book = new Book("Основы Java", 1000);
        Book book1 = new Book("Философия Java", 800);
        Book book2 = new Book("Алгоритмы", 750);
        Book book3 = new Book("Clean code", 500);
        Book[] books = new Book[4];
        books[0] = book;
        books[1] = book1;
        books[2] = book2;
        books[3] = book3;
        for (int index = 0; index < books.length; index++) {
            Book b = books[index];
            System.out.println(b.getName() + " - " + b.getPages());
        }
        books[0] = book3;
        books[3] = book;
        for (int index = 0; index < books.length; index++) {
            Book b = books[index];
            System.out.println(b.getName() + " - " + b.getPages());
        }
            for (int index = 0; index < books.length; index++) {
                Book b = books[index];
                if ("Clean code".equals(b.getName())) {
                    System.out.println(b.getName() + " - " + b.getPages());
                }
            }
        }
    }