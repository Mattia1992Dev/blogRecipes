package com.recipe.blogRecipes.service;

import com.recipe.blogRecipes.entity.Recipe;
import com.recipe.blogRecipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;
    public void save(Recipe p){
        recipeRepository.save(p);
    }

    public boolean existsByTitle(String title){
        return recipeRepository.existsByTitle(title);
    }

    public Optional<Recipe> findById(long id){
        return recipeRepository.findById(id);

    }


}
