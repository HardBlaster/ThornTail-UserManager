package hu.innovitech.web.rest;

import hu.innovitech.services.UserService;
import hu.innovitech.services.exceptions.IncorrectPasswordException;
import hu.innovitech.services.exceptions.NoSuchUserException;
import hu.innovitech.services.exceptions.UsernameTakenException;
import hu.innovitech.web.token.TokenProvider;
import hu.innovitech.web.vo.UserVO;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Authentication {

    @Inject
    UserService userService;

    @Inject
    TokenProvider tokenProvider;

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response register(UserVO user) {
        try {
            return Response.ok(userService.register(user)).build();
        } catch (UsernameTakenException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Username taken").build();
        }
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(UserVO user) {
        try {

            if(userService.login(user)) {

                String token = tokenProvider.generateToken(user.getUsername(), userService.getRoles(user.getUsername()));

                return Response.ok(token).build();

            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }

        } catch (NoSuchUserException e) {
            return Response.status(401).entity("Invalid username").build();
        } catch (IncorrectPasswordException e) {
            return Response.status(401).entity("Invalid password").build();
        }
    }

    @POST
    @Path("getRole")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String getRole(String token) {
        token = token.replace("\"", "");

        return tokenProvider.validateToken(token);
    }
}
