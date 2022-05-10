package com.example.demo.addressbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/addresses")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressBookService.getAllAddresses();
    }

    @GetMapping("/id/{id}")
    public Address getAddressById(@PathVariable("id") Integer id) {
        return addressBookService.getAddressById(id);
    }

    @GetMapping("/phoneNo/{phoneNo}")
    public Address getAddressByPhoneNo(@PathVariable Integer phoneNo) {
        return addressBookService.getAddressByPhoneNo(phoneNo);
    }

    @PostMapping // Shortform for @RequestMapping(method = RequestMethod.POST, value = "/addresses")
    public void addAddress(@RequestBody Address address) {
        addressBookService.addAddress(address);
    }

    @PutMapping("/id/{id}")
    public void updateAddress(@PathVariable Integer id, @RequestParam(required = false) String address, @RequestParam(required = false) Integer phoneNo) {
        addressBookService.updateAddress(id, address, phoneNo);
    }

    @DeleteMapping("/id/{id}")
    public void deleteAddress(@PathVariable Integer id) {
        addressBookService.deleteAddress(id);
    }
}
