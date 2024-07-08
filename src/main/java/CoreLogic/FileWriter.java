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
    public static int currBlankRowNumber = -1;

    public static void writePiece(int startingLength) {

        try {
            currOutput = new FileOutputStream("C://Users//aklug//Desktop//Kumiko Project//KumikoMachine//Excel Sheets//Kumiko Example.xlsx");
            currInput = new FileInputStream("C://Users//aklug//Desktop//Kumiko Project//KumikoMachine//Excel Sheets//Kumiko Example.xlsx");
            currWorkbook = new XSSFWorkbook(currInput);
            currSheet = currWorkbook.getSheetAt(0);
            Cell cell;
            Row currRow = currSheet.getRow(0);

            if (currBlankRowNumber == -1) {
                for (Row row : currSheet) {
                    cell = row.getCell(3);
                    if (cell.getStringCellValue().equals("")) {
                        //currInput.close();
                        currBlankRowNumber = cell.getRowIndex() + 1;
                        System.out.println(currBlankRowNumber);
                        cell.setCellValue(43);
                        System.out.println(currWorkbook);
                        currWorkbook.write(currOutput);
                        //currOutput.close();

                    }
                }
            } else {
                //currInput.close();
                currRow.setRowNum(currBlankRowNumber);
                cell = currRow.getCell(3);
                cell.setCellValue(55);
                currBlankRowNumber++;
                currWorkbook.write(currOutput);
                //currOutput.close();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void findBlankCell() throws IOException {

        currInput = new FileInputStream("C://Users//aklug//Desktop//Kumiko Project//KumikoMachine//Excel Sheets//Kumiko Example.xlsx");
        currWorkbook = new XSSFWorkbook(currInput);
        currSheet = currWorkbook.getSheetAt(0);
        Cell cell;

        if (currBlankRowNumber == -1) {
            for (Row row : currSheet) {
                cell = row.getCell(3);
                if (cell.getStringCellValue().equals("")) {
                    currInput.close();
                    currBlankRowNumber = cell.getRowIndex() + 1;
                }
            }


        }

    }
}
