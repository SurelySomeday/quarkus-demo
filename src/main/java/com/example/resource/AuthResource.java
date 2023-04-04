package com.example.resource;

import com.example.dto.UserLoginParameters;
import com.example.entity.Role;
import com.example.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@PermitAll
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    JsonWebToken jwt;

    @POST
    @PermitAll
    @Path("/login")
    public String login(UserLoginParameters userLoginParameters) {
        User user = User.find("from User where name=?1 and pass=?2", userLoginParameters.name, userLoginParameters.pass)
                .singleResult();
        if(user!=null){
            String token =
                    Jwt.issuer("https://example.com/issuer")
                            .upn("jdoe@quarkus.io")
                            .groups(user.roles.stream().map(item->item.role).collect(Collectors.toSet()))
                            .claim(Claims.birthdate.name(), "2001-07-13")
                            .sign();
            return token;
        }
        return null;
    }


}