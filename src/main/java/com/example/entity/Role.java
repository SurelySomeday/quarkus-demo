package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;

/**
 * @author yanxin
 * @Description:
 */
@NamedEntityGraph(
        name = "role.all",
        attributeNodes =  {
                @NamedAttributeNode("users")
        }
)
@Entity
@Cacheable
public class Role extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "roleSeq", sequenceName = "role_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "roleSeq")
    public Long id;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    @JsonBackReference
    public List<User> users;

    public String role;
}
