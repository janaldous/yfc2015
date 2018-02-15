package yfcdb.member;

/**
 * Created by janaldoustorres on 19/05/15.
 */
public class Address {
    private String street, city, village, postalCode;

    public Address() {}

    public Address(String street, String city, String village, String postalCode) {
        this.street = street;
        this.city = city;
        this.village = village;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
