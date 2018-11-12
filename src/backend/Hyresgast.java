package backend;

import java.io.Serializable;

public class Hyresgast implements Serializable {
	int id;
	private String firstName;
	private String lastName;
	private String personNumber;
	private String phone_number;
	private String email;
	private String apartmentNumber;
	public Hyresgast(int id, String firstName, String lastName, String personNumber, String phone_number,
			String email, String apartmentNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.personNumber = personNumber;
		this.phone_number = phone_number;
		this.email = email;
		this.apartmentNumber = apartmentNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getPersonNumber() {
		return personNumber;
	}
	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getApartmentNumber() {
		return apartmentNumber;
	}
	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	@Override
	public String toString() {
		return "Hyresgaster [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", personNumber="
				+ personNumber + ", phone_number=" + phone_number + ", email=" + email + ", apartmentNumber="
				+ apartmentNumber + "]";
	}

}
