package rest;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.RegisterRequestBean;
import beans.RegisterResponseBean;
import controllers.AuthenticationController;
import controllers.RegistrationController;

@Path("customerservice")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerServices {

	@GET
	@Path("login")
	public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {
//		String responseMessage;
//		ApplicationResponse response;
//		if (username == null || username.equals("") || password == null || password.equals("")) {
//			responseMessage = "Invalid Login Request. Username and password cannot be null/empty";
//			response = new ApplicationResponse(Response.Status.BAD_REQUEST.getStatusCode(), responseMessage);
//			return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
//		} else {
//			AuthenticationController authController = new AuthenticationController();
//			if (!authController.login(username, password)) {
//				responseMessage = "Incorrent username and/or password.";
//				response = new ApplicationResponse(Response.Status.BAD_REQUEST.getStatusCode(), responseMessage);
//				return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
//			} else {
//				responseMessage = "Customer Authenticated.";
//				response = new ApplicationResponse(Response.Status.OK.getStatusCode(), responseMessage);
				return Response.ok("Login done").build();
//			}
//		}
	}

	@POST
	@Path("register")
	public Response register(RegisterRequestBean requestBean) {        
		RegistrationController registeration = new RegistrationController();
		RegisterResponseBean response = registeration.register(requestBean);

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
