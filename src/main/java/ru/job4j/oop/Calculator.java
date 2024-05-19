package ru.job4j.oop;

public class Calculator {

    static private int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int y) {
        return y - x;
    }

    public int divide(int y) {
        return y / x;
    }

    public int sumAllOperation(int sumValue, int multiplyValue, int minusValue, int divideValue) {
        return sum(sumValue) + multiply(multiplyValue) + minus(minusValue) + divide(divideValue);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int result = Calculator.sum(10);
        System.out.println(result);
        System.out.println(calculator.multiply(13));
        System.out.println(Calculator.minus(45));
        System.out.println(calculator.divide(100));
        System.out.println(calculator.sumAllOperation(10, 13, 45, 100));
    }
}