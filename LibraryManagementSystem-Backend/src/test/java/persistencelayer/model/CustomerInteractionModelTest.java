package persistencelayer.model;

import static junit.framework.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import persistencelayer.dao.LoginDataDAO;
import persistencelayer.entities.LoginDataModel;

@RunWith(MockitoJUnitRunner.class)
public class CustomerInteractionModelTest {
    
    @InjectMocks
    private CustomerInteractionModel customerModel;
    
    @Mock
    private LoginDataDAO loginDAO;

    @InjectMocks
    private LoginDataModel loginData;
    
    @Test
    public void testLogin() {
        loginData.setUsername("username");
        loginData.setPassword("password");
        
        doReturn(loginData).when(loginDAO).readData("username");
        
        assertTrue(customerModel.login("username", "password"));
    }
}
