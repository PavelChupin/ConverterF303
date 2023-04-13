package ru.ysolutions.converter;

import jakarta.xml.bind.JAXBException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.ysolutions.converter.exception.ConvertException;
import ru.ysolutions.converter.helper.ParserHelper;
import ru.ysolutions.converter.models.xls.*;
import ru.ysolutions.converter.models.xml.Ф0409303;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Converter {
    private final Path fileFrom;
    private final Path fileTo;

    private CellStyle dateStyle;

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
        final Workbook book = getWorkBook(f303Xls);
        ParserHelper.writeIntoExcelXlsx(fileTo, book);
    }

    private Workbook getWorkBook(F303 f303Xls) {
        final Workbook book = new XSSFWorkbook();
        final Sheet sheet = book.createSheet("303_WORK");

        // Делаем стиль колонки типа Дата
        final DataFormat format = book.createDataFormat();
        this.dateStyle = book.createCellStyle();
        this.dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));

        int startContractRow;
        int maxContractRow = -1;

        for (F303Contract f303Contract : f303Xls.contracts()) {
            maxContractRow++;
            startContractRow = maxContractRow;
            int rowEnd;

            Row row = sheet.createRow(startContractRow);
            // Нумерация начинается с нуля
            //Row rowNumber = row.getSheet().getRow(startContractRow);
            // Записываем в строку клиента
            // 1
            saveValueToCellString(row, 0, f303Contract.client().name_p1_1());

            // 2
            saveValueToCellString(row, 1, f303Contract.client().ogrn_p1_2());

            // 3
            saveValueToCellString(row, 2, f303Contract.client().ogrnIp_p1_3());

            // 4
            saveValueCellDate(row, 3, f303Contract.client().ogrnDate_p1_4());

            // 5
            saveValueToCellString(row, 4, f303Contract.client().inn_p1_5());

            // 6
            saveValueToCellString(row, 5, f303Contract.client().innDescription_p1_6());

            // 7
            saveValueToCellString(row, 6, f303Contract.client().okpo_p1_7());

            // 8
            saveValueToCellString(row, 7, f303Contract.client().countryCode_p1_8());

            // 9
            saveValueToCellString(row, 8, f303Contract.client().settingKo_p1_9());

            // 10
            saveValueToCellString(row, 9, f303Contract.client().businessCode_p1_10());

            // 11/12
            rowEnd = saveClientGVZ(startContractRow, sheet, f303Contract.client().clientGVZs());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);

            // 13
            saveValueToCellString(row, 12, f303Contract.contractId_p2_13());

            // 14
            saveValueToCellString(row, 13, f303Contract.contractNumber_p2_14());

            // 15
            saveValueCellDate(row, 14, f303Contract.contractDate_p2_15());

            // 16
            saveValueToCellString(row, 15, f303Contract.numberBuyClaimRights_p2_16());

            // 17
            saveValueCellDate(row, 16, f303Contract.dateBuyClaimRights_p2_17());

            // 18
            saveValueToCellString(row, 17, f303Contract.nameOrganizationBuyClaimRights_p2_18());

            // 19
            saveValueToCellString(row, 18, f303Contract.ogrnOrganizationBuyClaimRights_p2_19());

            // 20
            saveValueToCellString(row, 19, f303Contract.regNumberOrganizationBuyClaimRights_p2_20());

            // 21
            saveValueToCellString(row, 20, f303Contract.okcmOrganizationBuyClaimRights_p2_21());

            // 22
            saveValueToCellString(row, 21, f303Contract.countRestrict_p2_22().toString());

            // 23
            saveValueToCellString(row, 22, f303Contract.informationBancrot_p2_23());

            // 24
            saveValueCellDate(row, 23, f303Contract.dateInformationBancrot_p2_24());

            // 25
            saveValueCellDate(row, 24, f303Contract.dateLastRestrict_p2_25());

            // 26/27/28/29/30/31/32
            rowEnd = saveEncumbrance(startContractRow, sheet, f303Contract.encumbrances());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);

            // 33
            saveValueToCellString(row, 32, f303Contract.kindRestrict_p2_33());

            // 34
            saveValueToCellString(row, 33, f303Contract.contractUID_p2_34());
        }

        // Меняем размер столбца
        //sheet.autoSizeColumn(1);
        return book;
    }

    // 26/27/28/29/30/31/32
    private int saveEncumbrance(int startContractRow, Sheet sheet, List<F303Encumbrance> encumbrances) {
        int number = startContractRow - 1;
        for (F303Encumbrance f303Encumbrance : encumbrances) {
            number++;
            Row rowNumber = sheet.getRow(number);
            if (rowNumber == null) {
                rowNumber = sheet.createRow(number);
            }
            // 26
            saveValueToCellString(rowNumber, 25, f303Encumbrance.p2_26());
            // 27
            saveValueToCellString(rowNumber, 26, f303Encumbrance.p2_27());
            // 28
            saveValueToCellString(rowNumber, 27, f303Encumbrance.p2_28());
            // 29
            saveValueToCellString(rowNumber, 28, f303Encumbrance.p2_29());
            // 30
            saveValueToCellString(rowNumber, 29, f303Encumbrance.p2_30());
            // 31
            saveValueToCellString(rowNumber, 30, f303Encumbrance.p2_31().toString());
            // 32
            saveValueCellDate(rowNumber, 31, f303Encumbrance.p2_32());

        }
        return number;
    }

    // Поля 11 и 12
    private int saveClientGVZ(int startContractRow, Sheet sheet, List<F303ClientGVZ> f303ClientGVZS) {
        int number = startContractRow - 1;
        for (F303ClientGVZ f303ClientGVZ : f303ClientGVZS) {
            number++;
            Row rowNumber = sheet.getRow(number);
            if (rowNumber == null) {
                rowNumber = sheet.createRow(number);
            }
            // 11
            saveValueToCellString(rowNumber, 10, f303ClientGVZ.groupNumber_p1_11());
            // 12
            saveValueToCellString(rowNumber, 11, f303ClientGVZ.groupName_p1_12());
        }
        return number;
    }

    private void saveValueToCellString(Row rowNumber, int i, String value) {
        final Cell groupNumber = rowNumber.createCell(i, CellType.STRING);
        groupNumber.setCellValue(value);
    }

    private void saveValueCellDate(Row row, int i, LocalDate valueDate) {
        final Cell ogrnDate = row.createCell(i);
        ogrnDate.setCellStyle(this.dateStyle);
        if (valueDate != null) {
            ogrnDate.setCellValue(
                    Date.from(valueDate.atStartOfDay()
                            .atZone(ZoneId.systemDefault())
                            .toInstant())
            );
        }
    }

    private void convertXlsToXml() {

    }
}