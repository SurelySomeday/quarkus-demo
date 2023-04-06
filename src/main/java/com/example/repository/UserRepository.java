package com.example.repository;

import com.example.dto.UserParameters;
import com.example.entity.User;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

/**
 * @author yanxin
 * @Description:
 */
@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public Uni<List<User>> findByParameters(UserParameters userParameters){
        return this.find("from User where name=?1",userParameters.name).list();
    }
}
