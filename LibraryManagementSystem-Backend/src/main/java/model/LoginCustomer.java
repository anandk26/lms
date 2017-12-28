package model;

import model.dao.LoginDataDAO;
import model.entities.LoginDataModel;

public class LoginCustomer {
    public boolean login(String username, String password) {
        LoginDataModel loginData = new LoginDataDAO().readData(username);
        if(loginData!= null && loginData.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
