package app;

public class HeadquarterResponse implements Response{
    /*
    This implementation of Response contains 7 fields:
        "address": string,
        "bankName": string,
        "countryISO2": string,
        "countryName": string,
        "isHeadquarter": bool,
        "swiftCode": string
        “branches”: [...]
     It is used to sore headquarters information.
     */
    private String address;
    private String bankName;
    private String countryISO2;
    private String countryName;
    private boolean isHeadquarter;
    private String swiftCode;
    private Response[] branches;

    public HeadquarterResponse(String address, String bankName, String countryISO2, String countryName, boolean isHeadquarter, String swiftCode, Response[] branches) {
        this.address = address;
        this.bankName = bankName;
        this.countryISO2 = countryISO2;
        this.countryName = countryName;
        this.isHeadquarter = isHeadquarter;
        this.swiftCode = swiftCode;
        this.branches = branches;
    }

    public String getAddress() {
        return address;
    }

    public HeadquarterResponse setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public HeadquarterResponse setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public String getCountryISO2() {
        return countryISO2;
    }

    public HeadquarterResponse setCountryISO2(String countryISO2) {
        this.countryISO2 = countryISO2;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public HeadquarterResponse setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public boolean isHeadquarter() {
        return isHeadquarter;
    }

    public HeadquarterResponse setHeadquarter(boolean headquarter) {
        isHeadquarter = headquarter;
        return this;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public HeadquarterResponse setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
        return this;
    }

    public Response[] getBranches() {
        return branches;
    }

    public HeadquarterResponse setBranches(Response[] branches) {
        this.branches = branches;
        return this;
    }
}
