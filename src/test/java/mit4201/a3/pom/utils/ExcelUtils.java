package mit4201.a3.pom.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    private XSSFSheet sheet;
    private XSSFWorkbook workbook;

    public ExcelUtils(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
    }

    public String getCellData(int rowNum, int colNum) {
        DataFormatter formatter = new DataFormatter();
        Row row = sheet.getRow(rowNum);
        return formatter.formatCellValue(row.getCell(colNum));
    }
    public void closeWorkbook() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}