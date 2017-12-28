package validations;

import beans.RegisterRequestBean;

public class RegisterRequestValidation{

	public boolean isRequestValid(RegisterRequestBean requestBean) {
		if(
			requestBean.getFirstName() == null || requestBean.getFirstName().equals("")
			|| requestBean.getLastName() == null || requestBean.getLastName().equals("")
			|| requestBean.getUsername() == null || requestBean.getUsername().equals("")
			|| requestBean.getPassword() == null || requestBean.getPassword().equals("")
			|| requestBean.getAddressLine1() == null || requestBean.getAddressLine1().equals("")
			|| requestBean.getCity() == null || requestBean.getCity().equals("")
			|| requestBean.getState() == null || requestBean.getState().equals("")
			|| requestBean.getPostcode() == null || requestBean.getPostcode().equals("")
			|| requestBean.getCountry() == null || requestBean.getCountry().equals("")
			|| requestBean.getPhone() >= 9999999999L || requestBean.getPhone() <= 1000000000L
		) {
			return false;
		}
		return true;
	}
}
