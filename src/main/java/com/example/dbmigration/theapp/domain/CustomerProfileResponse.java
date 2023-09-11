package com.example.dbmigration.theapp.domain;

public class CustomerProfileResponse {

    private final String id;

    private final String firstName;

    private final String lastName;

    private final String email;

    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public CustomerProfileResponse setAddress(Address address) {
        this.address = address;
        return this;
    }
}
