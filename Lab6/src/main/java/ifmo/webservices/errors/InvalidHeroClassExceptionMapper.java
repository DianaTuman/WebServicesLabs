package ifmo.webservices.errors;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidHeroClassExceptionMapper implements ExceptionMapper<InvalidHeroClassException> {
    @Override
    public Response toResponse(InvalidHeroClassException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
