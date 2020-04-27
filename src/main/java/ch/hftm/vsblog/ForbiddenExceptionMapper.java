package ch.hftm.vsblog;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import io.quarkus.security.UnauthorizedException;

@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<UnauthorizedException> {
    @Override
    public Response toResponse(UnauthorizedException exception) {
        return Response.status(200).entity("Zutritt verboten :-)").build();
    }
}