package model;

import beans.RegisterRequestBean;
import model.dao.CustomerDataDAO;
import model.dao.LoginDataDAO;
import model.entities.AddressModel;
import model.entities.CustomerDataModel;
import model.entities.LoginDataModel;

public class CustomerInteractionModel {
    private final AddressModel address = new AddressModel();
	private final CustomerDataModel customer = new CustomerDataModel();
	private final LoginDataModel login = new LoginDataModel();
    
    public boolean login(String username, String password) {
        LoginDataModel loginData = new LoginDataDAO().readData(username);
        return (loginData!= null && loginData.getPassword().equals(password));
    }

	public boolean register(RegisterRequestBean requestBean) {
		this.mapEntityParams(requestBean);
		return new CustomerDataDAO().writeData(this.customer);
	}
	
	private void mapEntityParams(RegisterRequestBean requestBean) {
		//Mapping address entity
		address.setAddressLine1(requestBean.getAddressLine1());
		if(!(requestBean.getAddressLine2().isEmpty() || requestBean.getAddressLine2() == null)) {
			address.setAddressLine1(requestBean.getAddressLine2());
		}
		address.setCity(requestBean.getCity());
		address.setCountry(requestBean.getCountry());
		address.setPostcode(requestBean.getPostcode());
		address.setState(requestBean.getState());
		
		//Mapping login entity
		login.setUsername(requestBean.getUsername());
		login.setPassword(requestBean.getPassword());
		
		//Mapping customerdata entity
		customer.setAddress(address);
		customer.setFirstName(requestBean.getFirstName());
		customer.setLastName(requestBean.getLastName());
		customer.setContactNumber(requestBean.getPhone());
		customer.setLogindata(login);
	}
}
