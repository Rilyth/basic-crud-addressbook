package com.example.demo.addressbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
    JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository.

    Their main functions are:

    CrudRepository mainly provides CRUD functions.
    PagingAndSortingRepository provides methods to do pagination and sorting records.
    JpaRepository provides some JPA-related methods such as flushing the persistence context and deleting records in a batch.
 */

@Repository
public interface AddressBookRepository extends JpaRepository<Address, Integer> {

    @Query("select a from Address a where a.phoneNo = :phoneNo")
    public Optional<Address> findAddressByPhoneNo(Integer phoneNo);

    @Query("select a from Address a where a.address = :address")
    public Optional<Address> findAddressByAddress(String address);
}
