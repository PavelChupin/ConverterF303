package ru.ysolutions.converter;

import jakarta.xml.bind.JAXBException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.ysolutions.converter.exception.ConvertException;
import ru.ysolutions.converter.helper.ParserHelper;
import ru.ysolutions.converter.models.xls.f303.F303;
import ru.ysolutions.converter.models.xls.f303.FactoryF303;
import ru.ysolutions.converter.models.xml.Ф0409303;

import java.io.IOException;
import java.nio.file.Path;

public class Converter {
    private final Path fileFrom;
    private final Path fileTo;

    public Converter(Path fileFrom, Path fileTo) {
        this.fileFrom = fileFrom;
        this.fileTo = fileTo;
    }

    public void convert() throws JAXBException, IOException {
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
        ParserHelper.writeIntoExcelXlsx(fileTo, book);
    }


    private void convertXlsToXml() {

    }
}