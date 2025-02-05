package app;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        final String fileLocation = "../Interns_2025_SWIFT_CODES.xlsx";
        XlsxDataParser xlsxDataParser = new XlsxDataParser(fileLocation);
        HashMap <String[], HashMap<String, BasicResponse>> data;

        // parsing data from .xlsx file
        xlsxDataParser.parseData();
        data = xlsxDataParser.getParsedData();

        // connecting to database
        Dotenv dotenv = Dotenv.load();
        String dbUri=dotenv.get("MONGO_URI");
        Database db = new Database("SWIFTcodes", dbUri, data);
        db.createDatabase();
        db.sendDataFromXlsxFile();
    }
}