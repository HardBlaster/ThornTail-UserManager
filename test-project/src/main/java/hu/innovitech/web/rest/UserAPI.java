package hu.innovitech.web.rest;

import hu.innovitech.services.AddressService;
import hu.innovitech.services.UserService;
import hu.innovitech.web.token.TokenProvider;
import hu.innovitech.web.vo.AddressVO;
import hu.innovitech.web.vo.UserVO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/secured/users")
public class UserAPI {

    @Inject
    UserService userService;

    @Inject
    AddressService addressService;

    @Inject
    TokenProvider tokenProvider;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/allUsers")
    public List<UserVO> getUsers() {
        return userService.getAllUsers();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/admin/changeRole")
    public UserVO changeRole(UserVO user) {
        userService.updateUser(user);

        return user;
    }

    @POST
    @Path("/admin/deleteUser")
    public Boolean deleteUser(UserVO user) {
        return userService.deleteUser(user.getUsername());
    }

    /*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/changePassword")
    public Response changePassword(String username, String oldPassword, String newPassword) {
        UserVO userVO = userService.getUser(username);
        if(userVO.getHashedPass().equals(oldPassword)) {

            userVO.setHashedPass(newPassword);
            userService.updateUser(userVO);

            return Response.ok().build();

        } else {
            return Response.status(Response.Status.FORBIDDEN).entity("Invalid password").build();
        }
    }*/

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deleteAddress")
    public Response deleteAddress(@HeaderParam("Authorization") String token,
                                  AddressVO addressVO) {
        token = token.replace("Bearer ", "");

        return Response.ok(userService.deleteAddress(tokenProvider.getUsername(token), addressVO))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addAddress")
    public Response addAddress(@HeaderParam("Authorization") String token,
                               AddressVO addressVO) {
        token = token.replace("Bearer ", "");
        addressService.addAddress(addressVO);

        return Response.ok(userService.addAddress(tokenProvider.getUsername(token), addressVO))
                .build();
    }
}
