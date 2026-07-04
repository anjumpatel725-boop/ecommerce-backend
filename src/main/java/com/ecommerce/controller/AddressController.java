package com.ecommerce.controller;

import com.ecommerce.model.Address;
import com.ecommerce.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
@CrossOrigin(origins = "*")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // Save Address
    @PostMapping("/{userId}")
    public Address saveAddress(
            @PathVariable Long userId,
            @RequestBody Address address) {

        return addressService.saveAddress(userId, address);
    }

    // Get Addresses
    @GetMapping("/{userId}")
    public List<Address> getAddresses(@PathVariable Long userId) {
        return addressService.getAddresses(userId);
    }

    // Delete Address
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        addressService.deleteAddress(id);

        return "Address Deleted";
    }
}