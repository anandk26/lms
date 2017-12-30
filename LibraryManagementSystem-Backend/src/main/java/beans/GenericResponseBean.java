package beans;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class GenericResponseBean {
    
	private int responseCode;
	private String reponseMessage;

    @XmlTransient
	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getReponseMessage() {
		return reponseMessage;
	}

	public void setReponseMessage(String reponseMessage) {
		this.reponseMessage = reponseMessage;
	}
}
