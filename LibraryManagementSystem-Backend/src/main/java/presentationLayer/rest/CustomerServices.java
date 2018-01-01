package presentationLayer.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.services.customerservice.register.RegisterRequestBean;
import beans.GenericResponseBean;
import businessLayer.SpringBusinessContext;

import businessLayer.controllers.CustomerInteractionController;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Path("customerservice")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerServices {

    private final CustomerInteractionController customerController;
    private final ApplicationContext context;
    
    public CustomerServices() {
        this.context = new AnnotationConfigApplicationContext(SpringBusinessContext.class);
        this.customerController = this.context.getBean("customerInteractionController",
            CustomerInteractionController.class);
    }

    @GET
	@Path("login")
	public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {
		GenericResponseBean response = this.customerController.login(username, password);
        switch(response.getResponseCode()) {
			case 200:
				return Response.ok().entity(response).build();
			case 400:
				return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
			case 503:
				return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(response).build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}

	@POST
	@Path("register")
	public Response register(RegisterRequestBean requestBean) {
		GenericResponseBean response = this.customerController.register(requestBean);

		switch(response.getResponseCode()) {
			case 200:
				return Response.ok().entity(response).build();
			case 400:
				return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
			case 503:
				return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(response).build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}
    
    @GET
	@Path("customer")
	public Response getCustomerDetails(@QueryParam("username") String username) {
		GenericResponseBean response = this.customerController.getCustomerDetails(username);
        switch(response.getResponseCode()) {
			case 200:
				return Response.ok().entity(response).build();
			case 400:
				return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
			case 503:
				return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(response).build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}
}
