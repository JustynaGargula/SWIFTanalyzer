package app;


import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        final String fileLocation = "../SWIFT_CODES_shorter.xlsx";
        XlsxDataParser xlsxDataParser = new XlsxDataParser(fileLocation);
        HashMap <String, ArrayList<HashMap<String, BasicResponse>>> data;

        xlsxDataParser.parseData();
        data = xlsxDataParser.getParsedData();
    }
}