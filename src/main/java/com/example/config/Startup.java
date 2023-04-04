package com.example.config;

import com.example.entity.Role;
import com.example.entity.User;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.time.Duration;

/**
 * @author yanxin
 * @Description:
 */
@Singleton
public class Startup {
    @Transactional(rollbackOn = Exception.class)
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users
        User.deleteAll().await().atMost(Duration.ofMinutes(5));
        User user=new User();
        user.name="admin";
        user.pass="123456";
        Role role=new Role();
        role.role="anno";
        role.persistAndFlush().await().atMost(Duration.ofMinutes(5));
        user.roles.add(role);
        user.persistAndFlush().await().atMost(Duration.ofMinutes(5));
    }
}
