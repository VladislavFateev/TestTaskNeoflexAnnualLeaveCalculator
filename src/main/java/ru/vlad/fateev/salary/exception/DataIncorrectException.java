package ru.vlad.fateev.salary.exception;

public class DataIncorrectException extends RuntimeException {
    public DataIncorrectException() {
        super("data is incorrect!");
    }
}
