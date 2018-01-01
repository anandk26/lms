package businessLayer.controllers;

import beans.GenericResponseBean;
import beans.services.customerservice.customer.CustomerResponseBean;
import beans.services.customerservice.register.RegisterRequestBean;

import persistenceLayer.model.CustomerInteractionModel;
import persistenceLayer.entities.CustomerDataModel;
import persistenceLayer.SpringPersistenceContext;

import businessLayer.utils.Constants;
import businessLayer.validations.LoginRequestValidation;
import businessLayer.validations.RegisterRequestValidation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CustomerInteractionController {
    
    private CustomerInteractionModel customerInteraction;
    private final ApplicationContext context = new AnnotationConfigApplicationContext(SpringPersistenceContext.class);

    public GenericResponseBean register(RegisterRequestBean requestBean) {
        
		GenericResponseBean response = new GenericResponseBean();
        this.customerInteraction = this.context.getBean("customerInteractionModel", CustomerInteractionModel.class);
		
		RegisterRequestValidation validator = new RegisterRequestValidation();
		if(!validator.isRequestValid(requestBean)) {
			response.setResponseCode(Constants.HTTP_BAD_REQEST);
			response.setReponseMessage("Missing mandatory parameters in the request");
		} else {
			if(!this.customerInteraction.register(requestBean)) {
				response.setResponseCode(Constants.HTTP_SERVICE_UNAVAILABLE);
				response.setReponseMessage("Currently there is a problem in registering the customer."
						+ " Please try after sometime");
			} else {
				response.setResponseCode(Constants.HTTP_SUCCESS);
				response.setReponseMessage("Customer registered successfully");
			}
		}
		return response;
	}
    
    public GenericResponseBean login(String username, String password) {
        
		GenericResponseBean response = new GenericResponseBean();
		LoginRequestValidation validator = new LoginRequestValidation();
        this.customerInteraction = this.context.getBean("customerInteractionModel", CustomerInteractionModel.class);
        
		if(!validator.isRequestValid(username, password)) {
			response.setResponseCode(Constants.HTTP_BAD_REQEST);
			response.setReponseMessage("Missing mandatory parameters in the request");
		} else {
			if(!this.customerInteraction.login(username, password)) {
				response.setResponseCode(Constants.HTTP_BAD_REQEST);
				response.setReponseMessage("Username and/or password incorrect");
			} else {
				response.setResponseCode(Constants.HTTP_SUCCESS);
				response.setReponseMessage("Customer logged in successfully");
			}
		}
		return response;
    }
    
    public CustomerResponseBean getCustomerDetails(String username) {
        
		CustomerResponseBean response = new CustomerResponseBean();
        this.customerInteraction = this.context.getBean("customerInteractionModel", CustomerInteractionModel.class);
        
		if(username == null || username.equals("")) {
			response.setResponseCode(Constants.HTTP_BAD_REQEST);
			response.setReponseMessage("Mandatory parameter: username not found in the request");
		} else {
            CustomerDataModel customer = this.customerInteraction.getCustomerDetails(username);
			if(customer == null) {
				response.setResponseCode(Constants.HTTP_BAD_REQEST);
				response.setReponseMessage("Username incorrect");
			} else {
				response.setResponseCode(Constants.HTTP_SUCCESS);
                response.setReponseMessage("Customer Details fetched successfully");
                response.setResponse(customer);
			}
		}
		return response;
    }
    
}
