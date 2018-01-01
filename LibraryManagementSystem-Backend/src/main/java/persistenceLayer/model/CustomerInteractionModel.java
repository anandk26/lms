package persistenceLayer.model;

import beans.services.customerservice.register.RegisterRequestBean;

import persistenceLayer.dao.CustomerDataDAO;
import persistenceLayer.dao.LoginDataDAO;
import persistenceLayer.entities.AddressModel;
import persistenceLayer.entities.CustomerDataModel;
import persistenceLayer.entities.LoginDataModel;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class CustomerInteractionModel {

    @Autowired
    private AddressModel address;
    @Autowired
	private CustomerDataModel customer;
    @Autowired
	private LoginDataModel loginData;
    @Autowired
	private LoginDataDAO loginDAO;
    @Autowired
	private CustomerDataDAO customerDAO;

    
    public boolean login(String username, String password) {
        this.loginData = this.loginDAO.readData(username);
        return (this.loginData!= null && this.loginData.getPassword().equals(password));
    }

	public boolean register(RegisterRequestBean requestBean) {
		this.mapEntityParams(requestBean);
		return this.customerDAO.writeData(this.customer);
	}
    
    public CustomerDataModel getCustomerDetails(String username) {
        return this.customerDAO.readData(username);
    }
	
	private void mapEntityParams(RegisterRequestBean requestBean) {
		//Mapping address entity
		this.address.setAddressLine1(requestBean.getAddressLine1());
		if(!(requestBean.getAddressLine2().isEmpty() || requestBean.getAddressLine2() == null)) {
			this.address.setAddressLine2(requestBean.getAddressLine2());
		}
		this.address.setCity(requestBean.getCity());
		this.address.setCountry(requestBean.getCountry());
		this.address.setPostcode(requestBean.getPostcode());
		this.address.setState(requestBean.getState());
		
		//Mapping login entity
		this.loginData.setUsername(requestBean.getUsername());
		this.loginData.setPassword(requestBean.getPassword());
		
		//Mapping customerdata entity
		this.customer.setAddress(this.address);
		this.customer.setFirstName(requestBean.getFirstName());
		this.customer.setLastName(requestBean.getLastName());
		this.customer.setContactNumber(requestBean.getPhone());
		this.customer.setLogindata(this.loginData);
	}
}
