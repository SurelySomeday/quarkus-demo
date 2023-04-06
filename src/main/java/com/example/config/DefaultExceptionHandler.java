package com.example.config;

import com.example.response.ResponseEntity;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.security.auth.login.LoginException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author yanxin
 * @Description:
 */
@ApplicationScoped
public class DefaultExceptionHandler {
    @ServerExceptionMapper(Exception.class)
    public Response exception(Exception exception) {
        String msg;
        msg = "系统未知异常: " + exception.getMessage();
        return Response.status(Response.Status.OK).entity(msg).build();
    }

    @ServerExceptionMapper(LoginException.class)
    public Response loginException(LoginException exception) {
        String msg;
        msg = "登陆失败:"+exception.getMessage();
        return Response.status(Response.Status.OK)
                .entity(ResponseEntity.custom("E0001",msg,null)).build();
    }
}