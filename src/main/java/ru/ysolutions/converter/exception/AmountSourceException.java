package ru.ysolutions.converter.exception;

public class AmountSourceException extends RuntimeException {
    public AmountSourceException() {
        super("Под источником указано несколько сумм.");
    }
}