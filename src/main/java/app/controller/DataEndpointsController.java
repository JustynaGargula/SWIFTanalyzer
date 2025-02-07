package app.controller;

import app.model.BasicResponse;
import app.model.CountriesResponse;
import app.model.ExtendedResponse;
import app.model.Response;
import app.service.DatabaseService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/v1/swift-codes")
public class DataEndpointsController {
    private final DatabaseService databaseService;

    public DataEndpointsController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/{swiftCode}")
    public ResponseEntity<Response> getSingleSwiftCode(@PathVariable String swiftCode) {
        return ResponseEntity.ok(databaseService.getDataForSwiftCode(swiftCode));
    }

    @GetMapping("/country/{countryISO2code}")
    public  ResponseEntity<CountriesResponse> getCountrySwiftCodes(@PathVariable String countryISO2code) {
        CountriesResponse response;
        FindIterable<Document> data = databaseService.getDataForCountry(countryISO2code);
        if(data != null) {
            String countryName = Objects.requireNonNull(data.first()).getString("countryName");
            response = new CountriesResponse(countryISO2code, countryName, new BasicResponse[0]);
            ArrayList<BasicResponse> swiftCodes = new ArrayList<>();
            for(Document document : data) {
                swiftCodes.add(new BasicResponse(document.getString("address"), document.getString("bankName"), document.getString("countryISO2"), document.getBoolean("isHeadquarter"), document.getString("swiftCode")));
            }
            response.setSwiftCodes(swiftCodes.toArray(new BasicResponse[0]));
        } else {
            return null;
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> addSwiftCode(@RequestBody ExtendedResponse request){
        String message = databaseService.addDataToDatabase(request);
        return ResponseEntity.ok("message: "+message);
    }
    @DeleteMapping("/{swiftCode}")
    public ResponseEntity<String> deleteSwiftCode(@PathVariable String swiftCode){
        String message = databaseService.deleteDataFromDatabase(swiftCode);
        return ResponseEntity.ok("message: "+message);
    }
}
