package ru.ysolutions.converter;

import ru.ysolutions.converter.converter.Converter;
import ru.ysolutions.converter.exception.CheckInputParamsException;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class ConverterMain {
    private static final List<String> fileEnds = Arrays.asList("xml", "xls");

    public static void main(String[] args) {
        try {
            final List<String> params = Arrays.asList(args.clone());
            checkInputParams(params);
            new Converter(Path.of(params.get(0)), Path.of(params.get(1))).convert();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkInputParams(List<String> params) throws CheckInputParamsException {
        try {
            if (params.isEmpty()) {
                throw new CheckInputParamsException("Ошибка. Не указаны параметры.");
            } else if (params.size() < 2) {
                throw new CheckInputParamsException("Ошибка. Не указан путь к файлу приведения.");
            } else if (params.size() > 2) {
                throw new CheckInputParamsException("Ошибка. Переданно не поддерживаемое количество параметров. Поддерживается два.");
            }
            final String fileFromEnd = params.get(0).split("\\.")[1].replace("xlsx", "xls");
            final String fileToEnd = params.get(1).split("\\.")[1].replace("xlsx", "xls");
            if (!fileEnds.contains(fileFromEnd)) {
                throw new CheckInputParamsException(String.format("Ошибка. В параметре файл конвертации указан не поддерживаемый формат файла. %s.", fileFromEnd));
            } else if (!fileEnds.contains(fileToEnd)) {
                throw new CheckInputParamsException(String.format("Ошибка. В параметре файл приведения указан не поддерживаемый формат файла. %s.", fileToEnd));
            } else if (fileFromEnd.equals(fileToEnd)) {
                throw new CheckInputParamsException(String.format("В параметрах вызова указаны файлы одинаковых типов. from: %s to: %s.", fileFromEnd, fileToEnd));
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CheckInputParamsException("Не задано расширение файла в одном из параметров.");
        }
    }
}