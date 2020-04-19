package ch.hftm.vsblog;

import java.net.URI;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import ch.hftm.model.Entry;
import ch.hftm.model.LoginForm;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@Path("/")
@Produces(MediaType.TEXT_HTML)
@RequestScoped
public class WebResource {

    @Inject
    Template index;

    @Inject
    Template login;

    @Inject
    Template about;

    @GET
    @Path("{a: |index.html}")
    public TemplateInstance getIndex() {
        return index.data("entries", Entry.findAll().list());
    }

    @GET
    @Path("login")
    public TemplateInstance getLogin() {
        return login.instance();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("login")
    public Response login(@MultipartForm LoginForm loginForm, @Context UriInfo uriInfo, @Context HttpHeaders headers) {
        if(loginForm.username.equals("admin") && loginForm.password.equals("admin")) {
            NewCookie cookie = new NewCookie("Authorization", TokenIssuer.generateToken(loginForm.username));

            final URI originalLocation = uriInfo.getRequestUri();
            final URI redirect = UriBuilder.fromPath(originalLocation.getPath() + "/../index.html").build();
            return Response.seeOther(redirect).cookie(cookie).build();
        } else {
            loginForm.errorMessage = "Benutzer/Passwort-Kombination passt leider nicht.";
            TemplateInstance site =  login.data("loginForm", loginForm);
            return Response.ok(site).build();
        }
    }

    @GET
    @Path("logout")
    public Response logout(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
        NewCookie removeCookie = new NewCookie("Authorization", null, "/", null, null, 0, false, true);

        final URI originalLocation = uriInfo.getRequestUri();
        final URI redirect = UriBuilder.fromPath(originalLocation.getPath() + "/../index.html").build();
        return Response.temporaryRedirect(redirect).cookie(removeCookie).build();

        //return Response.temporaryRedirect(new URI("index")).build();

        // TemplateInstance site = index.data("entries", Entry.findAll().list());
        // return Response.ok(site).cookie(removeCookie).build();
    }

    @GET
    @Path("about")
    @RolesAllowed("admin")
    //@PermitAll
    public TemplateInstance getAbout(@Context SecurityContext ctx) {
        return about.instance();
    }
}