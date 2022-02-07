package ch.hftm.vsblog.controllers;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.reactive.RestForm;

import ch.hftm.vsblog.model.Entry;
import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;

@Path("/")
@Produces(MediaType.TEXT_HTML)
@Blocking
public class Post extends Controller {

    @CheckedTemplate(requireTypeSafeExpressions = false)
    static class Templates {
        public static native TemplateInstance post();
    }

    @GET
    @RolesAllowed("admin")
    public TemplateInstance post() {
        return Templates.post();
    }

    @POST
    @RolesAllowed("admin")
    @Transactional
    public void post(@RestForm String title, @RestForm String content, @HeaderParam("X-Up-Validate") String validate, @Context SecurityContext ctx) {
        if(title.length() < 5) {
            validation.addError("title", "Minimum 5 Chars are required.");
        }
        if (content.length() < 10) {
            validation.addError("content", "Minimum 10 Chars are required.");
        }
        if((validate!= null && !validate.isBlank())) {
            // If it was only a validation request: Make a fake-error to ensure that the form keeps its state
            validation.addError("fake", "fake");
        }

        if (validationFailed()) {
            post();
        } else {
            var e = new Entry();
            e.title = title;
            e.content = content;
            e.author = ctx.getUserPrincipal().getName();
            e.persist();

            flash("message", "Blog-Post added!");
            redirect(Application.class).index("");
        }
    }
}