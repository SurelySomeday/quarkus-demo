package com.example.resource;

import com.example.dto.UserParameters;
import com.example.entity.Role;
import com.example.entity.User;
import io.smallrye.mutiny.Uni;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 权限
 */
@Path("/permission")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PermissionResource {


    /**
     * anno访问
     *
     * @return {@link com.example.entity.User}
     */
    @GET
    @Path("")
    @RolesAllowed("anno")
    public Uni<List<User>> all() {
        return User.findAll().list();
    }

    /**
     * error
     *
     */
    @GET
    @Path("/error")
    @PermitAll
    public Uni<Void> ex() throws Exception {
        throw new Exception("test");
    }


}