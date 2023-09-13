package com.example.dbmigration.theapp.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.Collections;
import java.util.List;

@Entity(name = "CustomerProfile")
public class CustomerProfileEntity {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private List<AddressEntity> addresses;

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

    public List<AddressEntity> getAddresses() {
        return addresses == null ? Collections.emptyList() : addresses;
    }

    public CustomerProfileEntity setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
        return this;
    }
}
