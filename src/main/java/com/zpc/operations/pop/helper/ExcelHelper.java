package com.zpc.operations.pop.helper;

import com.zpc.operations.pop.domain.OutwardItemsDetailsReport;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Id", "Title", "Description", "Published"};
    static String SHEET = "Reports";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<OutwardItemsDetailsReport> excelToReports(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<OutwardItemsDetailsReport> reports = new ArrayList<OutwardItemsDetailsReport>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                //skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                OutwardItemsDetailsReport report = new OutwardItemsDetailsReport();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            report.setSn((int) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            report.settDate(currentCell.getDateCellValue());
                            break;

                        case 2:
                            report.setDraweeBank(currentCell.getStringCellValue());
                            break;

                        case 3:
                            report.setSeqNo(currentCell.getStringCellValue());
                            break;
                        case 4:
                            report.setSerialNo(currentCell.getStringCellValue());
                            break;
                        case 5:
                            report.setSortCode(currentCell.getStringCellValue());
                            break;
                        case 6:
                            report.settCode(currentCell.getStringCellValue());
                            break;
                        case 7:
                            report.setAmount(currentCell.getNumericCellValue());
                            break;
                        case 8:
                            report.setReceivingAccountNo(currentCell.getStringCellValue());
                            break;
                        case 9:
                            report.setReceivingName(currentCell.getStringCellValue());
                            break;
                        case 10:
                            report.setPresentingAccountNo(currentCell.getStringCellValue());
                            break;
                        case 11:
                            report.setPresentingAccountName(currentCell.getStringCellValue());
                            break;
                        case 12:
                            report.setPresentingBank(currentCell.getStringCellValue());
                            break;
                        case 13:
                            report.setTransDetails(currentCell.getStringCellValue());
                            break;
                        case 14:
                            report.setWindow(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                reports.add(report);
            }

            workbook.close();

            return reports;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
