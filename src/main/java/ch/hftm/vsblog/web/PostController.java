package ch.hftm.vsblog.web;

import java.net.URI;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import ch.hftm.vsblog.model.Entry;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@Path("/")
@Produces(MediaType.TEXT_HTML)
@RequestScoped
public class PostController {

    @Inject
    Template post;

    @GET
    @Path("post")
    @RolesAllowed("admin")
    public TemplateInstance getPost() {
        var postForm = new PostForm();
        return post.data("postForm", postForm);
    }

    @POST
    @Path("post")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RolesAllowed("admin")
    @Transactional
    public Response post(@MultipartForm PostForm postForm,  @HeaderParam("X-Up-Validate") String validate, @Context UriInfo uriInfo, @Context HttpHeaders headers, @Context SecurityContext ctx) {
        if (validate == "username") {
        }
        if(postForm.content.length() > 5 && postForm.title.length() > 5) {
            var e = new Entry();
            e.title = postForm.title;
            e.content = postForm.content;
            e.author = ctx.getUserPrincipal().getName();
            e.persist();

            final URI originalLocation = uriInfo.getRequestUri();
            final URI redirect = UriBuilder.fromPath(originalLocation.getPath() + "/../index.html").build();
            return Response.seeOther(redirect).build();
        } else {
            postForm.errorMessage = "Minimum 5 Chars are required.";
            TemplateInstance site =  post.data("postForm", postForm);
            return Response.ok(site).build();
        }
    }
}