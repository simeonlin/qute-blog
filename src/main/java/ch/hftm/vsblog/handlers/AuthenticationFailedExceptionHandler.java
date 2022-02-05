package ch.hftm.vsblog.handlers;

import java.net.URI;

import javax.annotation.Priority;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import ch.hftm.vsblog.controllers.Login;
import io.quarkiverse.renarde.router.Router;
import io.quarkus.security.AuthenticationFailedException;

@Provider
@Priority(1)
public class AuthenticationFailedExceptionHandler implements ExceptionMapper<AuthenticationFailedException>  {

    @Override
    public Response toResponse(AuthenticationFailedException exception) {
        NewCookie removeCookie = new NewCookie("Authorization", null, "/", null, null, 0, false, true);
        URI uri = Router.getURI(Login::login);
        return Response.temporaryRedirect(uri).cookie(removeCookie).build();
    }
}
