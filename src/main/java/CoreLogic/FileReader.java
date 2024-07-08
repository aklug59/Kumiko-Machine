package CoreLogic;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
public class FileReader {
    public static FileInputStream currFile;
    public static XSSFWorkbook currWorkbook;
    public static XSSFSheet currSheet;
    public static void sheetRead() {
        {
            try {
                currFile = new FileInputStream("C://Users//aklug//Desktop//Kumiko Project//KumikoMachine//Excel Sheets//Kumiko Example.xlsx");
                currWorkbook = new XSSFWorkbook(currFile);
                currSheet = currWorkbook.getSheetAt(0);
                Iterator<Row> rowIterator = currSheet.rowIterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while(cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        System.out.println(cell.getStringCellValue());
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public FileReader() throws FileNotFoundException {
    }
}
