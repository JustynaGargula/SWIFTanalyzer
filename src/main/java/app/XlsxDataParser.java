package app;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class XlsxDataParser {
    private String fileLocation="";

    public XlsxDataParser(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    // dataForCountryCode: dictionary of data for country code, containing list of dictionaries of swift codes and parsed values (in BasicResponse object)
    private HashMap <String, ArrayList<HashMap<String, BasicResponse>>> dataForCountryCode = new HashMap<>();

    public void parseData(){
        FileInputStream file;
        Workbook workbook;
        try {
            file = new FileInputStream(new File(fileLocation));
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            System.out.println("ERROR: Couldn't read Excel file: "+fileLocation);
            return;
            //throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row: sheet){
            String countryCode = row.getCell(0).getStringCellValue();
            // TODO: parse necessary cells and create BasicResponse object with them
            if(dataForCountryCode.containsKey(countryCode)){
                // TODO: add only new value to list
            }
            else{
                // TODO: add new key: value pair
            }
        }
    }
    public HashMap<String, ArrayList<HashMap<String, BasicResponse>>> getParsedData(){
        return dataForCountryCode;
    }

}
