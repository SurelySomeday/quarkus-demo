package com.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yanxin
 * @Description:
 */
@Table(name = "test_user")
@Entity
@Cacheable
public class User extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "user_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "userSeq")
    public Long id;

    public String name;

    public String pass;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    public List<Role> roles = new ArrayList<>();

}
