package app;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        final String fileLocation = "../SWIFT_CODES_shorter.xlsx";
        XlsxDataParser xlsxDataParser = new XlsxDataParser(fileLocation);
        HashMap <String[], HashMap<String, BasicResponse>> data;

        xlsxDataParser.parseData();
        data = xlsxDataParser.getParsedData();

        Dotenv dotenv = Dotenv.load();
        String dbUri=dotenv.get("MONGO_URI");
        Database db = new Database("SWIFTcodes", dbUri, data);
        db.createDatabase();
    }
}