package com.recipe.blogRecipes.controller;

import com.recipe.blogRecipes.entity.Rating;
import com.recipe.blogRecipes.entity.RatingId;
import com.recipe.blogRecipes.entity.Recipe;
import com.recipe.blogRecipes.entity.User;
import com.recipe.blogRecipes.security.CurrentUser;
import com.recipe.blogRecipes.security.UserPrincipal;
import com.recipe.blogRecipes.service.RatingService;
import com.recipe.blogRecipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("rating")
public class RatingController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    RatingService ratingService;

    @PutMapping("/{recipeId}")
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity vote(@PathVariable long recipeId, @CurrentUser UserPrincipal userPrincipal){

        Optional<Recipe> p = recipeService.findById(recipeId);
        if (p.isEmpty())
            return new ResponseEntity("Recipe not found", HttpStatus.NOT_FOUND);



        Rating r = new Rating(new RatingId(new User(userPrincipal.getId()), new Recipe(recipeId)), true  );
        ratingService.save(r);

        return new ResponseEntity("Rating saved ", HttpStatus.OK); // se trova primary key va automaticamente in update



    }
}
