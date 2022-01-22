package ch.hftm.vsblog.web.util;

import javax.annotation.Priority;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import io.quarkus.security.AuthenticationFailedException;

@Provider
@Priority(1)
public class AuthenticationFailedExceptionHandler implements ExceptionMapper<AuthenticationFailedException>  {

    @Override
    public Response toResponse(AuthenticationFailedException exception) {
        NewCookie removeCookie = new NewCookie("Authorization", null, "/", null, null, 0, false, true);
        return Response.temporaryRedirect(UriBuilder.fromUri("/login").build()).cookie(removeCookie).build();
    }
}
