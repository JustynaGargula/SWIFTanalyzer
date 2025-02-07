package app.service;

import app.Database;
import app.Response;
import app.model.ExtendedResponse;
import app.model.HeadquarterResponse;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DatabaseService {
    private MongoDatabase db;

    public DatabaseService() {
        // connecting to database
        Dotenv dotenv = Dotenv.load();
        String dbUri=dotenv.get("MONGO_URI");

        Database databaseConnector = new Database("SWIFTcodes",dbUri);
        databaseConnector.createDatabase();
        db = databaseConnector.getDatabase();
    }


    public Response getDataForSwiftCode(String swiftCode) {
        Document data;
        Response response = null;
        String countryCode = swiftCode.substring(4, 6);
        MongoCollection<Document> collection = db.getCollection(countryCode);
        data = collection.find(new Document("swiftCode", swiftCode)).first();
        if(data != null) {
            boolean isHeadquarter = data.getBoolean("isHeadquarter");
            if(isHeadquarter) {
                response = new HeadquarterResponse(data.getString("address"), data.getString("bankName"), data.getString("countryISO2"), data.getString("countryName"), isHeadquarter, swiftCode, new Response[0]);
                ArrayList<Response> branches = new ArrayList<>();

                // TODO znaleźć powiązane branche i zrobić setBranches na repsonse
            }
            else{
                response = new ExtendedResponse(data.getString("address"), data.getString("bankName"), data.getString("countryISO2"), data.getString("countryName"), isHeadquarter, swiftCode);
            }
        }
        System.out.println("Zwracam responce: "+response);
        return response;
    }
}
