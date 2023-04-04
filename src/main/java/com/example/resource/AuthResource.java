package com.example.resource;

import com.example.dto.UserLoginParameters;
import com.example.entity.User;
import io.quarkus.security.identity.IdentityProvider;
import io.quarkus.vertx.http.runtime.security.HttpAuthenticationMechanism;
import io.smallrye.jwt.auth.principal.JWTCallerPrincipalFactory;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
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
    public Uni<String> login(UserLoginParameters userLoginParameters) {
        Uni<User> user = User.find("from User where name=?1 and pass=?2", userLoginParameters.name, userLoginParameters.pass)
                .singleResult();
        return user.map(item -> {
            if (item != null) {
                String token =
                        Jwt.issuer("https://example.com/issuer")
                                .upn("jdoe@quarkus.io")
                                .groups(item.roles.stream().map(item2 -> item2.role).collect(Collectors.toSet()))
                                .claim(Claims.birthdate.name(), "2001-07-13")
                                .expiresAt(System.currentTimeMillis()+ Duration.ofHours(24).toMillis())
                                .sign();
                return token;
            } else {
                return "";
            }
        });
    }
}