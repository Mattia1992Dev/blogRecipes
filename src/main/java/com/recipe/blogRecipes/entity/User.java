package com.recipe.blogRecipes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recipe.blogRecipes.entity.common.CreationUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class User extends CreationUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (length = 20, nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column (nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_authorities",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    Set<Authority> authorities= new HashSet<>();

    public User(String username, String password, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }


    public User(long id, String username,Set<Authority> authorities) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
    }

    public User(long number) {
        this.id = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
