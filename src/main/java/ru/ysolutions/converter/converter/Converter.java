package ru.ysolutions.converter.converter;

import ru.ysolutions.converter.exception.ConvertException;

import java.nio.file.Path;

public class Converter {
    private final Path fileFrom;
    private final Path fileTo;

    public Converter(Path fileFrom, Path fileTo) {
        this.fileFrom = fileFrom;
        this.fileTo = fileTo;
    }

    public void convert() {
        final String fileFromEnd = fileFrom.toString().split("\\.")[1].replace("xlsx", "xls");
        final String fileToEnd = fileTo.toString().split("\\.")[1].replace("xlsx", "xls");
        if (fileFromEnd.equals("xls") && fileToEnd.equals("xml")) {
            convertXlsToXml();
        } else if (fileFromEnd.equals("xml") && fileToEnd.equals("xls")) {
            convertXmlToXls();
        } else {
            throw new ConvertException(String.format("Operation of conversation is not supported. from: %s to: %s.", fileFromEnd, fileToEnd));
        }
    }

    private void convertXmlToXls() {

    }

    private void convertXlsToXml() {

    }
}