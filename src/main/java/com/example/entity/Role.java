package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;

/**
 * 角色
 * @author yanxin
 * @Description:
 */
@NamedEntityGraph(
        name = "role.all",
        attributeNodes =  {
                @NamedAttributeNode("users")
        }
)
@Table(name = "t_role")
@Entity
@Cacheable
public class Role extends PanacheEntityBase {

    /**
     * 主键id
     */
    @Id
    //@SequenceGenerator(name = "roleSeq", sequenceName = "seq_role_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /**
     * 用户列表
     */
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    @org.hibernate.annotations.ForeignKey(name = "none")
    @JsonBackReference
    public List<User> users;

    /**
     * 角色名
     */
    public String role;
}
