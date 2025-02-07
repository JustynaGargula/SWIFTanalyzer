package app;

import app.model.ExtendedResponse;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;

public class DatabaseConnector {
    private final String dbName;
    private final String connectionString;
    MongoDatabase database;
    MongoClient mongoClient;

    public DatabaseConnector(String dbName, String connectionString) {
        this.dbName = dbName;
        this.connectionString = connectionString;
        // connectionString looks like that: "mongodb+srv://userName:dbPassword@cluster0.rnpbj.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void connectToDatabase(){
        try{
            ServerApi serverApi = ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build();
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .serverApi(serverApi)
                    .build();
            mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase(dbName);
            System.out.println("Connected to database");
        } catch(Exception e){
            System.out.printf("ERROR: couldn't connect to database. Details: %s\n", e.getMessage());
        }
    }

    public void sendDataFromXlsxFile(HashMap <String[], HashMap<String, ExtendedResponse>> data) {
        try {
            MongoCollection<Document> collection;
            ExtendedResponse response;
            Document doc;

            for(String[] key : data.keySet()) {
                String countryCode = key[0];
                collection = database.getCollection(countryCode);
                for(String swiftCode: data.get(key).keySet()) {
                    if(collection.find(new Document("swiftCode", swiftCode)).first() == null) {
                        response = data.get(key).get(swiftCode);
                        doc = new Document("address", response.getAddress())
                                .append("bankName", response.getBankName())
                                .append("countryISO2", response.getCountryISO2())
                                .append("countryName", response.getCountryName())
                                .append("isHeadquarter", response.isHeadquarter())
                                .append("swiftCode", swiftCode);
                        collection.insertOne(doc);
                    }
                }
            }
            mongoClient.close();
            System.out.println("Finished sending data to database");
        } catch (Exception e) {
            System.out.printf("ERROR: couldn't send data to database. Details: %s\n", e.getMessage());
        }
    }
}
