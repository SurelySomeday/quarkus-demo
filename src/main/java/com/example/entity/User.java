package com.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.ConstraintMode.NO_CONSTRAINT;
import static javax.persistence.ConstraintMode.PROVIDER_DEFAULT;

/**
 * 用户
 * @author yanxin
 * @Description:
 */
@Table(name = "t_user")
@NamedEntityGraph(
        name = "user.all",
        attributeNodes =  {
                @NamedAttributeNode("roles")
        }
)
@Entity
@Cacheable
public class User extends PanacheEntityBase {
    /**
     * 主键id
     */
    @Id
    //@SequenceGenerator(name = "userSeq", sequenceName = "seq_user_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /**
     * 用户名
     */
    public String name;

    /**
     * 密码
     */
    public String pass;

    /**
     * 角色列表
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="t_user_role",
            foreignKey = @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey= @ForeignKey(name="none",value = ConstraintMode.NO_CONSTRAINT))
    @JsonManagedReference
    public List<Role> roles = new ArrayList<>();

}
