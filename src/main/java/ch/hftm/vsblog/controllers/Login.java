package ch.hftm.vsblog.controllers;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.reactive.RestForm;

import ch.hftm.vsblog.services.TokenIssuer;
import io.quarkiverse.renarde.Controller;
import io.quarkiverse.renarde.router.Router;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;

@Path("/")
@Produces(MediaType.TEXT_HTML)
@Blocking
public class Login extends Controller {

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance login();
    }

    @GET
    public TemplateInstance login() {
        return Templates.login();
    }

    @POST
    public Response loginrequest(@RestForm String username, @RestForm String password, @Context UriInfo uriInfo, @Context HttpHeaders headers) {
        if(username.equals("admin") && password.equals("admin")) {
            // Create a httpOnly-Cookie with the JWT
            NewCookie cookie = new NewCookie("Authorization", TokenIssuer.generateToken(username), null, null, null, -1, false, true);
            URI uri = Router.getURI(Application::index, "");
            return Response.seeOther(uri).cookie(cookie).build();
        } else {
            flash("message", "User/password-combination ist wrong.");
            flash("messageType", "warn");
            URI uri = Router.getURI(Login::login);
            return Response.seeOther(uri).build();
        }
    }

    @GET
    public Response logout(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
        NewCookie removeCookie = new NewCookie("Authorization", null, "/", null, null, 0, false, true);
        URI uri = Router.getURI(Application::index, "");
        return Response.seeOther(uri).cookie(removeCookie).build();
    }
    
}