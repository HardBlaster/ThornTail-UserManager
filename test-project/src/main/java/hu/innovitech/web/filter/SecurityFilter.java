package hu.innovitech.web.filter;

import hu.innovitech.web.token.TokenProvider;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.List;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final String ADMIN_PRE = "admin";
    private static final String USER_PRE = "users";
    private static final String SECURED_PRE = "secured";

    @Inject
    private TokenProvider tokenProvider;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        if(containerRequestContext.getUriInfo().getPath().contains(SECURED_PRE)) {

            String token = containerRequestContext.getHeaders().getFirst("Authorization");
            if(token != null) {

                token = token.replace("Bearer ", "");
                String roles = tokenProvider.validateToken(token);

                if (containerRequestContext.getUriInfo().getPath().contains(ADMIN_PRE)) {
                    if (!roles.equals("admin")) {
                        containerRequestContext.abortWith(Response
                                .status(Response.Status.FORBIDDEN)
                                .build());
                    }
                }

                if (containerRequestContext.getUriInfo().getPath().contains(USER_PRE)) {
                    if (!roles.equals("user") && !roles.equals("admin")) {
                        containerRequestContext.abortWith(Response
                                .status(Response.Status.FORBIDDEN)
                                .build());
                    }
                }
            } else {

                containerRequestContext.abortWith(Response
                        .status(Response.Status.FORBIDDEN)
                        .build());

            }
        }
    }
}
