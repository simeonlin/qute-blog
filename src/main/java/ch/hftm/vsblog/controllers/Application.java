package ch.hftm.vsblog.controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import ch.hftm.vsblog.model.Entry;
import io.quarkiverse.renarde.Controller;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;

@Path("/")
@Produces(MediaType.TEXT_HTML)
@Blocking
public class Application extends Controller {

    @CheckedTemplate
    static class Templates {
        public static native TemplateInstance index(List<Entry> entries);
        public static native TemplateInstance about();
    }

    @Path("/")
    @GET
    public TemplateInstance index(@QueryParam("searchString") String searchString) {
        List<Entry> entries;
        if(searchString == null || searchString.isEmpty()) {
            entries = Entry.listAll(Sort.by("id", Direction.Descending));
        } else {
            entries = Entry.list("title LIKE ?1 OR content LIKE ?1", Sort.by("id", Direction.Descending),"%"+searchString+"%");
        }

        return Templates.index(entries);
    }

    @Path("/mytest/path/about")
    @GET
    public TemplateInstance about(@Context SecurityContext ctx) {
        return Templates.about();
    }
}