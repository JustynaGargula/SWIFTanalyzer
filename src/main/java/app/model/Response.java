package app.model;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "swiftCode")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ExtendedResponse.class, name = "extended"),
        @JsonSubTypes.Type(value = HeadquarterResponse.class, name = "headquarter"),
        @JsonSubTypes.Type(value = BasicResponse.class, name = "basic")
})
public interface Response {
    String address = "";
    String bankName = "";
    String countryISO2 = "";
    boolean isHeadquarter = false;
    String swiftCode = "";

    String getAddress();
    String getBankName();
    String getCountryISO2();
    boolean isHeadquarter();
    String getSwiftCode();

}
