package CoreLogic;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class FileWriter {
    public static FileInputStream currInput;
    public static FileOutputStream currOutput;
    public static XSSFWorkbook currWorkbook;

    public static XSSFWorkbook tempWorkbook = new XSSFWorkbook();
    public static XSSFSheet currSheet;
    public static int currBlankRowNumber = 0;

    public static void writePiece(int startingLength) throws FileNotFoundException {
        try {
            //On the first write, the currBlankRowNumber will be 0. Call the findBlank cell method to populate the currBlankRowNumber
            //With the first instance of an empty cell.
            if (currBlankRowNumber == 0) {
                findBlankCell();
            }
            Row saveRow = currSheet.createRow(currBlankRowNumber);
            Cell cell = saveRow.createCell(3);
            cell.setCellValue(currBlankRowNumber + 1);
            FileOutputStream outFile = new FileOutputStream(new File("C://Users//aklug//Desktop//Kumiko Project//KumikoMachine//Excel Sheets//Kumiko Example.xlsx"));
            currWorkbook.write(outFile);
            currBlankRowNumber++;
            outFile.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void findBlankCell() throws IOException {

        currInput = new FileInputStream("C://Users//aklug//Desktop//Kumiko Project//KumikoMachine//Excel Sheets//Kumiko Example.xlsx");
        currWorkbook = new XSSFWorkbook(currInput);
        currSheet = currWorkbook.getSheetAt(0);
        Cell cell;

        for (Row row : currSheet) {
            cell = row.getCell(3);
            if (cell == null) {
                break;
            }
            Object cellType = cell.getCellType();
            String cellString = String.valueOf(cellType);
            System.out.println(cellString);
            if (cellString.equals("STRING") || cellString.equals("NUMERIC")) {
                currBlankRowNumber++;
            } else {
                currInput.close();
                break;
            }
        }
    }
}
