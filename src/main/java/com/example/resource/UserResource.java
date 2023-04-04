package com.example.resource;

import com.example.dto.UserParameters;
import com.example.entity.Role;
import com.example.entity.User;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {


    @GET
    @Path("")
    public Uni<List<User>> all() {
        return User.findAll().list();
    }

    @GET
    @Path("/{id}")
    public Uni<User> queryById(@PathParam("id") Long id) {
        return User.findById(id);
    }

    @GET
    @Path("/{id}/roles")
    public Uni<List<Role>> roles(@PathParam("id") Long id) {
        Uni<User> userUni = User.findById(id);
        return userUni.map(item->{
            if(item==null){
                return Collections.emptyList();
            }
            return item.roles;
        });
    }

    @GET
    @Path("/{id}/roles/{roleId}")
    public Uni<Role> roles(@PathParam("id") Long id,@PathParam("roleId") Long roleId) {
        return Role.find("""
                                select role from Role role
                                join role.users user
                                where user.id=?1 and role.id=?2
                               """,
                id, roleId).singleResult();
    }

    @GET
    @Path("/details")
    public Uni<List<User>> query(UserParameters userParameters) {
        return User.find("from User where name=?1",userParameters.name).list();
    }

    @POST
    @Path("")
    public Uni<Void> add(User user) {
        return User.persist(user);
    }


    @PUT
    @Path("")
    public Uni<Void> update(User user) {
        return User.persist(user);
    }

}