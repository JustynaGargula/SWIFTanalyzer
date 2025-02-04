package app;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;

public class Database {
    private final String dbName;
    private final String connectionString;
    HashMap <String[], HashMap<String, BasicResponse>> data;

    public Database(String dbName, String connectionString, HashMap <String[], HashMap<String, BasicResponse>> data) {
        this.dbName = dbName;
        this.connectionString = connectionString;
        // connectionString looks like that: "mongodb+srv://userName:dbPassword@cluster0.rnpbj.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        this.data = data;
    }

    public void createDatabase() {
        try {
            ServerApi serverApi = ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build();
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .serverApi(serverApi)
                    .build();
            MongoClient mongoClient = MongoClients.create(settings);
            MongoDatabase database = mongoClient.getDatabase(dbName);
            System.out.println("Connected to database");
            MongoCollection<Document> collection;
            BasicResponse response;
            Document doc;
            for(String[] key : data.keySet()) {
                String countryCode = key[0];
                String countryName = key[1];
                collection = database.getCollection(countryCode+"-"+countryName);
                for(String swiftCode: data.get(key).keySet()) {
                    if(collection.find(new Document("swiftCode", swiftCode)).first() == null) {
                        response = data.get(key).get(swiftCode);
                        doc = new Document("address", response.getAddress())
                                .append("bankName", response.getBankName())
                                .append("countryISO2", response.getCountryISO2())
                                .append("isHeadquarter", response.isHeadquarter())
                                .append("swiftCode", swiftCode);
                        collection.insertOne(doc);
                    }
                }
            }
            mongoClient.close();
        } catch (Exception e) {
            System.out.printf("ERROR: couldn't connect to database. Details: %s\n", e.getMessage());
        }
        System.out.println("Finished connecting to database");
    }
}
