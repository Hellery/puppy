package com.puppy.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"NAME"})})
public class Role{
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
