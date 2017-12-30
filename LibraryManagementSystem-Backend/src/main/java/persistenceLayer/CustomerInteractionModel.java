package persistenceLayer;

import beans.services.customerservice.register.RegisterRequestBean;
import persistenceLayer.dao.CustomerDataDAO;
import persistenceLayer.dao.LoginDataDAO;
import persistenceLayer.model.AddressModel;
import persistenceLayer.model.CustomerDataModel;
import persistenceLayer.model.LoginDataModel;
import persistenceLayer.conf.SpringAppContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomerInteractionModel {
    private final ApplicationContext context = new AnnotationConfigApplicationContext(SpringAppContext.class);
    
    private final AddressModel address = context.getBean(AddressModel.class);
	private final CustomerDataModel customer = context.getBean(CustomerDataModel.class);
	private final LoginDataModel login = context.getBean(LoginDataModel.class);
    
    public boolean login(String username, String password) {
        LoginDataModel loginData = new LoginDataDAO().readData(username);
        return (loginData!= null && loginData.getPassword().equals(password));
    }

	public boolean register(RegisterRequestBean requestBean) {
		this.mapEntityParams(requestBean);
		return new CustomerDataDAO().writeData(this.customer);
	}
    
    public CustomerDataModel getCustomerDetails(String username) {
        CustomerDataModel customerDetails = new CustomerDataDAO().readData(username);
        return customerDetails;
    }
	
	private void mapEntityParams(RegisterRequestBean requestBean) {
		//Mapping address entity
		address.setAddressLine1(requestBean.getAddressLine1());
		if(!(requestBean.getAddressLine2().isEmpty() || requestBean.getAddressLine2() == null)) {
			address.setAddressLine2(requestBean.getAddressLine2());
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
