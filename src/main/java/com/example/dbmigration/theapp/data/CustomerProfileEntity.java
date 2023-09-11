package com.example.dbmigration.theapp.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.Optional;

@Entity(name = "CustomerProfile")
public class CustomerProfileEntity {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    public String getId() {
        return id;
    }

    public CustomerProfileEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CustomerProfileEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerProfileEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerProfileEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public Optional<AddressEntity> getAddress() {
        return Optional.ofNullable(address);
    }

    public CustomerProfileEntity setAddress(AddressEntity address) {
        this.address = address;
        return this;
    }
}
