package app.service;

import app.DatabaseConnector;
import app.model.Response;
import app.model.BasicResponse;
import app.model.ExtendedResponse;
import app.model.HeadquarterResponse;
import com.mongodb.client.FindIterable;
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

        DatabaseConnector databaseConnector = new DatabaseConnector("SWIFTcodes",dbUri);
        databaseConnector.connectToDatabase();
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
                for(Document document: collection.find()) {
                    String otherSwiftCode = document.getString("swiftCode");
                    if(otherSwiftCode.substring(0,8).equals(swiftCode.substring(0,8)) && !otherSwiftCode.equals(swiftCode)) {
                        branches.add(new BasicResponse(document.getString("address"), document.getString("bankName"), document.getString("countryISO2"), document.getBoolean("isHeadquarter"), otherSwiftCode));
                    }
                }
                ((HeadquarterResponse) response).setBranches(branches.toArray(new Response[0]));
            }
            else{
                response = new ExtendedResponse(data.getString("address"), data.getString("bankName"), data.getString("countryISO2"), data.getString("countryName"), isHeadquarter, swiftCode);
            }
        }
        return response;
    }
    public FindIterable<Document> getDataForCountry(String countryISO2) {
        return db.getCollection(countryISO2).find();
    }
}
