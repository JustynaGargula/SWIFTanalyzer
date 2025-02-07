package app.model;

import app.Response;

public class ExtendedResponse implements Response {
    /*
    This implementation of Response contains 6 fields:
        "address": string,
        "bankName": string,
        "countryISO2": string,
        "countryName": string,
        "isHeadquarter": bool,
        "swiftCode": string
     It is used to add new SWIFT code.
     */
    private String address;
    private String bankName;
    private String countryISO2;
    private String countryName;
    private boolean isHeadquarter;
    private String swiftCode;

    public ExtendedResponse(String address, String bankName, String countryISO2, String countryName, boolean isHeadquarter, String swiftCode) {
        this.address = address;
        this.bankName = bankName;
        this.countryISO2 = countryISO2;
        this.countryName = countryName;
        this.isHeadquarter = isHeadquarter;
        this.swiftCode = swiftCode;
    }

    public String getAddress() {
        return address;
    }

    public ExtendedResponse setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public ExtendedResponse setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getCountryISO2() {
        return countryISO2;
    }

    public ExtendedResponse setCountryISO2(String countryISO2) {
        this.countryISO2 = countryISO2;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public ExtendedResponse setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public boolean isHeadquarter() {
        return isHeadquarter;
    }

    public ExtendedResponse setHeadquarter(boolean headquarter) {
        isHeadquarter = headquarter;
        return this;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public ExtendedResponse setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
        return this;
    }
}
