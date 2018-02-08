package com.puppy.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String password;
    private String ip;
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "uid", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "rid", referencedColumnName = "id")})
    private List<Role> roles;
}
