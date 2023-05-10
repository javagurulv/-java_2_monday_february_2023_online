package lv.javaguru.java2.servify.domain;

import lombok.Data;

@Data
public class Address {

    private String country;
    private String city;
    private String street;
    private int homeNumber;
    private int apartmentNumber;
    private String postalCode;


}
