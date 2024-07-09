package CoreLogic;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class FileWriter {
    public static FileInputStream currInput;
    public static FileOutputStream currOutput;
    public static XSSFWorkbook currWorkbook;

    public static XSSFWorkbook tempWorkbook = new XSSFWorkbook();
    public static XSSFSheet currSheet;
    public static int currBlankRowNumber = -1;

    public static void writePiece(int startingLength) throws FileNotFoundException {
        try {
            if (currBlankRowNumber == -1) {
                findBlankCell();
                System.out.println("The blank cell is " + currBlankRowNumber);
                Sheet sheet = currWorkbook.createSheet("Test");
                Row saveRow = sheet.createRow(3);
                saveRow.setRowNum(currBlankRowNumber);
                Cell cell = saveRow.createCell(3);
                cell.setCellValue(43);
                FileOutputStream outFile =new FileOutputStream(new File("C://Users//aklug//Desktop//Kumiko Project//KumikoMachine//Excel Sheets//Kumiko Example.xlsx"));

                currWorkbook.write(outFile);
                outFile.close();
            } else {
                 System.out.println("Now we here");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void findBlankCell() throws IOException {

        currInput = new FileInputStream("C://Users//aklug//Desktop//Kumiko Project//KumikoMachine//Excel Sheets//Kumiko Example.xlsx");
        currWorkbook = new XSSFWorkbook(currInput);
        currSheet = currWorkbook.getSheetAt(0);
        Cell cell;

        if (currBlankRowNumber == -1) {
            for (Row row : currSheet) {
                cell = row.getCell(3);
                if (cell.getStringCellValue().equals("")) {
                    currBlankRowNumber = cell.getRowIndex();
                    currInput.close();
                }
            }


        }

    }
}
