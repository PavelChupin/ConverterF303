package ru.ysolutions.converter;

import org.apache.poi.ss.usermodel.*;
import ru.ysolutions.converter.models.xls.F303;
import ru.ysolutions.converter.models.xls.F303ClientGVZ;
import ru.ysolutions.converter.models.xls.F303Contract;
import ru.ysolutions.converter.models.xls.F303Encumbrance;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConverterExcel {
    private final Workbook workBook;
    private final CellStyle dateStyle;
    private final CellStyle headerNumberStyle;
    private final CellStyle stringStyle;
    private final CellStyle headerColumnName;
    private final CellStyle headerColumnPatr;
    private final CellStyle headerPatr;

    public static final int countColumn = 114;

    public ConverterExcel(Workbook workBook) {
        this.workBook = workBook;

        // Делаем стиль колонки типа Дата
        final DataFormat format = workBook.createDataFormat();
        this.dateStyle = workBook.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));

        // Делаем стиль для колонки нумератора
        this.headerNumberStyle = workBook.createCellStyle();
        this.headerNumberStyle.setAlignment(HorizontalAlignment.CENTER);
        this.headerNumberStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // Шрифт для колонки нумератора
        final Font font = workBook.createFont();
        font.setColor(Font.COLOR_RED);
        this.headerNumberStyle.setFont(font);

        // Стиль данных текстовых
        this.stringStyle = workBook.createCellStyle();

        // Стиль для колонки наименования полей
        this.headerColumnName = workBook.createCellStyle();
        this.headerColumnName.setBorderTop(BorderStyle.THICK);
        this.headerColumnName.setBorderBottom(BorderStyle.MEDIUM);
        this.headerColumnName.setBorderLeft(BorderStyle.MEDIUM);
        this.headerColumnName.setBorderRight(BorderStyle.MEDIUM);
        this.headerColumnName.setWrapText(true);

        // Стиль для колонки нумератора колонки в разделе
        this.headerColumnPatr = workBook.createCellStyle();
        this.headerColumnPatr.setBorderTop(BorderStyle.MEDIUM);
        this.headerColumnPatr.setBorderBottom(BorderStyle.THICK);
        this.headerColumnPatr.setBorderLeft(BorderStyle.MEDIUM);
        this.headerColumnPatr.setBorderRight(BorderStyle.MEDIUM);
        this.headerColumnPatr.setAlignment(HorizontalAlignment.CENTER);
        this.headerColumnPatr.setVerticalAlignment(VerticalAlignment.CENTER);
        // Шрифт для нумератора колонки в разделе
        final Font fontColumnPatr = workBook.createFont();
        fontColumnPatr.setBold(true);
        this.headerColumnPatr.setFont(fontColumnPatr);


        // Делаем стиль для колонки нумератора
        this.headerPatr = workBook.createCellStyle();

        // Шрифт для колонки нумератора
        final Font fontPatr = workBook.createFont();
        fontPatr.setBold(true);
        this.headerPatr.setFont(fontPatr);
    }

    public Workbook getWorkBook(F303 f303Xls) {
        final Sheet sheet = this.workBook.createSheet("303_WORK");

        // Формируем шапку отчета
        int endHeaderRow = saveHeaderReport(sheet, f303Xls.reportDate());

        int startContractRow = endHeaderRow + 1;
        int maxContractRow = startContractRow - 1;

        for (F303Contract f303Contract : f303Xls.contracts()) {
            maxContractRow++;
            startContractRow = maxContractRow;
            int rowEnd;

            Row row = sheet.createRow(startContractRow);
            // Нумерация начинается с нуля
            //Row rowNumber = row.getSheet().getRow(startContractRow);
            // Записываем в строку клиента
            // 1
            saveValueToCellString(row, 0, f303Contract.client().name_p1_1(), this.stringStyle);

            // 2
            saveValueToCellString(row, 1, f303Contract.client().ogrn_p1_2(), this.stringStyle);

            // 3
            saveValueToCellString(row, 2, f303Contract.client().ogrnIp_p1_3(), this.stringStyle);

            // 4
            saveValueToCellDate(row, 3, f303Contract.client().ogrnDate_p1_4(), this.dateStyle);

            // 5
            saveValueToCellString(row, 4, f303Contract.client().inn_p1_5(), this.stringStyle);

            // 6
            saveValueToCellString(row, 5, f303Contract.client().innDescription_p1_6(), this.stringStyle);

            // 7
            saveValueToCellString(row, 6, f303Contract.client().okpo_p1_7(), this.stringStyle);

            // 8
            saveValueToCellString(row, 7, f303Contract.client().countryCode_p1_8(), this.stringStyle);

            // 9
            saveValueToCellString(row, 8, f303Contract.client().settingKo_p1_9(), this.stringStyle);

            // 10
            saveValueToCellString(row, 9, f303Contract.client().businessCode_p1_10(), this.stringStyle);

            // 11/12
            rowEnd = saveClientGVZ(startContractRow, sheet, f303Contract.client().clientGVZs());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);

            // 13
            saveValueToCellString(row, 12, f303Contract.contractId_p2_13(), this.stringStyle);

            // 14
            saveValueToCellString(row, 13, f303Contract.contractNumber_p2_14(), this.stringStyle);

            // 15
            saveValueToCellDate(row, 14, f303Contract.contractDate_p2_15(), this.dateStyle);

            // 16
            saveValueToCellString(row, 15, f303Contract.numberBuyClaimRights_p2_16(), this.stringStyle);

            // 17
            saveValueToCellDate(row, 16, f303Contract.dateBuyClaimRights_p2_17(), this.dateStyle);

            // 18
            saveValueToCellString(row, 17, f303Contract.nameOrganizationBuyClaimRights_p2_18(), this.stringStyle);

            // 19
            saveValueToCellString(row, 18, f303Contract.ogrnOrganizationBuyClaimRights_p2_19(), this.stringStyle);

            // 20
            saveValueToCellString(row, 19, f303Contract.regNumberOrganizationBuyClaimRights_p2_20(), this.stringStyle);

            // 21
            saveValueToCellString(row, 20, f303Contract.okcmOrganizationBuyClaimRights_p2_21(), this.stringStyle);

            // 22
            saveValueToCellString(row, 21, f303Contract.countRestrict_p2_22().toString(), this.stringStyle);

            // 23
            saveValueToCellString(row, 22, f303Contract.informationBancrot_p2_23(), this.stringStyle);

            // 24
            saveValueToCellDate(row, 23, f303Contract.dateInformationBancrot_p2_24(), this.dateStyle);

            // 25
            saveValueToCellDate(row, 24, f303Contract.dateLastRestrict_p2_25(), this.dateStyle);

            // 26/27/28/29/30/31/32
            rowEnd = saveEncumbrance(startContractRow, sheet, f303Contract.encumbrances());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);

            // 33
            saveValueToCellString(row, 32, f303Contract.kindRestrict_p2_33(), this.stringStyle);

            // 34
            saveValueToCellString(row, 33, f303Contract.contractUID_p2_34(), this.stringStyle);
        }

        // Меняем размер столбцов
//        for (int i = 0; i < countColumn; i++) {
//            sheet.autoSizeColumn(i);
//        }
        return this.workBook;
    }

    private int saveHeaderReport(Sheet sheet, LocalDate reportDate) {
        // Первая строка
        Row row = sheet.createRow(0);
        saveValueToCellString(row, 0, "Форма 0409303 (часть 1)", this.stringStyle);
        saveValueToCellDate(row, 1, reportDate, this.dateStyle);

        // Номера строк
        row = sheet.createRow(1);
        for (int i = 0; i < countColumn; i++) {
            saveValueToCellString(row, i, String.valueOf((i + 1)), this.headerNumberStyle);
        }

        // Наименования разделов
        row = sheet.createRow(2);
        final Row finalRow = row;
        ExcelHeader.parts.keySet().forEach(key -> saveValueToCellString(finalRow, key, ExcelHeader.parts.get(key), this.headerPatr));

        // Наименование столбцов
        row = sheet.createRow(3);
        final Row finalRow1 = row;
        ExcelHeader.columnNames.keySet().forEach(key -> saveValueToCellString(finalRow1, key, ExcelHeader.columnNames.get(key), this.headerColumnName));

        // Нумерация в подразделах
        row = sheet.createRow(4);
        final Row finalRow2 = row;
        ExcelHeader.columnPart.keySet().forEach(key -> saveValueToCellString(finalRow2, key, ExcelHeader.columnPart.get(key), this.headerColumnPatr));

        return row.getRowNum();
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
            saveValueToCellString(rowNumber, 25, f303Encumbrance.p2_26(), this.stringStyle);
            // 27
            saveValueToCellString(rowNumber, 26, f303Encumbrance.p2_27(), this.stringStyle);
            // 28
            saveValueToCellString(rowNumber, 27, f303Encumbrance.p2_28(), this.stringStyle);
            // 29
            saveValueToCellString(rowNumber, 28, f303Encumbrance.p2_29(), this.stringStyle);
            // 30
            saveValueToCellString(rowNumber, 29, f303Encumbrance.p2_30(), this.stringStyle);
            // 31
            saveValueToCellString(rowNumber, 30, f303Encumbrance.p2_31().toString(), this.stringStyle);
            // 32
            saveValueToCellDate(rowNumber, 31, f303Encumbrance.p2_32(), this.dateStyle);

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
            saveValueToCellString(rowNumber, 10, f303ClientGVZ.groupNumber_p1_11(), this.stringStyle);
            // 12
            saveValueToCellString(rowNumber, 11, f303ClientGVZ.groupName_p1_12(), this.stringStyle);
        }
        return number;
    }

    private void saveValueToCellString(Row rowNumber, int i, String value, CellStyle cellStyle) {
        final Cell cell = rowNumber.createCell(i, CellType.STRING);
        cell.setCellValue(value);
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
    }

    private void saveValueToCellDate(Row row, int i, LocalDate valueDate, CellStyle cellStyle) {
        final Cell cell = row.createCell(i);
        cell.setCellStyle(cellStyle);
        if (valueDate != null) {
            cell.setCellValue(
                    Date.from(valueDate.atStartOfDay()
                            .atZone(ZoneId.systemDefault())
                            .toInstant())
            );
        }
    }
}
