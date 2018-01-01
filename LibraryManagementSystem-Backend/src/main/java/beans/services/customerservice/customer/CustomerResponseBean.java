package beans.services.customerservice.customer;

import beans.GenericResponseBean;
import javax.xml.bind.annotation.XmlType;
import persistenceLayer.entities.CustomerDataModel;

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
