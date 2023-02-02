package com.recipe.blogRecipes.service;

import com.recipe.blogRecipes.entity.Rating;
import com.recipe.blogRecipes.entity.Recipe;
import com.recipe.blogRecipes.repository.RatingRepository;
import com.recipe.blogRecipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {


    @Autowired RecipeRepository recipeRepository;

    @Autowired
    RatingRepository ratingRepository;

    public Optional<Recipe> findById(long id){
        return recipeRepository.findById(id);

    }

    public void save(Rating r){
        ratingRepository.save(r);
    }
}
