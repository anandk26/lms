package buissnessLayer.validations;

public class LoginRequestValidation {
    public boolean isRequestValid(String username, String password) {
		if (username == null || username.equals("") || password == null || password.equals("")) {
			return false;
		} 
        return true;
	}
}
