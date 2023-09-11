package com.example.dbmigration.theapp.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "Address")
public class AddressEntity {

	@Id
	private String id;

	private String street;

	private String city;

	private String zipCode;

	public String getId() {
		return id;
	}

	public AddressEntity setId(String id) {
		this.id = id;
		return this;
	}

	public String getStreet() {
		return street;
	}

	public AddressEntity setStreet(String street) {
		this.street = street;
		return this;
	}

	public String getCity() {
		return city;
	}

	public AddressEntity setCity(String city) {
		this.city = city;
		return this;
	}

	public String getZipCode() {
		return zipCode;
	}

	public AddressEntity setZipCode(String zipCode) {
		this.zipCode = zipCode;
		return this;
	}
}
