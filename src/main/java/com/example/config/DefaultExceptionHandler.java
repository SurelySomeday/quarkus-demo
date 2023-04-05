package com.example.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author yanxin
 * @Description:
 */
@Provider
public class DefaultExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        String msg;
        msg = "Exception: " + exception.getMessage();
        return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
    }
}