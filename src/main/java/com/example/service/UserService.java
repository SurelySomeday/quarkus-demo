package com.example.service;

import com.example.dto.UserParameters;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import io.smallrye.mutiny.Uni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * @author yanxin
 * @Description:
 */
@ApplicationScoped
public class UserService {

    Logger logger= LoggerFactory.getLogger(UserService.class);

    @Inject
    UserRepository userRepository;
    @Inject
    RoleRepository roleRepository;

    public Uni<List<User>> all(){
        return userRepository.findAll().list();
    }

    public Uni<User> findById(Long id){
        return userRepository.findById(id);
    }

    public Uni<List<Role>> getRoles(Long id){
        Uni<User> userUni = userRepository.findById(id);
        return userUni.map(item->{
            if(item==null){
                return Collections.emptyList();
            }
            return item.roles;
        });
    }

    public Uni<Role> getUserRole(Long id,Long roleId){
        return roleRepository.findUserRole(id,roleId);
    }



    public Uni<List<User>> findByParameters(UserParameters userParameters){
        return userRepository.findByParameters(userParameters);
    }

    public Uni<User> add(User user){
        user.id=null;
        return userRepository.persistAndFlush(user);
    }

    public Uni<User> update(User user){
        if(user.id==null){
            logger.error("update primaryKey must not null ! ");
        }
        return userRepository.persistAndFlush(user);
    }


}
