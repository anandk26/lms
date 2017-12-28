package controllers;

import beans.RegisterRequestBean;
import beans.GenericResponseBean;
import model.RegisterCustomer;
import utils.Constants;
import validations.RegisterRequestValidation;

public class RegistrationController {
	public GenericResponseBean register(RegisterRequestBean requestBean) {
		RegisterCustomer registrationModel = new RegisterCustomer();
		GenericResponseBean response = new GenericResponseBean();
		
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
