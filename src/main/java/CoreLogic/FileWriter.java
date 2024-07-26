package CoreLogic;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {
    public static FileInputStream currInput;
    public static FileOutputStream currOutput;
    public static XSSFWorkbook currWorkbook;
    public static XSSFSheet currSheet;
    public static int currBlankRowNumber = 0;
    public static final int firstRow = 1;
    public static final int secondRow = 2;
    public static final int thirdRow = 3;
    public static final int sheetIndex = 0;
    private static final String filePath = "C://Users//aklug//Desktop//Kumiko Project//KumikoMachine//Excel Sheets//Kumiko Example.xlsx";
    public static Row saveRow;

    public static void writePiece(double startingLength, float time) {
        try {
        /* On the first write, the currBlankRowNumber will be 0. Call the findBlankRow method to populate the currBlankRowNumber
         * With the first instance of an empty row.
         */
            if (currBlankRowNumber == 0) {
                findBlankRow();
            }

            /*Get the current blank row from the sheet. Set the time and material variables appropriately. Save to file
             * and close output stream.
             */
            saveRow = currSheet.createRow(currBlankRowNumber);
            Cell testCell = saveRow.createCell(firstRow);
            testCell.setCellValue(currBlankRowNumber);
            Cell cell = saveRow.createCell(secondRow);
            cell.setCellValue(time);
            cell = saveRow.createCell(thirdRow);
            cell.setCellValue(startingLength);
            FileOutputStream outFile = new FileOutputStream(filePath);
            currWorkbook.write(outFile);
            currBlankRowNumber++;
            outFile.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void findBlankRow() {
        //Make new FileInputStream, workbook and get the first sheet from the file
        try {
            currInput = new FileInputStream(filePath);
            currWorkbook = new XSSFWorkbook(currInput);
            currSheet = currWorkbook.getSheetAt(sheetIndex);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        Cell cell;
        /* For every row in the sheet, get the cell type in the 2nd column, if it is null, break,
         * otherwise if the cell contains either a String or a number, iterate currBlankRowNumber
         */
        for (Row row : currSheet) {
            cell = row.getCell(secondRow);
            if (cell == null) {
                break;
            }
            Object cellType = cell.getCellType();
            String cellString = String.valueOf(cellType);
            if (cellString.equals("STRING") || cellString.equals("NUMERIC")) {
                currBlankRowNumber++;
            } else {
                try {
                    currInput.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }
}
