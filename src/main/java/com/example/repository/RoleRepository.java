package com.example.repository;

import com.example.entity.Role;
import com.example.entity.User;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author yanxin
 * @Description:
 */
@ApplicationScoped
public class RoleRepository implements PanacheRepository<Role> {

    public Uni<Role> findUserRole(Long id,Long roleId){
        return this.find("""
                                select role from Role role
                                join role.users user
                                where user.id=?1 and role.id=?2
                               """,
                id, roleId).singleResult();
    }
}
