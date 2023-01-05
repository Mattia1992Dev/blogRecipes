package com.recipe.blogRecipes.entity;

import com.recipe.blogRecipes.entity.common.Creation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter @Setter @NoArgsConstructor
public class Comment extends Creation{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false)
    private String comment;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="author", nullable = false)
    private User author;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "recipe_id", nullable = false)
    private Recipe recipe;

    public Comment(String comment, User author, Recipe recipe){
        this.comment = comment;
        this.author = author;
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return id == comment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
