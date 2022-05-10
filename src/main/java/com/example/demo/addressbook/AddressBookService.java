package com.example.demo.addressbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

//    private final List<Address> addressList = new ArrayList<>(List.of(new Address(1, "Jack", "42 Clover Avenue", 97108826), new Address(2, "Jill", "Jln Pemimpin", 97557904)));

    public List<Address> getAllAddresses() {
        return addressBookRepository.findAll();
    }

    public Address getAddressById(Integer id) {
        Optional<Address> address = addressBookRepository.findById(id);
        if (address.isPresent()) {
            return address.get();
        }
        throw new IllegalArgumentException("Id doesn't exist!");
    }

    public Address getAddressByPhoneNo(Integer phoneNo) {
        Optional<Address> address = addressBookRepository.findAddressByPhoneNo(phoneNo);
        if (address.isPresent()) {
            return address.get();
        }
        throw new IllegalArgumentException("Email doesn't exist!");
    }

    public void addAddress(Address address) {
        // Base Cases
        if (addressBookRepository.findAddressByAddress(address.getAddress()).isPresent()) {
            throw new IllegalArgumentException("Someone already lives at " + address);
        }

        if (addressBookRepository.findAddressByPhoneNo(address.getPhoneNo()).isPresent() || address.getPhoneNo() < 10000000 || address.getPhoneNo() > 99999999) {
            throw new IllegalArgumentException("Phone number is already in use or invalid!");
        }

        Optional<Address> temp = addressBookRepository.findById(address.getId());
        if (temp.isPresent()) {
            throw new IllegalArgumentException("Id: " + address.getId() + " already exists!");
        }

        addressBookRepository.save(address);
    }

    @Transactional
    public void updateAddress(Integer id, String address, Integer phoneNo) {

        // Base Cases
        if (address != null && addressBookRepository.findAddressByAddress(address).isPresent()) {
            throw new IllegalArgumentException("Someone already lives at " + address);
        }

        if (phoneNo != null && (addressBookRepository.findAddressByPhoneNo(phoneNo).isPresent() || phoneNo < 10000000 || phoneNo > 99999999)) {
            throw new IllegalArgumentException("Phone number is already in use or invalid!");
        }

        Optional<Address> temp = addressBookRepository.findById(id);
        if (temp.isEmpty()) {
            throw new IllegalArgumentException("Id: " + id + " does not exist!");
        }

        // Updating Address
        Address tempAddress = temp.get();

        if (address != null) {
            tempAddress.setAddress(address);
        }

        if (phoneNo != null) {
            tempAddress.setPhoneNo(phoneNo);
        }

        addressBookRepository.save(tempAddress);
    }

    public void deleteAddress(Integer id) {
        addressBookRepository.deleteById(id);
    }


}
