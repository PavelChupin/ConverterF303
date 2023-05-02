package ru.ysolutions.converter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ru.ysolutions.converter.data.ContractBoard;
import ru.ysolutions.converter.models.xls.f303.F303;
import ru.ysolutions.converter.models.xls.f303.F303Client;
import ru.ysolutions.converter.models.xls.f303.F303ClientGVZ;
import ru.ysolutions.converter.models.xls.f303.F303Contract;

import java.math.BigDecimal;
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
                .reportDate(sheet.getRow(0).getCell(1).getLocalDateTimeCellValue().toLocalDate());

        for (ContractBoard contractBoard : contractBoards
        ) {
            final F303Contract contract = new F303Contract()
                    .client_p1(getClient(contractBoard, sheet));


            f303.addContract(contract);
        }

        return f303;
    }

    private List<ContractBoard> getBoardContract(Sheet sheet) {
        final List<ContractBoard> contractBoards = new ArrayList<>();

        int lastRow = sheet.getLastRowNum();
        int startContractRow = 5;

        ContractBoard contractBoard = new ContractBoard().startNumRow(startContractRow);
        String clientName = sheet.getRow(startContractRow).getCell(0).getStringCellValue();

        for (int i = startContractRow; i < lastRow; i++) {
            Row row = sheet.getRow(i);

            // Если текущая строка это начало новой группы строк, то считаем что предыдущий контракт закончили и начали новый.
            // Учет ведем по клиентам
            if (row.getCell(0) != null
                    && !row.getCell(0).getStringCellValue().isEmpty()
                    && !clientName.equals(row.getCell(0).getStringCellValue())) {
                contractBoard.endNumRow(i - 1);
                contractBoards.add(contractBoard);
                contractBoard = new ContractBoard().startNumRow(i);
            }
        }
        return contractBoards;
    }

    private F303Client getClient(ContractBoard contractBoard, Sheet sheet) {
        final Row row = sheet.getRow(contractBoard.startNumRow());
        return new F303Client()
                .p1(getStringValue(row.getCell(0)))
                .p2(getStringValue(row.getCell(1)))
                .p3(getStringValue(row.getCell(2)))
                .p4(getLocalDateValue(row.getCell(3)))
                .p5(getStringValue(row.getCell(4)))
                .p6(getStringValue(row.getCell(5)))
                .p7(getStringValue(row.getCell(6)))
                .p8(getStringValue(row.getCell(7)))
                .p9(getStringValue(row.getCell(8)))
                .p10(getStringValue(row.getCell(9)))
                .clientGVZs(getClientGVZs(contractBoard, sheet));
    }

    private List<F303ClientGVZ> getClientGVZs(ContractBoard contractBoard, Sheet sheet) {
        final List<F303ClientGVZ> gzvs = new ArrayList<>();
        for (int i = contractBoard.startNumRow(); i <= contractBoard.endNumRow(); i++) {
            Row row = sheet.getRow(i);
            gzvs.add(new F303ClientGVZ()
                    .p11(getStringValue(row.getCell(10)))
                    .p12(getStringValue(row.getCell(11)))
            );
        }

        return gzvs
                .stream()
                .filter(v -> (v.p11() != null && !v.p11().isEmpty()) || (v.p12() != null && !v.p12().isEmpty()))
                .collect(Collectors.toList());
    }

    private String getStringValue(Cell cell) {
        return cell == null ? null : cell.getStringCellValue();
    }

    private LocalDate getLocalDateValue(Cell cell) {
        return cell == null ? null : cell.getLocalDateTimeCellValue().toLocalDate();
    }

    private BigDecimal getBigDecimalValue(Cell cell) {
        return cell == null ? null : BigDecimal.valueOf(cell.getNumericCellValue());
    }
}
