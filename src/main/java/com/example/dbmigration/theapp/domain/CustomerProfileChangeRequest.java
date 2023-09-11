package com.example.dbmigration.theapp.domain;

/**
 * Request to update/change the customer profile. Only first and lastName can be changed.
 */
public class CustomerProfileChangeRequest {

    private final String firstName;

    private final String lastName;

    public CustomerProfileChangeRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
