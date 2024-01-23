package Helper;

import Configuration.ConfigurationFile;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataReaderExcelSheet {
    static FileInputStream fis = null;
    ConfigurationFile config;
    public FileInputStream getFileInputStream()
    {
        String FilePath = System.getProperty("user.dir")+"/src/test/java/TestingData/Test Data.xlsx";
        File SrcFile = new File(FilePath);
        try {
            fis = new FileInputStream(SrcFile);
        } catch (FileNotFoundException e) {
            System.out.println("Test Data File not found. Check File path of the Test Data : "+ e.getMessage());
            System.exit(0);
        }
        return fis;
    }
    public Object [][] getExcelData(int SheetIndex) throws IOException {
        fis = getFileInputStream();
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(SheetIndex);
        int TotalNumberOfRows = sheet.getLastRowNum()+1;
        int TotalNumberOfCols = sheet.getRow(0).getLastCellNum();
        String [][] ArrayOfExcelData = new String[TotalNumberOfRows][TotalNumberOfCols];
        for (int i = 0; i < TotalNumberOfRows; i++)
        {
            for(int j=0 ; j<TotalNumberOfCols; j++)
            {
                XSSFRow row = sheet.getRow(i);
                ArrayOfExcelData[i][j] = row.getCell(j).toString();
            }
        }
        wb.close();
        return ArrayOfExcelData;
    }
}