package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS", schema="LMS")
@SequenceGenerator(name = "add_seq", sequenceName = "ADDRESS_SEQ")
public class AddressModel {
	
	@Id
	@Column(name = "ADDRESSID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "add_seq")
	private int addressId;
	
	@Column(name = "ADDRESS_LINE1")
	private String addressLine1;
	
	@Column(name = "ADDRESS_LINE2")
	private String addressLine2;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "POSTCODE")
	private String postcode;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "COUNTRY")
	private String country;
	
	public int getAddressId() {
		return addressId;
	}
	
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
	public String getAddressLine1() {
		return addressLine1;
	}
	
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	public String getAddressLine2() {
		return addressLine2;
	}
	
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
