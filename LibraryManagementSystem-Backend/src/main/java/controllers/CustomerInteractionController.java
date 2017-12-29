package controllers;

import beans.GenericResponseBean;
import beans.RegisterRequestBean;
import model.CustomerInteractionModel;
import utils.Constants;
import validations.LoginRequestValidation;
import validations.RegisterRequestValidation;

public class CustomerInteractionController {
    
    public GenericResponseBean register(RegisterRequestBean requestBean) {
		CustomerInteractionModel customerInteraction = new CustomerInteractionModel();
		GenericResponseBean response = new GenericResponseBean();
		
		RegisterRequestValidation validator = new RegisterRequestValidation();
		if(!validator.isRequestValid(requestBean)) {
			response.setResponseCode(Constants.HTTP_BAD_REQEST);
			response.setReponseMessage("Missing mandatory parameters in the request");
		} else {
			if(!customerInteraction.register(requestBean)) {
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
        CustomerInteractionModel customerInteraction = new CustomerInteractionModel();
		GenericResponseBean response = new GenericResponseBean();
		LoginRequestValidation validator = new LoginRequestValidation();
        
		if(!validator.isRequestValid(username, password)) {
			response.setResponseCode(Constants.HTTP_BAD_REQEST);
			response.setReponseMessage("Missing mandatory parameters in the request");
		} else {
			if(!customerInteraction.login(username, password)) {
				response.setResponseCode(Constants.HTTP_BAD_REQEST);
				response.setReponseMessage("Username and/or password incorrect");
			} else {
				response.setResponseCode(Constants.HTTP_SUCCESS);
				response.setReponseMessage("Customer logged in successfully");
			}
		}
		return response;
    }
    
}
