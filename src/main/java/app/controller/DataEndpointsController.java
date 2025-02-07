package app.controller;

import app.model.ExtendedResponse;
import app.Response;
import app.service.DatabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public  void getCountrySwiftCodes(@PathVariable String countryISO2code) {
        // TODO pobrać z bazy  SwiftCode'y dla kraju i zwrócić jes w odpowiednim formacie
        //TODO response o odpowiedniej strukturze
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
