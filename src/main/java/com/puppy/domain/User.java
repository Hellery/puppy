package com.puppy.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"NAME"})})
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String password;
    private String ip;
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "uid", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "rid", referencedColumnName = "id")})
    private List<Role> roles;
}
