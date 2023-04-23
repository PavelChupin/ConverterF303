package ru.ysolutions.converter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import ru.ysolutions.converter.models.xls.ExcelHeader;
import ru.ysolutions.converter.models.xls.f303.*;

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
    private final CellStyle stringStyleZeroColumn;
    private final CellStyle headerColumnName;
    private final CellStyle headerColumnPatr;
    private final CellStyle headerPatr;

    private final CellStyle moneyStyle;

    public static final int countColumn = 114;

    public ConverterExcel(Workbook workBook) {
        this.workBook = workBook;

        // Делаем стиль колонки типа Дата
        final DataFormat format = workBook.createDataFormat();
        this.dateStyle = workBook.createCellStyle();
        this.dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
        //this.dateStyle.setShrinkToFit(true);


        final DataFormat format1 = workBook.createDataFormat();
        this.moneyStyle = workBook.createCellStyle();
        this.moneyStyle.setDataFormat(format.getFormat("0.00"));
        //this.moneyStyle.setShrinkToFit(true);

        // Стиль данных текстовых
        this.stringStyle = workBook.createCellStyle();
        //this.stringStyle.setShrinkToFit(true);

        this.stringStyleZeroColumn = workBook.createCellStyle();
        // Заливка ячеки
        this.stringStyleZeroColumn.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        this.stringStyleZeroColumn.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //this.stringStyleZeroColumn.setShrinkToFit(true);
        //this.stringStyleZeroColumn.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());

        // Делаем стиль для колонки нумератора
        this.headerNumberStyle = workBook.createCellStyle();
        this.headerNumberStyle.setAlignment(HorizontalAlignment.CENTER);
        this.headerNumberStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // Шрифт для колонки нумератора
        final Font font = workBook.createFont();
        font.setColor(Font.COLOR_RED);
        this.headerNumberStyle.setFont(font);

        // Стиль для колонки наименования полей
        this.headerColumnName = workBook.createCellStyle();
        this.headerColumnName.setBorderTop(BorderStyle.THICK);
        this.headerColumnName.setBorderBottom(BorderStyle.MEDIUM);
        this.headerColumnName.setBorderLeft(BorderStyle.MEDIUM);
        this.headerColumnName.setBorderRight(BorderStyle.MEDIUM);
        this.headerColumnName.setWrapText(true);
        this.headerColumnName.setAlignment(HorizontalAlignment.CENTER);
        this.headerColumnName.setVerticalAlignment(VerticalAlignment.CENTER);


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
            saveValueToCellString(row, 0, f303Contract.client_p1().p1(), this.stringStyleZeroColumn);

            // 2
            saveValueToCellString(row, 1, f303Contract.client_p1().p2(), this.stringStyle);

            // 3
            saveValueToCellString(row, 2, f303Contract.client_p1().p3(), this.stringStyle);

            // 4
            saveValueToCellDate(row, 3, f303Contract.client_p1().p4(), this.dateStyle);

            // 5
            saveValueToCellString(row, 4, f303Contract.client_p1().p5(), this.stringStyle);

            // 6
            saveValueToCellString(row, 5, f303Contract.client_p1().p6(), this.stringStyle);

            // 7
            saveValueToCellString(row, 6, f303Contract.client_p1().p7(), this.stringStyle);

            // 8
            saveValueToCellString(row, 7, f303Contract.client_p1().p8(), this.stringStyle);

            // 9
            saveValueToCellString(row, 8, f303Contract.client_p1().p9(), this.stringStyle);

            // 10
            saveValueToCellString(row, 9, f303Contract.client_p1().p10(), this.stringStyle);

            // 11/12
            rowEnd = saveClientGVZ(startContractRow, sheet, f303Contract.client_p1().clientGVZs());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);

            // 13
            saveValueToCellString(row, 12, f303Contract.p13(), this.stringStyle);

            // 14
            saveValueToCellString(row, 13, f303Contract.p14(), this.stringStyle);

            // 15
            saveValueToCellDate(row, 14, f303Contract.p15(), this.dateStyle);

            // 16
            saveValueToCellString(row, 15, f303Contract.p16(), this.stringStyle);

            // 17
            saveValueToCellDate(row, 16, f303Contract.p17(), this.dateStyle);

            // 18
            saveValueToCellString(row, 17, f303Contract.p18(), this.stringStyle);

            // 19
            saveValueToCellString(row, 18, f303Contract.p19(), this.stringStyle);

            // 20
            saveValueToCellString(row, 19, f303Contract.p20(), this.stringStyle);

            // 21
            saveValueToCellString(row, 20, f303Contract.p21(), this.stringStyle);

            // 22
            saveValueToCellBigInteger(row, 21, f303Contract.p22(), this.stringStyle);

            // 23
            saveValueToCellString(row, 22, f303Contract.p23(), this.stringStyle);

            // 24
            saveValueToCellDate(row, 23, f303Contract.p24(), this.dateStyle);

            // 25
            saveValueToCellDate(row, 24, f303Contract.p25(), this.dateStyle);

            // 26/27/28/29/30/31/32
            rowEnd = saveEncumbrance(startContractRow, sheet, f303Contract.encumbrances());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);

            // 33
            saveValueToCellString(row, 32, f303Contract.p33(), this.stringStyle);

            // 34
            saveValueToCellString(row, 33, f303Contract.p34(), this.stringStyle);

            // 35
            saveValueToCellString(row, 34, f303Contract.p35(), this.stringStyle);

            // 36
            saveValueToCellString(row, 35, f303Contract.p36(), this.stringStyle);

            // 37
            saveValueToCellBigDecimal(row, 36, f303Contract.p37(), this.moneyStyle);

            // 38
            saveValueToCellBigDecimal(row, 37, f303Contract.p38(), this.moneyStyle);

            // 39
            saveValueToCellString(row, 38, f303Contract.p39(), this.stringStyle);

            // 40
            saveValueToCellString(row, 39, f303Contract.p40(), this.stringStyle);

            // 41
            saveValueToCellDate(row, 40, f303Contract.p41(), this.dateStyle);

            // 42
            saveValueToCellDate(row, 41, f303Contract.p42(), this.dateStyle);

            // 43
            saveValueToCellString(row, 42, f303Contract.p43(), this.stringStyle);

            // 44
            saveValueToCellBigDecimal(row, 43, f303Contract.p44(), this.stringStyle);

            // 45
            saveValueToCellBigDecimal(row, 44, f303Contract.p45(), this.stringStyle);

            // 46
            saveValueToCellBigDecimal(row, 45, f303Contract.p46(), this.stringStyle);

            // 47
            saveValueToCellBigInteger(row, 46, f303Contract.p47(), this.stringStyle);

            // 48
            saveValueToCellString(row, 47, f303Contract.p48(), this.stringStyle);

            // 49/50/51
            rowEnd = saveConditionsCode(startContractRow, sheet, f303Contract.conditionsCodes());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);

            // 52
            saveValueToCellString(row, 51, f303Contract.p52(), this.stringStyle);

            // 53
            saveValueToCellString(row, 52, f303Contract.p53(), this.stringStyle);

            // 54
            saveValueToCellBigDecimal(row, 53, f303Contract.p54(), this.stringStyle);

            // 55
            saveValueToCellBigDecimal(row, 54, f303Contract.p55(), this.stringStyle);

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
            saveValueToCellBigDecimal(row, 63, f303Contract.p64(), this.moneyStyle);
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
            saveValueToCellBigDecimal(row, 72, f303Contract.p73(), this.moneyStyle);
            // 74
            saveValueToCellBigDecimal(row, 73, f303Contract.p74(), this.moneyStyle);
            // 75
            saveValueToCellString(row, 74, f303Contract.p75(), this.stringStyle);
            // 76
            saveValueToCellString(row, 75, f303Contract.p76(), this.stringStyle);
            // 77
            saveValueToCellBigDecimal(row, 76, f303Contract.p77(), this.stringStyle);
            // 78
            saveValueToCellBigDecimal(row, 77, f303Contract.p78(), this.moneyStyle);
            // 79
            saveValueToCellBigDecimal(row, 78, f303Contract.p79(), this.moneyStyle);
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
            saveValueToCellBigDecimal(row, 84, f303Contract.p85(), this.moneyStyle);
            // 86
            saveValueToCellBigDecimal(row, 85, f303Contract.p86(), this.moneyStyle);
            // 87
            saveValueToCellBigDecimal(row, 86, f303Contract.p87(), this.moneyStyle);

            // 88
            saveValueToCellBigDecimal(row, 87, f303Contract.p88(), this.moneyStyle);
            // 89
            saveValueToCellBigDecimal(row, 88, f303Contract.p89(), this.moneyStyle);
            // 90
            saveValueToCellBigDecimal(row, 89, f303Contract.p90(), this.moneyStyle);
            // 91
            saveValueToCellBigDecimal(row, 90, f303Contract.p91(), this.stringStyle);

            // 92
            saveValueToCellString(row, 91, f303Contract.p92(), this.stringStyle);
            // 93
            saveValueToCellBigDecimal(row, 92, f303Contract.p93(), this.moneyStyle);
            // 94
            saveValueToCellBigDecimal(row, 93, f303Contract.p94(), this.moneyStyle);
            // 95
            saveValueToCellString(row, 94, f303Contract.p95(), this.stringStyle);
            // 96
            saveValueToCellBigDecimal(row, 95, f303Contract.p96(), this.moneyStyle);
            // 97
            saveValueToCellBigDecimal(row, 96, f303Contract.p97(), this.moneyStyle);
            // 98
            saveValueToCellBigDecimal(row, 97, f303Contract.p98(), this.moneyStyle);
            // 99
            saveValueToCellDate(row, 98, f303Contract.p99(), this.dateStyle);

            // 99/100
            rowEnd = saveRepayments(startContractRow, sheet, f303Contract.f303Repayments());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);

            // 100
            // 101
            rowEnd = saveRepaymentSources(startContractRow, sheet, f303Contract.f303RepaymentSources());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);

            // 102
            // 103

            // 104
            saveValueToCellString(row, 103, f303Contract.p104(), this.stringStyle);
            // 105
            saveValueToCellString(row, 104, f303Contract.p105(), this.stringStyle);

            // 106/107/108/109/110/111/112/113/114
            rowEnd = saveP10(startContractRow, sheet, f303Contract.p10());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);


            // Транши
            rowEnd = saveTranches(maxContractRow, sheet, f303Contract.tranches(), f303Contract.p13());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            row = sheet.getRow(startContractRow);


        }

        // Автоподбор ширины для столбца
        sheet.autoSizeColumn(0);

        // Фиксируем столбцы и строки. Значения порядковый номер начиная с 1.
        sheet.createFreezePane(1, 5);

        // Объеденяем ячейки
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 11));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 12, 33));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 34, 54));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 55, 60));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 61, 69));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 70, 83));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 84, 86));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 87, 90));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 91, 104));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 105, 113));

        //sheet.setColumnBreak(0);
        //sheet.createFreezePane(0, -1);
        // Меняем размер столбцов
//        for (int i = 0; i < countColumn; i++) {
//            sheet.autoSizeColumn(i);
//        }
        return this.workBook;
    }

    //p94/p97/p98/p101/102/103/104/105
    private int saveRepaymentSources(int startContractRow, Sheet sheet, List<F303RepaymentSource> f303RepaymentSources) {
        int number = startContractRow - 1;
        for (F303RepaymentSource f303RepaymentSource : f303RepaymentSources) {
            number++;
            Row rowNumber = sheet.getRow(number);
            if (rowNumber == null) {
                rowNumber = sheet.createRow(number);
            }
            // 101
            saveValueToCellString(rowNumber, 100, f303RepaymentSource.p101(), this.stringStyle);
        }
        return number;
    }

    // 99/100
    private int saveRepayments(int startContractRow, Sheet sheet, List<F303Repayment> f303Repayments) {
        int endRow = startContractRow;
        for (F303Repayment repayment : f303Repayments) {
            endRow++;

            Row rowNumber = sheet.getRow(endRow);
            if (rowNumber == null) {
                rowNumber = sheet.createRow(endRow);
            }
            // p9
            // 99
            saveValueToCellDate(rowNumber, 98, repayment.p99(), this.dateStyle);
            // 100
            saveValueToCellDate(rowNumber, 99, repayment.p100(), this.dateStyle);
        }
        return endRow;
    }

    private int saveTranches(int maxContractRow, Sheet sheet, List<F303Tranche> tranches, String contractId) {
        int startRowNumberTranche;
        int maxRowNumberTranche = maxContractRow;
        for (F303Tranche tranche : tranches) {
            int rowEnd;
            maxRowNumberTranche++;
            startRowNumberTranche = maxRowNumberTranche;

            Row rowNumber = sheet.getRow(startRowNumberTranche);
            if (rowNumber == null) {
                rowNumber = sheet.createRow(startRowNumberTranche);
            }

            // p2
            // 13
            saveValueToCellString(rowNumber, 12, contractId, this.stringStyle);
            // 22
            saveValueToCellBigInteger(rowNumber, 21, tranche.p22(), this.stringStyle);
            // 25
            saveValueToCellDate(rowNumber, 24, tranche.p25(), this.dateStyle);
            // 33
            saveValueToCellString(rowNumber, 32, tranche.p33(), this.stringStyle);
            // p3
            // 36
            saveValueToCellString(rowNumber, 35, tranche.p36(), this.stringStyle);
            // 37
            saveValueToCellBigDecimal(rowNumber, 36, tranche.p37(), this.moneyStyle);
            // 38
            saveValueToCellBigDecimal(rowNumber, 37, tranche.p38(), this.moneyStyle);
            // 39
            saveValueToCellString(rowNumber, 38, tranche.p39(), this.stringStyle);
            // 40
            saveValueToCellString(rowNumber, 39, tranche.p40(), this.stringStyle);
            // 41
            saveValueToCellDate(rowNumber, 40, tranche.p41(), this.dateStyle);
            // 42
            saveValueToCellDate(rowNumber, 41, tranche.p42(), this.dateStyle);
            // 43
            saveValueToCellString(rowNumber, 42, tranche.p43(), this.stringStyle);
            // 44
            saveValueToCellBigDecimal(rowNumber, 43, tranche.p44(), this.stringStyle);
            // 45
            saveValueToCellBigDecimal(rowNumber, 44, tranche.p45(), this.stringStyle);
            // 46
            saveValueToCellBigDecimal(rowNumber, 45, tranche.p46(), this.stringStyle);
            // 47
            saveValueToCellBigInteger(rowNumber, 46, tranche.p47(), this.stringStyle);
            // 48
            saveValueToCellString(rowNumber, 47, tranche.p48(), this.stringStyle);

            // 49/50/51
            rowEnd = saveConditionsCode(startRowNumberTranche, sheet, tranche.conditionsCodes());
            if (maxRowNumberTranche < rowEnd) {
                maxRowNumberTranche = rowEnd;
            }
            rowNumber = sheet.getRow(startRowNumberTranche);


            // 52
            saveValueToCellString(rowNumber, 51, tranche.p52(), this.stringStyle);
            // 53
            saveValueToCellString(rowNumber, 52, tranche.p53(), this.stringStyle);
            //54
            saveValueToCellBigDecimal(rowNumber, 53, tranche.p54(), this.stringStyle);
            //55
            saveValueToCellBigDecimal(rowNumber, 54, tranche.p55(), this.stringStyle);

            // p4
            rowEnd = saveWarranty(startRowNumberTranche, sheet, tranche.warranties());
            if (maxRowNumberTranche < rowEnd) {
                maxRowNumberTranche = rowEnd;
            }
            rowNumber = sheet.getRow(startRowNumberTranche);

            // p5
            // 62
            saveValueToCellDate(rowNumber, 61, tranche.p62(), this.dateStyle);
            // 63
            saveValueToCellString(rowNumber, 62, tranche.p63(), this.stringStyle);
            // 64
            saveValueToCellBigDecimal(rowNumber, 63, tranche.p64(), this.moneyStyle);
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

            // p6
            // 71
            saveValueToCellString(rowNumber, 70, tranche.p71(), this.stringStyle);
            // 72
            saveValueToCellString(rowNumber, 71, tranche.p72(), this.stringStyle);
            // 73
            saveValueToCellBigDecimal(rowNumber, 72, tranche.p73(), this.moneyStyle);
            // 74
            saveValueToCellBigDecimal(rowNumber, 73, tranche.p74(), this.moneyStyle);
            // 75
            saveValueToCellString(rowNumber, 74, tranche.p75(), this.stringStyle);
            // 76
            saveValueToCellString(rowNumber, 75, tranche.p76(), this.stringStyle);
            // 77
            saveValueToCellBigDecimal(rowNumber, 76, tranche.p77(), this.stringStyle);
            // 78
            saveValueToCellBigDecimal(rowNumber, 77, tranche.p78(), this.moneyStyle);
            // 79
            saveValueToCellBigDecimal(rowNumber, 78, tranche.p79(), this.moneyStyle);
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

            // p7
            // 85
            saveValueToCellBigDecimal(rowNumber, 84, tranche.p85(), this.moneyStyle);
            // 86
            saveValueToCellBigDecimal(rowNumber, 85, tranche.p86(), this.moneyStyle);
            // 87
            saveValueToCellBigDecimal(rowNumber, 86, tranche.p87(), this.moneyStyle);

            // 92
            saveValueToCellString(rowNumber, 91, tranche.p92(), this.stringStyle);
            // 93
            saveValueToCellBigDecimal(rowNumber, 92, tranche.p93(), this.moneyStyle);
            // 94
            saveValueToCellBigDecimal(rowNumber, 93, tranche.p94(), this.moneyStyle);
            // 95
            saveValueToCellString(rowNumber, 94, tranche.p95(), this.stringStyle);
            // 96
            saveValueToCellBigDecimal(rowNumber, 95, tranche.p96(), this.moneyStyle);
            // 97
            saveValueToCellBigDecimal(rowNumber, 96, tranche.p97(), this.moneyStyle);
            // 98
            saveValueToCellBigDecimal(rowNumber, 97, tranche.p98(), this.moneyStyle);
            // 99
            saveValueToCellDate(rowNumber, 98, tranche.p99(), this.dateStyle);

            // 99/100
            rowEnd = saveRepayments(startRowNumberTranche, sheet, tranche.f303Repayments());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            rowNumber = sheet.getRow(startRowNumberTranche);

            // 100
            // 101
            rowEnd = saveRepaymentSources(startRowNumberTranche, sheet, tranche.f303RepaymentSources());
            if (maxContractRow < rowEnd) {
                maxContractRow = rowEnd;
            }
            rowNumber = sheet.getRow(startRowNumberTranche);
            // 102
            // 103

            // 104
            saveValueToCellString(rowNumber, 103, tranche.p104(), this.stringStyle);
            // 105
            saveValueToCellString(rowNumber, 104, tranche.p105(), this.stringStyle);
        }
        return maxRowNumberTranche;
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

    // 56/57/58/59/60/61
    private int saveWarranty(int startContractRow, Sheet sheet, List<F303Warranty> warranties) {
        int number = startContractRow;
        for (F303Warranty warranty : warranties) {
            number++;
            Row rowNumber = sheet.getRow(number);
            if (rowNumber == null) {
                rowNumber = sheet.createRow(number);
            }
            // 56
            saveValueToCellString(rowNumber, 55, warranty.p56(), this.stringStyle);
            // 57
            saveValueToCellBigDecimal(rowNumber, 56, warranty.p57(), this.moneyStyle);
            // 58
            saveValueToCellDate(rowNumber, 57, warranty.p58(), this.dateStyle);
            // 59
            saveValueToCellBigDecimal(rowNumber, 58, warranty.p59(), this.moneyStyle);
            // 60
            saveValueToCellBigDecimal(rowNumber, 59, warranty.p60(), this.moneyStyle);
            // 61
            saveValueToCellBigDecimal(rowNumber, 60, warranty.p61(), this.moneyStyle);
        }
        return number;
    }

    // Поля 49 50 51
    private int saveConditionsCode(int startContractRow, Sheet sheet, List<F303SpecialCondition> f303SpecialConditions) {
        int number = startContractRow - 1;
        for (F303SpecialCondition f303SpecialCondition : f303SpecialConditions) {
            number++;
            Row rowNumber = sheet.getRow(number);
            if (rowNumber == null) {
                rowNumber = sheet.createRow(number);
            }
            // 49
            saveValueToCellString(rowNumber, 48, f303SpecialCondition.p49(), this.stringStyle);
            // 50/51
            int maxContractRow = saveConditionsCodeCond(number, sheet, f303SpecialCondition.conditionsCodeConds());
            if (number < maxContractRow) {
                number = maxContractRow;
            }
        }
        return number;
    }

    // Поля 50 51
    private int saveConditionsCodeCond(int startContractRow, Sheet sheet, List<F303SpecialConditionProperty> f303SpecialConditionProperties) {
        int number = startContractRow - 1;
        for (F303SpecialConditionProperty f303SpecialConditionProperty : f303SpecialConditionProperties) {
            number++;
            Row rowNumber = sheet.getRow(number);
            if (rowNumber == null) {
                rowNumber = sheet.createRow(number);
            }
            // 50
            saveValueToCellString(rowNumber, 49, f303SpecialConditionProperty.p50(), this.stringStyle);
            // 51
            saveValueToCellString(rowNumber, 50, f303SpecialConditionProperty.p51(), this.stringStyle);

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
            saveValueToCellString(rowNumber, 10, f303ClientGVZ.p11(), this.stringStyle);
            // 12
            saveValueToCellString(rowNumber, 11, f303ClientGVZ.p12(), this.stringStyle);
        }
        return number;
    }

    private void saveValueToCellBigDecimal(Row rowNumber, int i, BigDecimal value, CellStyle cellStyle) {
        if (value == null) {
            return;
        }
        final Cell cell = rowNumber.createCell(i, CellType.STRING);
        cell.setCellValue(value.doubleValue());
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
    }

    private void saveValueToCellBigInteger(Row rowNumber, int i, BigInteger value, CellStyle cellStyle) {
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
        row.setHeight((short) 2200);
        final Row finalRow1 = row;
        ExcelHeader.columnNames.keySet().forEach(key -> {
            sheet.setColumnWidth(key, ExcelHeader.columnNames.get(key).width());
            saveValueToCellString(finalRow1, key, ExcelHeader.columnNames.get(key).name(), this.headerColumnName);
        });

        // Нумерация в подразделах
        row = sheet.createRow(4);
        final Row finalRow2 = row;
        ExcelHeader.columnPart.keySet().forEach(key -> saveValueToCellString(finalRow2, key, ExcelHeader.columnPart.get(key), this.headerColumnPatr));

        return row.getRowNum();
    }
}
