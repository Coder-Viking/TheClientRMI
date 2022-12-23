package data;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Contact {

	String stringForEmptyFields = "       ";
	@Override
	public String toString() {
		return name + ", " + surName + " - " + (address.isBlank() ? stringForEmptyFields : address) + " - "
				+ (postalCode.isBlank() ? stringForEmptyFields : postalCode) + " "
				+ (city.isBlank() ? stringForEmptyFields : city) + " - " + telephone + " - "
				+ (email.isBlank() ? stringForEmptyFields : email);
	}

	private Long id;
	private String name;
	private String surName;
	private String address;
	private String postalCode;
	private String city;
	private String telephone;
	private String email;

	@XmlElement
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	@XmlElement
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlElement
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@XmlElement
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@XmlElement
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
