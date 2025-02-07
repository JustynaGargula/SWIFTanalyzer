package app;

import app.model.ExtendedResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class Main {
    private static DatabaseConnector db;

    public static void main(String[] args) {

        // connecting to database //<-- currently handled by DatabaseService for REST connections
//        Dotenv dotenv = Dotenv.load();
//        String dbUri=dotenv.get("MONGO_URI");
//        db = new DatabaseConnector("SWIFTcodes", dbUri);
//        db.connectToDatabase();

        // parsing data from .xlsx file and sending it to database
        //parseAndSendData();   //<-- currently omitted as data is already in the database

        // starting REST API
        SpringApplication.run(Main.class, args);
    }

    public static void parseAndSendData(){
        final String fileLocation = "../Interns_2025_SWIFT_CODES.xlsx";
        XlsxDataParser xlsxDataParser = new XlsxDataParser(fileLocation);
        HashMap <String[], HashMap<String, ExtendedResponse>> data;

        // parsing data from .xlsx file
        xlsxDataParser.parseData();
        data = xlsxDataParser.getParsedData();

        db.sendDataFromXlsxFile(data);
    }
}