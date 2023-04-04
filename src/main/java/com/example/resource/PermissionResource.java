package com.example.resource;

import com.example.dto.UserParameters;
import com.example.entity.Role;
import com.example.entity.User;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Path("/permission")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PermissionResource {


    @GET
    @Path("")
    @RolesAllowed("anno")
    public List<User> all() {
        return User.findAll().list();
    }


}