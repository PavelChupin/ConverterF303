package ru.ysolutions.converter;

import jakarta.xml.bind.JAXBException;
import ru.ysolutions.converter.exception.ConvertException;
import ru.ysolutions.converter.helper.ParserHelper;
import ru.ysolutions.converter.models.xml.Ф0409303;

import java.nio.file.Path;

public class Converter {
    private final Path fileFrom;
    private final Path fileTo;

    public Converter(Path fileFrom, Path fileTo) {
        this.fileFrom = fileFrom;
        this.fileTo = fileTo;
    }

    public void convert() throws JAXBException {
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

    private void convertXmlToXls() throws JAXBException {
        final Ф0409303 f303 = ParserHelper.getObjectFromXMLFile(fileFrom);
        System.out.println();
    }

    private void convertXlsToXml() {

    }
}