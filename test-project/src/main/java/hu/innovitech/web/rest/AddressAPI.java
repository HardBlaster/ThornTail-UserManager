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
import java.util.List;

@RequestScoped
@Path("/secured/address")
public class AddressAPI {

    @Inject
    UserService userService;

    @Inject
    AddressService addressService;

    @Inject
    TokenProvider tokenProvider;

    @POST
    @Path("/addresses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AddressVO> getAddresses(@HeaderParam("Authorization") String token) {
        String username = tokenProvider.getUsername(token.replace("Bearer ", ""));
        return userService.getUserAddresses(username);
    }

    @POST
    @Path("/newAddress")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean newAddress(@HeaderParam("Authorization") String token, AddressVO address) {
        String username = tokenProvider.getUsername(token.replace("Bearer ", ""));
        UserVO user = userService.getUser(username);
        user.getAddresses().add(address);

        return userService.updateUser(user);
    }

    @POST
    @Path("/modifyAddress")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean editAddress(AddressVO[] addresses) {
        return addressService.editAddress(addresses[0], addresses[1]);
    }
}
