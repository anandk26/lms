package persistencelayer.model;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import beans.services.customerservice.register.RegisterRequestBean;
import persistencelayer.dao.CustomerDataDAO;
import persistencelayer.dao.LoginDataDAO;
import persistencelayer.entities.AddressModel;
import persistencelayer.entities.CustomerDataModel;
import persistencelayer.entities.LoginDataModel;

@RunWith(MockitoJUnitRunner.class)
public class CustomerInteractionModelTest {

	@Mock
    private AddressModel addressData;
	@Mock
	private CustomerDataModel customerData;
	@Mock
	private LoginDataModel loginData;
	@Mock
	private LoginDataDAO loginDAO;
	@Mock
	private CustomerDataDAO customerDAO;

    @InjectMocks
    private CustomerInteractionModel customerModel;
    
    @Test
    public void testLogin() {
    	doReturn("password").when(loginData).getPassword();
        doReturn(loginData).when(loginDAO).readData("username");
        
        assertTrue(customerModel.login("username", "password"));
        verify(loginDAO, times(1)).readData("username");
    }

    @Test
    public void testRegister() {
    	RegisterRequestBean request = new RegisterRequestBean();
    	request.setFirstName("firstName");
    	request.setLastName("lastName");
    	request.setUsername("username");
    	request.setPassword("password");
    	request.setAddressLine1("address line 1");
    	request.setAddressLine2("address line 2");
    	request.setCity("city");
    	request.setState("state");
    	request.setCountry("country");
    	request.setPhone(01111111111);
    	request.setPostcode("post");
    	
    	doReturn(true).when(customerDAO).writeData(any(CustomerDataModel.class));
    	
    	assertTrue(customerModel.register(request));
    	verify(customerDAO, times(1)).writeData(any(CustomerDataModel.class));
    }

    @Test
    public void testGetCustomerDetails() {
    	doReturn("password").when(loginData).getPassword();
    	doReturn("address line 1").when(addressData).getAddressLine1();
    	doReturn("firstName").when(customerData).getFirstName();
    	doReturn("lastName").when(customerData).getLastName();
    	doReturn(loginData).when(customerData).getLogindata();
    	doReturn(addressData).when(customerData).getAddress();
    	doReturn(customerData).when(customerDAO).readData("username");

    	CustomerDataModel customer = customerModel.getCustomerDetails("username");
    	
    	assertEquals(customer.getFirstName(), "firstName");
    	assertEquals(customer.getLastName(), "lastName");
    	assertEquals(customer.getAddress().getAddressLine1(), "address line 1");
    	assertEquals(customer.getLogindata().getPassword(), "password");
    	verify(customerData, times(1)).getAddress();
    	verify(customerData, times(1)).getLogindata();
    	verify(customerDAO, times(1)).readData("username");
    }
}
