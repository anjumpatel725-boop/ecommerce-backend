package com.ecommerce.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalAmount;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

   @JsonManagedReference
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
private List<OrderItem> items;

// Delivery Address
private String fullName;
private String mobile;
private String house;
private String street;
private String city;
private String state;
private String country;
private String pincode;
    
    public Order() {}

    public Long getId() {
        return id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
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
}