package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BasicResponse implements Response {
    /*
    This implementation of Response contains 5 fields:
        "address": string,
        "bankName": string,
        "countryISO2": string,
        "isHeadquarter": bool,
        "swiftCode": string
    It is used in list of branches for a headquarters.
     */
    private String address;
    private String bankName;
    private String countryISO2;
    private boolean isHeadquarter;
    private String swiftCode;
    private final String type="basic";

    public BasicResponse(String address, String bankName, String countryISO2, boolean isHeadquarter, String swiftCode) {
        this.address = address;
        this.bankName = bankName;
        this.countryISO2 = countryISO2.toUpperCase();
        this.isHeadquarter = isHeadquarter;
        this.swiftCode = swiftCode;
    }

    public String getAddress() {
        return address;
    }

    public BasicResponse setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public BasicResponse setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getCountryISO2() {
        return countryISO2;
    }

    public BasicResponse setCountryISO2(String countryISO2) {
        this.countryISO2 = countryISO2;
        return this;
    }

    public boolean isHeadquarter() {
        return isHeadquarter;
    }

    public BasicResponse setHeadquarter(boolean headquarter) {
        isHeadquarter = headquarter;
        return this;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public BasicResponse setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
        return this;
    }

    @JsonIgnore
    public String getType() {
        return type;
    }
}
