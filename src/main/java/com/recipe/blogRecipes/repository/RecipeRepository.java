package com.recipe.blogRecipes.repository;

import com.recipe.blogRecipes.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {

    boolean existsByTitle(String title);

}
