package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.RegisterRequestBean;
import beans.GenericResponseBean;
import controllers.CustomerInteractionController;

@Path("customerservice")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerServices {
    private final CustomerInteractionController customerController = new CustomerInteractionController();
	@GET
	@Path("login")
	public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {
		GenericResponseBean response = this.customerController.login(username, password);
        switch(response.getResponseCode()) {
			case 200:
				return Response.ok(response).build();
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
				return Response.ok(response).build();
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
	public Response getCustomerDetails(@QueryParam("username") String username, String password) {
		GenericResponseBean response = this.customerController.login(username, password);
        switch(response.getResponseCode()) {
			case 200:
				return Response.ok(response).build();
			case 400:
				return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
			case 503:
				return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(response).build();
			default:
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}
}
