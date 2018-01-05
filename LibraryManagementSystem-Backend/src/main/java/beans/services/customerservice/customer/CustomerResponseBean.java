package beans.services.customerservice.customer;

import beans.GenericResponseBean;
import persistencelayer.entities.CustomerDataModel;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "")
public class CustomerResponseBean extends GenericResponseBean{
    private CustomerDataModel response;

    public CustomerDataModel getResponse() {
        return response;
    }

    public void setResponse(CustomerDataModel response) {
        this.response = response;
    }
}
