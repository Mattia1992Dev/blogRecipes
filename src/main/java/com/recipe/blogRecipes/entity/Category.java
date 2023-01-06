package com.recipe.blogRecipes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class Category {

    @Id
    @Column(length = 50)
    private String categoryName;


    private boolean visible= true;

    public Category (String categoryName, User author){
        this.categoryName= categoryName;
        this.user= author;
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="author", nullable = false)
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return categoryName.equals(category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName);
    }
}
