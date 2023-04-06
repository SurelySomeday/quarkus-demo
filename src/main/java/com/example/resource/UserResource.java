package com.example.resource;

import com.example.dto.UserParameters;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.service.UserService;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

/**
 * 用户管理
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;


    /**
     * 获取所有
     * @return
     */
    @GET
    @Path("")
    public Uni<List<User>> all() {
        return userService.all();
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    public Uni<User> queryById(@PathParam("id") Long id) {
        return userService.findById(id);
    }

    /**
     * 获取指定用户的角色
     * @param id
     * @return
     */
    @GET
    @Path("/{id}/roles")
    public Uni<List<Role>> roles(@PathParam("id") Long id) {
        return userService.getRoles(id);
    }

    /**
     * 获取指定用户的指定角色
     * @param id
     * @param roleId
     * @return
     */
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

    /**
     * 根据用户名查询用户
     * @param userParameters
     * @return
     */
    @GET
    @Path("/details")
    public Uni<List<User>> query(UserParameters userParameters) {
        return userService.findByParameters(userParameters);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @POST
    @Path("")
    public Uni<Void> add(User user) {
        return userService.add(user).replaceWithVoid();
    }

    /**
     * 更新用户
     * @param user
     * @return
     */

    @PUT
    @Path("")
    public Uni<Void> update(User user) {
        return userService.update(user).replaceWithVoid();
    }

}