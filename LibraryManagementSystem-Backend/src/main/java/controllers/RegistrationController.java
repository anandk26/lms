package controllers;

import beans.RegisterRequestBean;
import beans.RegisterResponseBean;
import model.RegisterCustomer;
import utils.Constants;
import validations.RegisterRequestValidation;

public class RegistrationController {
	public RegisterResponseBean register(RegisterRequestBean requestBean) {
		RegisterCustomer registrationModel = new RegisterCustomer();
		RegisterResponseBean response = new RegisterResponseBean();
		
		RegisterRequestValidation validator = new RegisterRequestValidation();
		if(!validator.isRequestValid(requestBean)) {
			response.setResponseCode(Constants.HTTP_BAD_REQEST);
			response.setReponseMessage("Missing mandatory parameters in the request");
		} else {
			if(!registrationModel.register(requestBean)) {
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
}
