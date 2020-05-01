package ch.hftm.vsblog.resources;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ResponseException extends WebApplicationException {
    private static final long serialVersionUID = 1L;

    public ResponseException(Status status, String message) {
        super(message, Response.status(status.getStatusCode()).type(MediaType.TEXT_PLAIN).header("info", message)
                .entity(message).build());
    }
}