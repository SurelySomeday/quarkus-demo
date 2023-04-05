package com.example.repository;

import com.example.entity.User;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author yanxin
 * @Description:
 */
@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
