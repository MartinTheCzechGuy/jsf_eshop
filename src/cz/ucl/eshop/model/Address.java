package cz.ucl.eshop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String country;
    private String city;
    private String street;
    @Column(name = "house_number")
    private int houseNumber;
    private int zip;
    @ManyToMany(mappedBy = "addresses")
    private List<Customer> customers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
}
