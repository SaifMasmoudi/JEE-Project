package tn.micro.service.cloud.request;

public class CreateAddressRequest {

	private String street;

	private String city;

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

	public CreateAddressRequest(String street, String city) {
		this.street = street;
		this.city = city;
	}

	public CreateAddressRequest() {
	}

}
