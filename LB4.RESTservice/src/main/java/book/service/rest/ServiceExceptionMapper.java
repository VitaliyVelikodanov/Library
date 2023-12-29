package book.service.rest;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import book.service.ServiceException;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    @Override
    public Response toResponse(ServiceException exception) {
        System.out.println("toResponse() exception " + exception.getMessage());
        exception.printStackTrace();

        return Response.status(exception.getStatusCode())
            .entity(exception.getMessage())
            .type(MediaType.TEXT_PLAIN_TYPE)
            .build();
    }

}
