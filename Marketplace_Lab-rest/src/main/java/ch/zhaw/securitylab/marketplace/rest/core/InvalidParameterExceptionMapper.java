package ch.zhaw.securitylab.marketplace.rest.core;

import java.security.InvalidParameterException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import ch.zhaw.securitylab.marketplace.common.model.RestErrorDto;

@Provider
public class InvalidParameterExceptionMapper implements ExceptionMapper<InvalidParameterException> {

    @Override
    public Response toResponse(InvalidParameterException exception) {
        RestErrorDto error = new RestErrorDto();
        error.setError(exception.getMessage());
        return Response.status(Status.BAD_REQUEST).entity(error).build();
    }
}