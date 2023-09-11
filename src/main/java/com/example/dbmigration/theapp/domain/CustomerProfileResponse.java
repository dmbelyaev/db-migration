package com.example.dbmigration.theapp.domain;

import java.util.List;

public class CustomerProfileResponse {

    private final String id;

    private final String firstName;

    private final String lastName;

    private final String email;

    private List<Address> addresses;

    public CustomerProfileResponse(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public CustomerProfileResponse setAddresses(List<Address> addresses) {
        this.addresses = addresses;
        return this;
    }
}
