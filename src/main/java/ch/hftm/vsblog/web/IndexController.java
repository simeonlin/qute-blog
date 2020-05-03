package ch.hftm.vsblog.web;

import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import ch.hftm.vsblog.model.Entry;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@Path("/")
@Produces(MediaType.TEXT_HTML)
@RequestScoped
public class IndexController {

    @Inject
    Template index;

    @Inject
    Template about;

    @GET
    @Path("{a: |index.html}")
    public TemplateInstance getIndex() {
        LocalDateTime actDate = LocalDateTime.now();
        System.out.println("INDEX loaded: " +actDate);
        return index.data("entries", Entry.listAll(Sort.by("id", Direction.Descending))).data("lastreload", actDate);
    }

    @GET
    @Path("about")
    public TemplateInstance getAbout(@Context SecurityContext ctx) {
        return about.instance();
    }
}