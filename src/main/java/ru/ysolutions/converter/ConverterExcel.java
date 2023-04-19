package ru.ysolutions.converter;

import org.apache.poi.ss.usermodel.*;
import ru.ysolutions.converter.models.xls.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
            saveValueToCellString(row, 0, f303Contract.client_p1().name_p1(), this.stringStyle);

            // 2
            saveValueToCellString(row, 1, f303Contract.client_p1().ogrn_p2(), this.stringStyle);

            // 3
            saveValueToCellString(row, 2, f303Contract.client_p1().ogrnIp_p3(), this.stringStyle);

            // 4
            saveValueToCellDate(row, 3, f303Contract.client_p1().ogrnDate_p4(), this.dateStyle);

            // 5
            saveValueToCellString(row, 4, f303Contract.client_p1().inn_p5(), this.stringStyle);

            // 6
            saveValueToCellString(row, 5, f303Contract.client_p1().innDescription_p6(), this.stringStyle);

            // 7
            saveValueToCellString(row, 6, f303Contract.client_p1().okpo_p7(), this.stringStyle);

            // 8
            saveValueToCellString(row, 7, f303Contract.client_p1().countryCode_p8(), this.stringStyle);

            // 9
            saveValueToCellString(row, 8, f303Contract.client_p1().settingKo_p9(), this.stringStyle);

            // 10
            saveValueToCellString(row, 9, f303Contract.client_p1().businessCode_p10(), this.stringStyle);

            // 11/12
            rowEnd = saveClientGVZ(startContractRow, sheet, f303Contract.client_p1().clientGVZs());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);

            // 13
            saveValueToCellString(row, 12, f303Contract.contractId_p13(), this.stringStyle);

            // 14
            saveValueToCellString(row, 13, f303Contract.contractNumber_p14(), this.stringStyle);

            // 15
            saveValueToCellDate(row, 14, f303Contract.contractDate_p15(), this.dateStyle);

            // 16
            saveValueToCellString(row, 15, f303Contract.numberBuyClaimRights_p16(), this.stringStyle);

            // 17
            saveValueToCellDate(row, 16, f303Contract.dateBuyClaimRights_p17(), this.dateStyle);

            // 18
            saveValueToCellString(row, 17, f303Contract.nameOrganizationBuyClaimRights_p18(), this.stringStyle);

            // 19
            saveValueToCellString(row, 18, f303Contract.ogrnOrganizationBuyClaimRights_p19(), this.stringStyle);

            // 20
            saveValueToCellString(row, 19, f303Contract.regNumberOrganizationBuyClaimRights_p20(), this.stringStyle);

            // 21
            saveValueToCellString(row, 20, f303Contract.okcmOrganizationBuyClaimRights_p21(), this.stringStyle);

            // 22
            saveValueToBigInteger(row, 21, f303Contract.countRestrict_p22(), this.stringStyle);

            // 23
            saveValueToCellString(row, 22, f303Contract.informationBancrot_p23(), this.stringStyle);

            // 24
            saveValueToCellDate(row, 23, f303Contract.dateInformationBancrot_24(), this.dateStyle);

            // 25
            saveValueToCellDate(row, 24, f303Contract.dateLastRestrict_p25(), this.dateStyle);

            // 26/27/28/29/30/31/32
            rowEnd = saveEncumbrance(startContractRow, sheet, f303Contract.encumbrances());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);

            // 33
            saveValueToCellString(row, 32, f303Contract.kindRestrict_p33(), this.stringStyle);

            // 34
            saveValueToCellString(row, 33, f303Contract.contractUID_p34(), this.stringStyle);

            // 35
            saveValueToCellString(row, 34, f303Contract.conditions().p35(), this.stringStyle);

            // 36
            saveValueToCellString(row, 35, f303Contract.conditions().p36(), this.stringStyle);

            // 37
            saveValueToCellBigDecimal(row, 36, f303Contract.conditions().p37(), this.stringStyle);

            // 38
            saveValueToCellBigDecimal(row, 37, f303Contract.conditions().p38(), this.stringStyle);

            // 39
            saveValueToCellString(row, 38, f303Contract.conditions().p39(), this.stringStyle);

            // 40
            saveValueToCellString(row, 39, f303Contract.conditions().p40(), this.stringStyle);

            // 41
            saveValueToCellDate(row, 40, f303Contract.conditions().p41(), this.dateStyle);

            // 42
            saveValueToCellDate(row, 41, f303Contract.conditions().p42(), this.dateStyle);

            // 43
            saveValueToCellString(row, 42, f303Contract.conditions().p43(), this.stringStyle);

            // 44
            saveValueToCellBigDecimal(row, 43, f303Contract.conditions().p44(), this.stringStyle);

            // 45
            saveValueToCellBigDecimal(row, 44, f303Contract.conditions().p45(), this.stringStyle);

            // 46
            saveValueToCellBigDecimal(row, 45, f303Contract.conditions().p46(), this.stringStyle);

            // 47
            saveValueToBigInteger(row, 46, f303Contract.conditions().p47(), this.stringStyle);

            // 48
            saveValueToCellString(row, 47, f303Contract.conditions().p48(), this.stringStyle);

            // 49/50/51
            rowEnd = saveConditionsCode(startContractRow, sheet, f303Contract.conditions().conditionsCodes());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);

            // 52
            saveValueToCellString(row, 51, f303Contract.conditions().p52(), this.stringStyle);

            // 53
            saveValueToCellString(row, 52, f303Contract.conditions().p53(), this.stringStyle);

            // 54
            saveValueToCellBigDecimal(row, 53, f303Contract.conditions().p54(), this.stringStyle);

            // 55
            saveValueToCellBigDecimal(row, 54, f303Contract.conditions().p55(), this.stringStyle);

            // 56/57/58/59/60/61
            rowEnd = saveWarranty(startContractRow, sheet, f303Contract.warranties());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);

            // 62
            saveValueToCellDate(row, 61, f303Contract.p62(), this.dateStyle);
            // 63
            saveValueToCellString(row, 62, f303Contract.p63(), this.stringStyle);
            // 64
            saveValueToCellBigDecimal(row, 63, f303Contract.p64(), this.stringStyle);
            // 65
            saveValueToCellString(row, 64, f303Contract.p65(), this.stringStyle);
            // 66
            saveValueToCellString(row, 65, f303Contract.p66(), this.stringStyle);
            // 67
            saveValueToCellBigDecimal(row, 66, f303Contract.p67(), this.stringStyle);
            // 68
            saveValueToCellString(row, 67, f303Contract.p68(), this.stringStyle);
            // 69
            saveValueToCellBigDecimal(row, 68, f303Contract.p69(), this.stringStyle);
            // 70
            saveValueToCellString(row, 69, f303Contract.p70(), this.stringStyle);
            // 71
            saveValueToCellString(row, 70, f303Contract.p71(), this.stringStyle);
            // 72
            saveValueToCellString(row, 71, f303Contract.p72(), this.stringStyle);
            // 73
            saveValueToCellBigDecimal(row, 72, f303Contract.p73(), this.stringStyle);
            // 74
            saveValueToCellBigDecimal(row, 73, f303Contract.p74(), this.stringStyle);
            // 75
            saveValueToCellString(row, 74, f303Contract.p75(), this.stringStyle);
            // 76
            saveValueToCellString(row, 75, f303Contract.p76(), this.stringStyle);
            // 77
            saveValueToCellBigDecimal(row, 76, f303Contract.p77(), this.stringStyle);
            // 78
            saveValueToCellBigDecimal(row, 77, f303Contract.p78(), this.stringStyle);
            // 79
            saveValueToCellBigDecimal(row, 78, f303Contract.p79(), this.stringStyle);
            // 80
            saveValueToCellString(row, 79, f303Contract.p80(), this.stringStyle);
            // 81
            saveValueToCellString(row, 80, f303Contract.p81(), this.stringStyle);
            // 82
            saveValueToCellString(row, 81, f303Contract.p82(), this.stringStyle);
            // 83
            saveValueToCellString(row, 82, f303Contract.p83(), this.stringStyle);
            // 84
            saveValueToCellString(row, 83, f303Contract.p84(), this.stringStyle);
            // 85
            saveValueToCellBigDecimal(row, 84, f303Contract.p85(), this.stringStyle);
            // 86
            saveValueToCellBigDecimal(row, 85, f303Contract.p86(), this.stringStyle);
            // 87
            saveValueToCellBigDecimal(row, 86, f303Contract.p87(), this.stringStyle);




            // 88
            saveValueToCellBigDecimal(row, 87, f303Contract.p88(), this.stringStyle);
            // 89
            saveValueToCellBigDecimal(row, 88, f303Contract.p89(), this.stringStyle);
            // 90
            saveValueToCellBigDecimal(row, 89, f303Contract.p90(), this.stringStyle);
            // 91
            saveValueToCellBigDecimal(row, 90, f303Contract.p91(), this.stringStyle);





            // 106/107/108/109/110/111/112/113/114
            rowEnd = saveP10(startContractRow, sheet, f303Contract.p10());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);


            // Транши
            // 62/63/64/65/66/67/68/69/70/71/72/73/74/75/76/77/78/79/80/81/82/83/84/85/86/87
            rowEnd = saveTranches(maxContractRow, sheet, f303Contract.tranches());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);


        }

        // Меняем размер столбцов
//        for (int i = 0; i < countColumn; i++) {
//            sheet.autoSizeColumn(i);
//        }
        return this.workBook;
    }

    // 106/107/108/109/110/111/112/113/114
    private int saveP10(int startContractRow, Sheet sheet, List<F303p10> p10) {
        int number = startContractRow - 1;
        for (F303p10 p : p10) {
            number++;
            Row rowNumber = sheet.getRow(number);
            if (rowNumber == null) {
                rowNumber = sheet.createRow(number);
            }
            // 106
            saveValueToCellString(rowNumber, 105, p.p106(), this.stringStyle);
            // 107
            saveValueToCellBigDecimal(rowNumber, 106, p.p107(), this.stringStyle);
            // 108
            saveValueToCellBigDecimal(rowNumber, 107, p.p108(), this.stringStyle);
            // 109
            saveValueToCellString(rowNumber, 108, p.p109(), this.stringStyle);
            // 110
            saveValueToCellString(rowNumber, 109, p.p110(), this.stringStyle);
            // 111
            saveValueToCellString(rowNumber, 110, p.p111(), this.stringStyle);
            // 112
            saveValueToCellString(rowNumber, 111, p.p112(), this.stringStyle);
            // 113
            saveValueToCellBigDecimal(rowNumber, 112, p.p113(), this.stringStyle);
            // 114
            saveValueToCellString(rowNumber, 113, p.p114(), this.stringStyle);
        }
        return number;
    }

    // 62/63/64/65/66/67/68/69/70
    private int saveTranches(int maxContractRow, Sheet sheet, List<F303Tranche> tranches) {
        int number = maxContractRow;
        for (F303Tranche tranche : tranches) {
            number++;
            Row rowNumber = sheet.getRow(number);
            if (rowNumber == null) {
                rowNumber = sheet.createRow(number);
            }
            // 62
            saveValueToCellDate(rowNumber, 61, tranche.p62(), this.dateStyle);
            // 63
            saveValueToCellString(rowNumber, 62, tranche.p63(), this.stringStyle);
            // 64
            saveValueToCellBigDecimal(rowNumber, 63, tranche.p64(), this.stringStyle);
            // 65
            saveValueToCellString(rowNumber, 64, tranche.p65(), this.stringStyle);
            // 66
            saveValueToCellString(rowNumber, 65, tranche.p66(), this.stringStyle);
            // 67
            saveValueToCellBigDecimal(rowNumber, 66, tranche.p67(), this.stringStyle);
            // 68
            saveValueToCellString(rowNumber, 67, tranche.p68(), this.stringStyle);
            // 69
            saveValueToCellBigDecimal(rowNumber, 68, tranche.p69(), this.stringStyle);
            // 70
            saveValueToCellString(rowNumber, 69, tranche.p70(), this.stringStyle);
            // 71
            saveValueToCellString(rowNumber, 70, tranche.p71(), this.stringStyle);
            // 72
            saveValueToCellString(rowNumber, 71, tranche.p72(), this.stringStyle);
            // 73
            saveValueToCellBigDecimal(rowNumber, 72, tranche.p73(), this.stringStyle);
            // 74
            saveValueToCellBigDecimal(rowNumber, 73, tranche.p74(), this.stringStyle);
            // 75
            saveValueToCellString(rowNumber, 74, tranche.p75(), this.stringStyle);
            // 76
            saveValueToCellString(rowNumber, 75, tranche.p76(), this.stringStyle);
            // 77
            saveValueToCellBigDecimal(rowNumber, 76, tranche.p77(), this.stringStyle);
            // 78
            saveValueToCellBigDecimal(rowNumber, 77, tranche.p78(), this.stringStyle);
            // 79
            saveValueToCellBigDecimal(rowNumber, 78, tranche.p79(), this.stringStyle);
            // 80
            saveValueToCellString(rowNumber, 79, tranche.p80(), this.stringStyle);
            // 81
            saveValueToCellString(rowNumber, 80, tranche.p81(), this.stringStyle);
            // 82
            saveValueToCellString(rowNumber, 81, tranche.p82(), this.stringStyle);
            // 83
            saveValueToCellString(rowNumber, 82, tranche.p83(), this.stringStyle);
            // 84
            saveValueToCellString(rowNumber, 83, tranche.p84(), this.stringStyle);
            // 85
            saveValueToCellBigDecimal(rowNumber, 84, tranche.p85(), this.stringStyle);
            // 86
            saveValueToCellBigDecimal(rowNumber, 85, tranche.p86(), this.stringStyle);
            // 87
            saveValueToCellBigDecimal(rowNumber, 86, tranche.p87(), this.stringStyle);
        }
        return number;
    }

    // 56/57/58/59/60/61
    private int saveWarranty(int startContractRow, Sheet sheet, List<F303Warranty> warranties) {
        int number = startContractRow - 1;
        for (F303Warranty warranty : warranties) {
            number++;
            Row rowNumber = sheet.getRow(number);
            if (rowNumber == null) {
                rowNumber = sheet.createRow(number);
            }
            // 56
            saveValueToCellString(rowNumber, 55, warranty.p56(), this.stringStyle);
            // 57
            saveValueToCellBigDecimal(rowNumber, 56, warranty.p57(), this.stringStyle);
            // 58
            saveValueToCellDate(rowNumber, 57, warranty.p58(), this.dateStyle);
            // 59
            saveValueToCellBigDecimal(rowNumber, 58, warranty.p59(), this.stringStyle);
            // 60
            saveValueToCellBigDecimal(rowNumber, 59, warranty.p60(), this.stringStyle);
            // 61
            saveValueToCellBigDecimal(rowNumber, 60, warranty.p61(), this.stringStyle);
        }
        return number;
    }

    // Поля 49 50 51
    private int saveConditionsCode(int startContractRow, Sheet sheet, List<F303ConditionsCode> f303ConditionsCodes) {
        int number = startContractRow - 1;
        for (F303ConditionsCode f303ConditionsCode : f303ConditionsCodes) {
            number++;
            Row rowNumber = sheet.getRow(number);
            if (rowNumber == null) {
                rowNumber = sheet.createRow(number);
            }
            // 49
            saveValueToCellString(rowNumber, 48, f303ConditionsCode.p49(), this.stringStyle);
            // 50/51
            int maxContractRow = saveConditionsCodeCond(number, sheet, f303ConditionsCode.conditionsCodeConds());
            if (number < maxContractRow) {
                number = maxContractRow;
            }
        }
        return number;
    }

    // Поля 50 51
    private int saveConditionsCodeCond(int startContractRow, Sheet sheet, List<F303ConditionsCodeCond> f303ConditionsCodeConds) {
        int number = startContractRow - 1;
        for (F303ConditionsCodeCond f303ConditionsCodeCond : f303ConditionsCodeConds) {
            number++;
            Row rowNumber = sheet.getRow(number);
            if (rowNumber == null) {
                rowNumber = sheet.createRow(number);
            }
            // 50
            saveValueToCellString(rowNumber, 49, f303ConditionsCodeCond.p50(), this.stringStyle);
            // 51
            saveValueToCellString(rowNumber, 50, f303ConditionsCodeCond.p51(), this.stringStyle);

        }
        return number;
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
            saveValueToCellString(rowNumber, 25, f303Encumbrance.p26(), this.stringStyle);
            // 27
            saveValueToCellString(rowNumber, 26, f303Encumbrance.p27(), this.stringStyle);
            // 28
            saveValueToCellString(rowNumber, 27, f303Encumbrance.p28(), this.stringStyle);
            // 29
            saveValueToCellString(rowNumber, 28, f303Encumbrance.p29(), this.stringStyle);
            // 30
            saveValueToCellString(rowNumber, 29, f303Encumbrance.p30(), this.stringStyle);
            // 31
            saveValueToCellBigDecimal(rowNumber, 30, f303Encumbrance.p31(), this.stringStyle);
            // 32
            saveValueToCellDate(rowNumber, 31, f303Encumbrance.p32(), this.dateStyle);
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
            saveValueToCellString(rowNumber, 10, f303ClientGVZ.groupNumber_p11(), this.stringStyle);
            // 12
            saveValueToCellString(rowNumber, 11, f303ClientGVZ.groupName_p12(), this.stringStyle);
        }
        return number;
    }

    private void saveValueToCellBigDecimal(Row rowNumber, int i, BigDecimal value, CellStyle cellStyle) {
        if (value == null) {
            return;
        }
        final Cell cell = rowNumber.createCell(i, CellType.STRING);
        cell.setCellValue(value.toString());
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
    }

    private void saveValueToBigInteger(Row rowNumber, int i, BigInteger value, CellStyle cellStyle) {
        if (value == null) {
            return;
        }
        final Cell cell = rowNumber.createCell(i, CellType.STRING);
        cell.setCellValue(value.toString());
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
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
}
