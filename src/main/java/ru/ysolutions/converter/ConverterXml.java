package ru.ysolutions.converter;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ru.ysolutions.converter.data.ConditionBoard;
import ru.ysolutions.converter.data.ContractBoard;
import ru.ysolutions.converter.models.xls.f303.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConverterXml {
    private final Workbook workBook;

    public ConverterXml(Workbook workBook) {
        this.workBook = workBook;
    }

    public F303 getDataFromXls() {
        final Sheet sheet = workBook.getSheet(ConverterExcel.SHEET_NAME);
        // Составим список границ договоров
        final List<ContractBoard> contractBoards = getBoardContract(sheet);

        final F303 f303 = new F303()
                .reportDate(sheet
                        .getRow(0)
                        .getCell(1)
                        .getLocalDateTimeCellValue()
                        .toLocalDate()
                );

        f303.getContracts().addAll(contractBoards
                .stream()
                .map(cb -> new F303Contract()
                        .client_p1(getClientByRowXls(cb, sheet))

                        // P2
                        .p13(getStringValue(sheet.getRow(cb.startNumRow()), 12))
                        .p14(getStringValue(sheet.getRow(cb.startNumRow()), 13))
                        .p15(getLocalDateValue(sheet.getRow(cb.startNumRow()), 14))
                        .p16(getStringValue(sheet.getRow(cb.startNumRow()), 15))
                        .p17(getLocalDateValue(sheet.getRow(cb.startNumRow()), 16))
                        .p18(getStringValue(sheet.getRow(cb.startNumRow()), 17))
                        .p19(getStringValue(sheet.getRow(cb.startNumRow()), 18))
                        .p20(getStringValue(sheet.getRow(cb.startNumRow()), 19))
                        .p21(getStringValue(sheet.getRow(cb.startNumRow()), 20))
                        .p22(getBigIntegerValue(sheet.getRow(cb.startNumRow()), 21))
                        .p23(getStringValue(sheet.getRow(cb.startNumRow()), 22))
                        .p24(getLocalDateValue(sheet.getRow(cb.startNumRow()), 23))
                        .p25(getLocalDateValue(sheet.getRow(cb.startNumRow()), 24))
                        // p26 p27 p28 p29 p30 p31 p32
                        .encumbrances(getEncumbrances(cb.startNumRow(), cb.getNumRowEndContract(), sheet))
                        .p33(getStringValue(sheet.getRow(cb.startNumRow()), 32))
                        .p34(getStringValue(sheet.getRow(cb.startNumRow()), 33))

                        // P3
                        .p35(getStringValue(sheet.getRow(cb.startNumRow()), 34))
                        .p36(getStringValue(sheet.getRow(cb.startNumRow()), 35))
                        .p37(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 36))
                        .p38(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 37))
                        .p39(getStringValue(sheet.getRow(cb.startNumRow()), 38))
                        .p40(getStringValue(sheet.getRow(cb.startNumRow()), 39))
                        .p41(getLocalDateValue(sheet.getRow(cb.startNumRow()), 40))
                        .p42(getLocalDateValue(sheet.getRow(cb.startNumRow()), 41))
                        .p43(getStringValue(sheet.getRow(cb.startNumRow()), 42))
                        .p44(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 43))
                        .p45(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 44))
                        .p46(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 45))
                        .p47(getBigIntegerValue(sheet.getRow(cb.startNumRow()), 46))
                        .p48(getStringValue(sheet.getRow(cb.startNumRow()), 47))
                        // p49 p50 p51
                        .conditionsCodes(getConditionsCodes(cb.startNumRow(), cb.getNumRowEndContract(), sheet))
                        .p52(getStringValue(sheet.getRow(cb.startNumRow()), 51))
                        .p53(getStringValue(sheet.getRow(cb.startNumRow()), 52))
                        .p54(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 53))
                        .p55(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 54))

                        // P4
                        .warranties(getWarranties(cb.startNumRow(), cb.getNumRowEndContract(), sheet))

                        // P5
                        .p62(getLocalDateValue(sheet.getRow(cb.startNumRow()), 61))
                        .p63(getStringValue(sheet.getRow(cb.startNumRow()), 62))
                        .p64(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 63))
                        .p65(getStringValue(sheet.getRow(cb.startNumRow()), 64))
                        .p66(getStringValue(sheet.getRow(cb.startNumRow()), 65))
                        .p67(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 66))
                        .p68(getStringValue(sheet.getRow(cb.startNumRow()), 67))
                        .p69(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 68))
                        .p70(getStringValue(sheet.getRow(cb.startNumRow()), 69))

                        // P6
                        .p71(getStringValue(sheet.getRow(cb.startNumRow()), 70))
                        .p72(getStringValue(sheet.getRow(cb.startNumRow()), 71))
                        .p73(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 72))
                        .p74(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 73))
                        .p75(getStringValue(sheet.getRow(cb.startNumRow()), 74))
                        .p76(getStringValue(sheet.getRow(cb.startNumRow()), 75))
                        .p77(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 76))
                        .p78(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 77))
                        .p79(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 78))
                        .p80(getStringValue(sheet.getRow(cb.startNumRow()), 79))
                        .p81(getStringValue(sheet.getRow(cb.startNumRow()), 80))
                        .p82(getStringValue(sheet.getRow(cb.startNumRow()), 81))
                        .p83(getStringValue(sheet.getRow(cb.startNumRow()), 82))
                        .p84(getStringValue(sheet.getRow(cb.startNumRow()), 83))

                        // P7
                        .p85(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 84))
                        .p86(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 85))
                        .p87(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 86))

                        // P8
                        .p88(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 87))
                        .p89(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 88))
                        .p90(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 89))
                        .p91(getBigDecimalValue(sheet.getRow(cb.startNumRow()), 90))

                        // P9

                        // P10
                        .p10(getP10(cb.startNumRow(), cb.getNumRowEndContract(), sheet))
                )
                .collect(Collectors.toList())
        );

        return f303;
    }

    private List<F303p10> getP10(Integer startNumRow, Integer numRowEndContract, Sheet sheet) {
        final List<F303p10> p10 = new ArrayList<>();
        Row row;
        for (int i = startNumRow; i <= numRowEndContract; i++) {
            row = sheet.getRow(i);

            p10.add(new F303p10()
                    .p106(getStringValue(row, 105))
                    .p107(getBigDecimalValue(row, 106))
                    .p108(getBigDecimalValue(row, 107))
                    .p109(getStringValue(row, 108))
                    .p110(getStringValue(row, 109))
                    .p111(getStringValue(row, 110))
                    .p112(getStringValue(row, 111))
                    .p113(getBigDecimalValue(row, 112))
                    .p114(getStringValue(row, 113))
            );
        }

        return p10
                .stream()
                .filter(e -> e.p106() != null
                        || e.p107() != null
                        || e.p108() != null
                        || e.p109() != null
                        || e.p110() != null
                        || e.p111() != null
                        || e.p112() != null
                        || e.p113() != null
                        || e.p114() != null
                )
                .collect(Collectors.toList());
    }

    private List<F303Warranty> getWarranties(Integer startNumRow, Integer numRowEndContract, Sheet sheet) {
        final List<F303Warranty> warranties = new ArrayList<>();
        Row row;
        for (int i = startNumRow; i <= numRowEndContract; i++) {
            row = sheet.getRow(i);

            warranties.add(new F303Warranty()
                    .p56(getStringValue(row, 55))
                    .p57(getBigDecimalValue(row, 56))
                    .p58(getLocalDateValue(row, 57))
                    .p59(getBigDecimalValue(row, 58))
                    .p60(getBigDecimalValue(row, 59))
                    .p61(getBigDecimalValue(row, 60))
            );
        }

        return warranties
                .stream()
                .filter(e -> e.p56() != null
                        || e.p57() != null
                        || e.p58() != null
                        || e.p59() != null
                        || e.p60() != null
                        || e.p61() != null
                )
                .collect(Collectors.toList());
    }

    private List<F303SpecialCondition> getConditionsCodes(Integer startNumRow, Integer numRowEndContract, Sheet sheet) {
        final List<ConditionBoard> conditionBoards = new ArrayList<>();

        Row row;
        String conditionName = null;
        for (int i = startNumRow; i <= numRowEndContract; i++) {
            row = sheet.getRow(i);

            if (row.getCell(48) != null && getStringValue(row, 48) != null) {
                conditionName = getStringValue(row, 48);
            }

            if (conditionName != null && !conditionName.isEmpty()) {
                conditionBoards.add(new ConditionBoard()
                        .rowIndex(i)
                        .condition(conditionName)
                );
            }
        }

        return conditionBoards.stream()
                .collect(Collectors.groupingBy(ConditionBoard::condition))
                .entrySet()
                .stream()
                .map(entry -> new F303SpecialCondition()
                        .p49(entry.getKey())
                        .conditionsCodeConds(getConditionsCodeConds(entry.getValue().stream().mapToInt(ConditionBoard::rowIndex).min().getAsInt()
                                , entry.getValue().stream().mapToInt(ConditionBoard::rowIndex).max().getAsInt()
                                , sheet))
                )
                .collect(Collectors.toList());
    }

    private List<F303SpecialConditionProperty> getConditionsCodeConds(Integer startNumRow, Integer endNumRow, Sheet sheet) {
        final List<F303SpecialConditionProperty> specialConditionProperties = new ArrayList<>();
        Row row;
        for (int i = startNumRow; i <= endNumRow; i++) {
            row = sheet.getRow(i);

            specialConditionProperties.add(new F303SpecialConditionProperty()
                    .p50(getStringValue(row, 49))
                    .p51(getStringValue(row, 50))
            );
        }

        return specialConditionProperties
                .stream()
                .filter(r -> r.p50() != null
                        || r.p51() != null
                )
                .collect(Collectors.toList());
    }

    private List<F303Encumbrance> getEncumbrances(Integer startNumRow, Integer numRowEndContract, Sheet sheet) {
        final List<F303Encumbrance> encumbrances = new ArrayList<>();
        Row row;
        for (int i = startNumRow; i <= numRowEndContract; i++) {
            row = sheet.getRow(i);

            encumbrances.add(new F303Encumbrance()
                    .p26(getStringValue(row, 25))
                    .p27(getStringValue(row, 26))
                    .p28(getStringValue(row, 27))
                    .p29(getStringValue(row, 28))
                    .p30(getStringValue(row, 29))
                    .p31(getBigDecimalValue(row, 30))
                    .p32(getLocalDateValue(row, 31))
            );
        }

        return encumbrances
                .stream()
                .filter(e -> e.p26() != null
                        || e.p27() != null
                        || e.p28() != null
                        || e.p29() != null
                        || e.p30() != null
                        || e.p31() != null
                        || e.p32() != null
                )
                .collect(Collectors.toList());
    }

    private F303Client getClientByRowXls(ContractBoard contractBoard, Sheet sheet) {
        final Row row = sheet.getRow(contractBoard.startNumRow());
        return new F303Client()
                .p1(getStringValue(row, 0))
                .p2(getStringValue(row, 1))
                .p3(getStringValue(row, 2))
                .p4(getLocalDateValue(row, 3))
                .p5(getStringValue(row, 4))
                .p6(getStringValue(row, 5))
                .p7(getStringValue(row, 6))
                .p8(getStringValue(row, 7))
                .p9(getStringValue(row, 8))
                .p10(getStringValue(row, 9))
                .clientGVZs(getClientGVZsByRowXls(contractBoard, sheet));
    }

    private List<F303ClientGVZ> getClientGVZsByRowXls(ContractBoard contractBoard, Sheet sheet) {
        final List<F303ClientGVZ> gzvs = new ArrayList<>();
        Row row;
        for (int i = contractBoard.startNumRow(); i <= contractBoard.endNumRow(); i++) {
            row = sheet.getRow(i);
            gzvs.add(new F303ClientGVZ()
                    .p11(getStringValue(row, 10))
                    .p12(getStringValue(row, 11))
            );
        }

        return gzvs
                .stream()
                .filter(v -> v.p11() != null
                        || v.p12() != null
                )
                .collect(Collectors.toList());
    }

    private String getStringValue(Row row, Integer cell) {
        return row.getCell(cell) == null || row.getCell(cell).getStringCellValue().isEmpty() ? null : row.getCell(cell).getStringCellValue();
    }

    private LocalDate getLocalDateValue(Row row, Integer cellIndex) {
        return row.getCell(cellIndex) == null || row.getCell(cellIndex).getLocalDateTimeCellValue() == null ? null : row.getCell(cellIndex).getLocalDateTimeCellValue().toLocalDate();
    }

    private BigDecimal getBigDecimalValue(Row row, Integer cellIndex) {
        return row.getCell(cellIndex) == null ? null : BigDecimal.valueOf(row.getCell(cellIndex).getNumericCellValue());
    }

    private BigInteger getBigIntegerValue(Row row, Integer cellIndex) {
        return row.getCell(cellIndex) == null ? null : BigInteger.valueOf(Long.parseLong(row.getCell(cellIndex).getStringCellValue()));
    }

    private List<ContractBoard> getBoardContract(Sheet sheet) {
        final List<ContractBoard> contractBoards = new ArrayList<>();

        int lastRow = sheet.getLastRowNum();
        int startContractRow = 5;

        ContractBoard contractBoard = new ContractBoard().startNumRow(startContractRow);
        String clientName = sheet.getRow(startContractRow).getCell(0).getStringCellValue();

        ContractBoard trancheBoard = null;
        String trancheNumberNow = null;
//        Integer startRowNumTranche = null;
//        Integer endRowNumTranche = null;
        Row row;
        for (int i = startContractRow; i <= lastRow; i++) {
            row = sheet.getRow(i);

            // Если в поле номер транша есть запись, значит начали новый транш.
            if (row.getCell(62) != null
                    && getStringValue(row, 62) != null) {
                if (trancheBoard == null) {
                    trancheBoard = new ContractBoard().startNumRow(i);
                    trancheNumberNow = getStringValue(row, 62);
                }
                if (!trancheNumberNow.equalsIgnoreCase(getStringValue(row, 62))) {
                    trancheBoard.endNumRow(i - 1);
                    contractBoard.addTranche(trancheBoard);
                    trancheBoard = new ContractBoard().startNumRow(i);
                    trancheNumberNow = getStringValue(row, 62);
                }
            }

            // Если текущая строка это начало новой группы строк, то считаем что предыдущий контракт закончили и начали новый.
            // Учет ведем по клиентам
            if (row.getCell(0) != null
                    && !row.getCell(0).getStringCellValue().isEmpty()
                    && !clientName.equals(row.getCell(0).getStringCellValue())) {
                contractBoard.endNumRow(i - 1);
                if (trancheBoard != null) {
                    contractBoard.addTranche(trancheBoard.endNumRow(i - 1));
                    trancheBoard = null;
                    trancheNumberNow = null;
                }
                contractBoards.add(contractBoard);
                contractBoard = new ContractBoard().startNumRow(i);
            }
            if (i == lastRow) {
                if (trancheBoard != null) {
                    contractBoard.addTranche(trancheBoard.endNumRow(i));
                    trancheBoard = null;
                    trancheNumberNow = null;
                }
                contractBoard.endNumRow(i);
                contractBoards.add(contractBoard);
            }
        }
        return contractBoards;
    }
}
