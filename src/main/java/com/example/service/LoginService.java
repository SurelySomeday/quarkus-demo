package com.example.service;

import com.example.dto.UserLoginParameters;
import com.example.dto.UserParameters;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.repository.UserRepository;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.jwt.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.security.auth.login.LoginException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yanxin
 * @Description:
 */
@ApplicationScoped
public class LoginService {

    Logger logger= LoggerFactory.getLogger(LoginService.class);

    @Inject
    UserRepository userRepository;

    public Uni<String> login(UserLoginParameters userLoginParameters){
        Uni<User> user = User.find("from User where name=?1 and pass=?2", userLoginParameters.name, userLoginParameters.pass)
                .singleResult();
        return user.map(item -> {
                    if (item != null) {
                        return Jwt.issuer("https://example.com/issuer")
                                .upn("jdoe@quarkus.io")
                                .groups(item.roles.stream().map(item2 -> item2.role).collect(Collectors.toSet()))
                                .claim(Claims.birthdate.name(), "2001-07-13")
                                .expiresAt(System.currentTimeMillis()+ Duration.ofHours(24).toMillis())
                                .sign();
                    } else {
                        return "";
                    }
                })
                .onFailure(NoResultException.class)
                .transform((item)->new LoginException("账号或密码错误！"));
    }



}
