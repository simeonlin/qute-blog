package ch.hftm.vsblog;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import ch.hftm.model.Entry;

@Path("/entries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlogResource {

    @POST
    @Transactional
    public Entry addEntry(Entry e) {
        if(e.id == null) {
            e.persistAndFlush();
            return e;
        } else {
            throw new ResponseException(Status.FORBIDDEN, "You are not allowed to set the ID on a new Blog-Entry.");
        }
    }

    @GET
    public List<Entry> getAll() {
        return Entry.findAll().list();
    }
}