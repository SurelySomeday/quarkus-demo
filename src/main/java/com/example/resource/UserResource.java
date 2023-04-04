package com.example.resource;

import com.example.dto.UserParameters;
import com.example.entity.Role;
import com.example.entity.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {


    @GET
    @Path("")
    public List<User> all() {
        return User.findAll().list();
    }

    @GET
    @Path("/{id}")
    public User queryById(@PathParam("id") Long id) {
        return User.findById(id);
    }

    @GET
    @Path("/{id}/roles")
    public List<Role> roles(@PathParam("id") Long id) {
        Optional<User> optional = User.findByIdOptional(id);
        if(optional.isEmpty()){
            return Collections.emptyList();
        }
        return optional.get().roles;
    }

    @GET
    @Path("/{id}/roles/{roleId}")
    public Role roles(@PathParam("id") Long id,@PathParam("roleId") Long roleId) {
        return Role.find("""
                                select role from Role role
                                join role.users user
                                where user.id=?1 and role.id=?2
                               """,
                id, roleId).singleResult();
    }

    @GET
    @Path("/details")
    public List<User> query(UserParameters userParameters) {
        return User.find("from User where name=?1",userParameters.name).list();
    }

    @POST
    @Path("")
    public void add(User user) {
        User.persist(user);
    }


    @PUT
    @Path("")
    public void update(User user) {
        User.persist(user);
    }

}