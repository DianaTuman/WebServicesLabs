package ifmo.webservices.errors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidNameExceptionMapper implements ExceptionMapper<InvalidNameException> {
    @Override
    public Response toResponse(InvalidNameException e) {
        return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}