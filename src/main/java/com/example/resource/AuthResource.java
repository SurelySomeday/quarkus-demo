package com.example.resource;

import com.example.dto.UserLoginParameters;
import com.example.entity.User;
import com.example.service.LoginService;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.util.stream.Collectors;

/**
 * 授权
 */
@PermitAll
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    LoginService loginService;

    /**
     * 登录
     * @param userLoginParameters
     * @return
     */
    @POST
    @PermitAll
    @Path("/login")
    public Uni<String> login(UserLoginParameters userLoginParameters) {
        return loginService.login(userLoginParameters);
    }
}