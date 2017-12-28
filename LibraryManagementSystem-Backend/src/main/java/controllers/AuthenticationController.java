package controllers;

import beans.GenericResponseBean;
import model.LoginCustomer;
import model.RegisterCustomer;
import utils.Constants;
import validations.LoginRequestValidation;
import validations.RegisterRequestValidation;

public class AuthenticationController {
    public GenericResponseBean login(String username, String password) {
        LoginCustomer loginModel = new LoginCustomer();
		GenericResponseBean response = new GenericResponseBean();
		LoginRequestValidation validator = new LoginRequestValidation();
        
		if(!validator.isRequestValid(username, password)) {
			response.setResponseCode(Constants.HTTP_BAD_REQEST);
			response.setReponseMessage("Missing mandatory parameters in the request");
		} else {
			if(!loginModel.login(username, password)) {
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