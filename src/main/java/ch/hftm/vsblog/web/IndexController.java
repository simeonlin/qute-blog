package ch.hftm.vsblog.web;

import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    public TemplateInstance getIndex(@QueryParam("searchString") String searchString) {
        LocalDateTime actDate = LocalDateTime.now();
        System.out.println("INDEX loaded: " +actDate);
        List<Entry> entries;

        if(searchString == null || searchString.isEmpty()) {
            entries = Entry.listAll(Sort.by("id", Direction.Descending));
        } else {
            entries = Entry.list("title LIKE ?1 OR content LIKE ?1", Sort.by("id", Direction.Descending),"%"+searchString+"%");
        }
        

        return index.data("entries", entries).data("lastreload", actDate);
    }

    @GET
    @Path("about")
    public TemplateInstance getAbout(@Context SecurityContext ctx) {
        return about.instance();
    }
}