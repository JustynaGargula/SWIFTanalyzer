package app.controller;

import app.model.BasicResponse;
import app.model.CountriesResponse;
import app.model.ExtendedResponse;
import app.model.Response;
import app.service.DatabaseService;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
        // TODO przerobić data na response, wstawić countryName
        if(data != null) {
            String countryName = data.first().getString("countryName");
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
    public void addSwiftCode(@RequestBody ExtendedResponse request){
        // TODO czy ExtendedResponse może być?, dodać do bazy dane z requesta
        //TODO response o odpowiedniej strukturze
    }
    @DeleteMapping("/{swiftCode}")
    public void deleteSwiftCode(@PathVariable String swiftCode){
        // TODO usunięcie danych dla swiftcode
        //TODO response o odpowiedniej strukturze
    }
}
