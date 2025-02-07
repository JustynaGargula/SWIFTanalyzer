package app.controller;


import app.model.*;
import app.service.DatabaseService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DataEndpointsControllerTest {

    private DatabaseService databaseService = new DatabaseService();
    private DataEndpointsController dataEndpointsController = new DataEndpointsController(databaseService);
    @Test
    void getSingleSwiftCodeData() {
        String swiftCode = "TESETTTTXXX";
        ResponseEntity<Response> responseEntity = dataEndpointsController.getSingleSwiftCodeData(swiftCode);
        HeadquarterResponse response = (HeadquarterResponse) responseEntity.getBody();
        HeadquarterResponse expectedResponse = new HeadquarterResponse("Test 1 ", "Test 1", "TT", "Test", true, "TESETTTTXXX", new Response[0]);
        assertEquals(response.getSwiftCode(), expectedResponse.getSwiftCode());
        assertEquals(response.getAddress(), expectedResponse.getAddress());
        assertEquals(response.getBankName(), expectedResponse.getBankName());
        assertEquals(response.getCountryISO2(), expectedResponse.getCountryISO2());
        assertEquals(response.getCountryName(), expectedResponse.getCountryName());
        assertEquals(response.isHeadquarter(), expectedResponse.isHeadquarter());
        assertArrayEquals(response.getBranches(), expectedResponse.getBranches());
    }

    @Test
    void getCountrySwiftCodes() {
        String countryCode = "TT";
        ResponseEntity<CountriesResponse> responseEntity = dataEndpointsController.getCountrySwiftCodes(countryCode);
        CountriesResponse response = (CountriesResponse) responseEntity.getBody();
        BasicResponse response1 = new BasicResponse("Test 1", "Test 1", "TT", true, "TESETTTTXXX");
        BasicResponse response2 = new BasicResponse("Test 2", "Test 2", "TT", false, "TSTETTTTXYZ");
        BasicResponse[] countries = {response2, response1};
        CountriesResponse expectedResponse = new CountriesResponse(countryCode, "TEST", countries);
        assertEquals(response.getCountryISO2(), expectedResponse.getCountryISO2());
        assertEquals(response.getCountryName(), expectedResponse.getCountryName());
        assertEquals(response.getSwiftCodes().length, expectedResponse.getSwiftCodes().length);
    }

    @Test
    void addSwiftCode() {
        String swiftCode = "TESTTTTTXXX";
        ExtendedResponse request = new ExtendedResponse("Test", "Test", "TT", "TEST", true, "TESTTTTTXXX");
        String message = dataEndpointsController.addSwiftCode(request).getBody();
        String expectedMessage = "message: Successfully added data for SWIFT code "+swiftCode+" to the database";
        assertEquals(expectedMessage, message);
    }

    @Test
    void deleteSwiftCode() {
        String swiftCode = "TESTTTTTXXX";
        String message = dataEndpointsController.deleteSwiftCode(swiftCode).getBody();
        String expectedMessage = "message: Successfully deleted data for SWIFT code "+swiftCode;
        assertEquals(expectedMessage, message);
    }
}