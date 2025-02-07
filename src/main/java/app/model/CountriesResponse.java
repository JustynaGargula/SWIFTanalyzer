package app.model;

public class CountriesResponse {
    /*
    Contains fields:
        "countryISO2": string,
        "countryName": string,
        "swiftCodes": [BasicResponse objects]
     */
    String countryISO2;
    String countryName;
    BasicResponse[] swiftCodes;

    public CountriesResponse(String countryISO2, String countryName, BasicResponse[] swiftCodes) {
        this.countryISO2 = countryISO2;
        this.countryName = countryName;
        this.swiftCodes = swiftCodes;
    }

    public String getCountryISO2() {
        return countryISO2;
    }

    public String getCountryName() {
        return countryName;
    }

    public BasicResponse[] getSwiftCodes() {
        return swiftCodes;
    }

    public void setSwiftCodes(BasicResponse[] swiftCodes) {
        this.swiftCodes = swiftCodes;
    }
}