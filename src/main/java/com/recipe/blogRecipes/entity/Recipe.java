package com.recipe.blogRecipes.entity;

import com.recipe.blogRecipes.entity.common.CreationUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Recipe extends CreationUpdate{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false,unique = true, length = 100)
    private String title;

    @Column(nullable = false)
    private String ingredients;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private String image;

    private boolean visible=false;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "author", nullable = false)
    private User author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="recipe_categories",
            joinColumns = {@JoinColumn(name="recipe_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="category_name", referencedColumnName = "categoryName")})
    Set<Category> categories = new HashSet<>();

    @OneToMany (mappedBy = "recipe", orphanRemoval = true, cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();



    public Recipe(String title, String ingredients, String content, User author) {
        this.title = title;
        this.ingredients = ingredients;
        this.content = content;
        this.author = author;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe recipe)) return false;
        return id == recipe.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
