package persistenceLayer.model;

import persistenceLayer.model.AddressModel;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(schema = "LMS", name = "CUSTOMERDATA")
@SequenceGenerator(name = "cust_seq", sequenceName = "CUSTOMERDATA_SEQ1")
public class CustomerDataModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cust_seq")
	@Column(name = "CUSTOMERID")
	private long customerId;
	
	@Column(name = "FIRSTNAME")
	private String firstName;
	
	@Column(name = "LASTNAME")
	private String lastName;
	
	@Column(name = "CONTACTNUMBER")
	private long contactNumber;
	
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ADDRESSID", referencedColumnName = "ADDRESSID")
	private AddressModel address;
	
    @OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
    private LoginDataModel logindata;

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
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

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public AddressModel getAddress() {
		return address;
	}

	public void setAddress(AddressModel address) {
		this.address = address;
	}

	public LoginDataModel getLogindata() {
		return logindata;
	}

	public void setLogindata(LoginDataModel logindata) {
		this.logindata = logindata;
	}
}
