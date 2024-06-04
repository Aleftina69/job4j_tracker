package ru.job4j.cast;

    public class Airplane implements Vehicle {
        @Override
        public void move() {
            System.out.println("Самолет летит по воздуху");
        }

        @Override
        public void passengers() {
            System.out.println("В самолете 200 пассажиров");
        }
    }
