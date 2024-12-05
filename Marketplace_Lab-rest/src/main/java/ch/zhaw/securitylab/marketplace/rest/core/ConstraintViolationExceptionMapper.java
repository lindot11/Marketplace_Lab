package ch.zhaw.securitylab.marketplace.rest.core;

import ch.zhaw.securitylab.marketplace.common.model.RestErrorDto;
import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        StringBuilder errorMessage = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            if (errorMessage.length() > 0) {
                errorMessage.append(", ");
            }
            errorMessage.append(constraintViolation.getMessage());
        }
        RestErrorDto error = new RestErrorDto();
        error.setError(errorMessage.toString());
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}
