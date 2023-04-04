package com.example.config;

import com.example.entity.Role;
import com.example.entity.User;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;

/**
 * @author yanxin
 * @Description:
 */
@Singleton
public class Startup {
    @Transactional(rollbackOn = Exception.class)
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users
        User.deleteAll();
        User user=new User();
        user.name="admin";
        user.pass="123456";
        Role role=new Role();
        role.role="anno";
        Role.persist(role);
        user.roles.add(role);
        User.persist(user);
    }
}
