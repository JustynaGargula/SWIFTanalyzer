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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XlsxDataParser {
    private String fileLocation="";

    public XlsxDataParser(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    // dataForCountryCode: dictionary of data for country code (and country name), containing dictionary of swift codes and parsed values (in BasicResponse object)
    private HashMap <String[], HashMap<String, BasicResponse>> dataForCountryCode = new HashMap<>();

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
        sheet.removeRow(sheet.getRow(0));
        for (Row row: sheet){
            String countryCode = row.getCell(0).getStringCellValue();
            String swiftCode = row.getCell(1).getStringCellValue();
            String bankName = row.getCell(3).getStringCellValue();
            String address = row.getCell(4).getStringCellValue();
            String countryName = row.getCell(6).getStringCellValue();
            Pattern headquarterPattern = Pattern.compile(".{8}(XXX)\\b");
            Matcher headquarterMatcher = headquarterPattern.matcher(swiftCode);

            boolean isHeadquarter = headquarterMatcher.find();
            BasicResponse parsedRow = new BasicResponse(address, bankName, countryCode, isHeadquarter, swiftCode);
            if( ! dataForCountryCode.containsKey(new String[]{countryCode, countryName})){
                HashMap<String, BasicResponse> rowData = new HashMap<>();
                rowData.put(swiftCode, parsedRow);
                dataForCountryCode.put(new String[]{countryCode, countryName}, rowData);
            }
            else {
                dataForCountryCode.get(new String[]{countryCode, countryName}).put(swiftCode, parsedRow);
            }

        }
    }
    public HashMap<String[], HashMap<String, BasicResponse>> getParsedData(){
        return dataForCountryCode;
    }

}
