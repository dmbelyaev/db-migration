package com.example.dbmigration.theapp.domain;

public class CustomerProfileCreateRequest {

    private final String firstName;

    private final String lastName;

    private final String email;

    private final Address address;

    public CustomerProfileCreateRequest(String firstName, String lastName, String email, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }
}
