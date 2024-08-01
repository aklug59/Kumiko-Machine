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

    //Variable declarations
    public static FileInputStream currFile;
    public static XSSFWorkbook currWorkbook;
    public static XSSFSheet currSheet;
    private static final String filePath = "C://Users//aklug//Desktop//Kumiko Project//KumikoMachine//Excel Sheets//Kumiko Example.xlsx";
    public FileReader() throws FileNotFoundException {}

    /**Get the name of the project from the Excel File.*/
    public static String getProjectName() {

            try {
                currFile = new FileInputStream(filePath);
                currWorkbook = new XSSFWorkbook(currFile);
                currSheet = currWorkbook.getSheetAt(0);
                Iterator<Row> rowIterator = currSheet.rowIterator();

                Row saveRow = currSheet.getRow(0);
                Cell cell = saveRow.getCell(0);
                return cell.getStringCellValue();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

}
