package tn.micro.service.cloud.response;

import java.util.ArrayList;
import java.util.List;

import tn.micro.service.cloud.entity.Student;

public class StudentResponse {

	private long id;

	private String firstName;

	private String lastName;

	private String email;

	private String street;

	private String city;

	public StudentResponse(Student student) {
		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();

	
	}

	public StudentResponse(Student student, AddressResponse address) {
		// TODO Auto-generated constructor stub
		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();
		this.city=address.getCity();
		this.street=address.getStreet();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public static List<StudentResponse> toArrayList(List<Student> all) {
		List<StudentResponse> list = new ArrayList<>();
		for (Student student : all) {
			list.add( new StudentResponse(student));
		}
		return list;
	}

}
