package ru.ysolutions.converter;

import jakarta.xml.bind.JAXBException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.ysolutions.converter.exception.ConvertException;
import ru.ysolutions.converter.helper.ParserHelper;
import ru.ysolutions.converter.models.xls.f303.F303;
import ru.ysolutions.converter.models.xls.f303.FactoryF303;
import ru.ysolutions.converter.models.xml.FactoryF303Xml;
import ru.ysolutions.converter.models.xml.Ф0409303;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class Converter {
    private final Path fileFrom;
    private final Path fileTo;

    public Converter(Path fileFrom, Path fileTo) {
        this.fileFrom = fileFrom;
        this.fileTo = fileTo;
    }

    public void convert() throws JAXBException, IOException, DatatypeConfigurationException,XMLStreamException {
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

    private void convertXmlToXls() throws JAXBException, IOException {
        final Ф0409303 f303Xml = ParserHelper.getObjectFromXMLFile(fileFrom);
        final F303 f303Xls = FactoryF303.getF303ByXmlData(f303Xml);
        final Workbook book = new ConverterExcel(new XSSFWorkbook()).getWorkBook(f303Xls);
        book.write(new FileOutputStream(fileTo.toFile()));
        book.close();
    }

    private void convertXlsToXml() throws IOException, JAXBException,XMLStreamException {
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(fileFrom.toFile()))) {
            final F303 f303Xls = new ConverterXml(workbook).getDataFromXls();
            final Ф0409303 f303Xml = FactoryF303Xml.getDataXmlByF303(f303Xls);
            ParserHelper.saveObjectToXMLFile(fileTo, f303Xml);
        }
    }
}