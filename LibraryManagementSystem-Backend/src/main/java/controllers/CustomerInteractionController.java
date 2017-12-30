package controllers;

import beans.GenericResponseBean;
import beans.services.customer.CustomerResponseBean;
import beans.services.register.RegisterRequestBean;
import model.CustomerInteractionModel;
import model.entities.CustomerDataModel;
import utils.Constants;
import validations.LoginRequestValidation;
import validations.RegisterRequestValidation;

public class CustomerInteractionController {
    
    private final CustomerInteractionModel customerInteraction = new CustomerInteractionModel();
    
    public GenericResponseBean register(RegisterRequestBean requestBean) {
        
		GenericResponseBean response = new GenericResponseBean();
		
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
                response.setReponseMessage("Customer Detals fetched successfully");
                response.setResponse(customer);
			}
		}
		return response;
    }
    
}
