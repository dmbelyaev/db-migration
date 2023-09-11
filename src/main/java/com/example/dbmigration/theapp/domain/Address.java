package com.example.dbmigration.theapp.domain;

public class Address {

	private final String street;

	private final String city;

	private final String zipCode;

	private boolean main;

	public Address(String street, String city, String zipCode) {
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
		this.main = true;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public boolean isMain() {
		return main;
	}

	public Address setMain(boolean main) {
		this.main = main;
		return this;
	}
}
