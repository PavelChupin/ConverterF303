package ru.ysolutions.converter;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ru.ysolutions.converter.data.ContractBoard;
import ru.ysolutions.converter.models.xls.f303.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
                )
                .collect(Collectors.toList())
        );

        return f303;
    }

    private List<F303SpecialCondition> getConditionsCodes(Integer startNumRow, Integer numRowEndContract, Sheet sheet) {
        final List<F303SpecialCondition> f303SpecialConditions = new ArrayList<>();
        Row row;
        String p49ValueNow = getStringValue(sheet.getRow(startNumRow), 48);
        Integer p49StartRowIndex = p49ValueNow != null ? startNumRow : null;
        Integer p49EndRowIndex = p49StartRowIndex;

        for (int i = startNumRow; i <= numRowEndContract; i++) {
            row = sheet.getRow(i);

            if (p49ValueNow == null || p49ValueNow.equalsIgnoreCase(getStringValue(row, 48))
            ) {
                p49EndRowIndex = i;
                p49ValueNow = getStringValue(row, 48);
            } else {
                f303SpecialConditions.add(new F303SpecialCondition()
                        .p49(p49ValueNow)
                        // p3_50 p3_51
                        .conditionsCodeConds(getConditionsCodeConds(p49StartRowIndex, p49EndRowIndex, sheet)));
                p49StartRowIndex = i;
                p49EndRowIndex = i;
                p49ValueNow = getStringValue(row, 48);
            }
        }

        return f303SpecialConditions
                .stream()
                .filter(c -> c.p49() != null)
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
                        || e.p32() != null)
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
        return row.getCell(cell) == null ? null : row.getCell(cell).getStringCellValue();
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
                    && !getStringValue(row, 62).isEmpty()) {
                if (trancheBoard == null) {
                    trancheBoard = new ContractBoard().startNumRow(i);
                    trancheNumberNow = getStringValue(row, 62);
                } if (!trancheNumberNow.equalsIgnoreCase(getStringValue(row, 62))){
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
                if (trancheBoard != null){
                    contractBoard.addTranche(trancheBoard.endNumRow(i - 1));
                    trancheBoard = null;
                    trancheNumberNow = null;
                }
                contractBoards.add(contractBoard);
                contractBoard = new ContractBoard().startNumRow(i);
            } if (i == lastRow){
                if (trancheBoard != null){
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
