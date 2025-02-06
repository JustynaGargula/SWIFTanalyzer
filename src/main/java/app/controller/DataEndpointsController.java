package app.controller;

import app.ExtendedResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/swift-codes")
public class DataEndpointsController {
    @GetMapping("/{swiftCode}")
    public  String getSingleSwiftCode(@PathVariable String swiftCode) {
        // TODO pobrać z bazy dane dla SwiftCode i jeśli to headquarter to znaleźć i dołączyć branches
        //TODO response o odpowiedniej strukturze
        return "Got SWIFTcode: " + swiftCode;
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
