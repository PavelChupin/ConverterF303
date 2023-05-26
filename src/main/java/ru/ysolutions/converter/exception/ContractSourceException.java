package ru.ysolutions.converter.exception;

public class ContractSourceException extends RuntimeException {
    public ContractSourceException() {
        super("Под источником указано несколько договоров");
    }
}
