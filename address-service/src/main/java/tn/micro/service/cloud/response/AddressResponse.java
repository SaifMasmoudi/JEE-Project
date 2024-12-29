package tn.micro.service.cloud.response;

import tn.micro.service.cloud.entity.Address;

public class AddressResponse {
	private long id;

	private String street;

	private String city;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public AddressResponse(long id, String street, String city) {
		this.id = id;
		this.street = street;
		this.city = city;
	}

	public AddressResponse() {
	}

	public AddressResponse(Address address) {
		this.city = address.getCity();
		this.street = address.getStreet();
		this.id = address.getId();
	}

}
