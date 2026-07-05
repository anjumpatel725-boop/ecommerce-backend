package com.ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full Name is required")
private String fullName;

@NotBlank(message = "Mobile number is required")
@Pattern(
    regexp = "^[0-9]{10}$",
    message = "Mobile number must be exactly 10 digits"
)
private String mobile;

@NotBlank(message = "House is required")
private String house;

@NotBlank(message = "Street is required")
private String street;

@NotBlank(message = "City is required")
private String city;

@NotBlank(message = "State is required")
private String state;

@NotBlank(message = "Country is required")
private String country;

@NotBlank(message = "Pincode is required")
@Pattern(
    regexp = "^[0-9]{6}$",
    message = "Pincode must be exactly 6 digits"
)
private String pincode;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Address() {}

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}