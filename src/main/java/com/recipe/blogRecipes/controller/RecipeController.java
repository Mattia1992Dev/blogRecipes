package com.recipe.blogRecipes.controller;

import com.recipe.blogRecipes.entity.Recipe;
import com.recipe.blogRecipes.entity.User;
import com.recipe.blogRecipes.payload.request.RecipeRequest;
import com.recipe.blogRecipes.security.CurrentUser;
import com.recipe.blogRecipes.security.UserPrincipal;
import com.recipe.blogRecipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("recipe")
public class RecipeController {


    @Autowired
    RecipeService recipeService;
    @PutMapping
    @PreAuthorize("hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')")
    //ROLE_ è OBBLIGATORIO se uso SpringSecurity    //annotazione @CurrentUser creata
    public ResponseEntity<?> save(@Valid @RequestBody RecipeRequest request, @CurrentUser UserPrincipal userPrincipal) { //@RequestBody perchè gli passiamo un request
        //Passiamo i dati della nostra entità ricetta e necessita anche l'autore
        // controllo preventivo sull'unicità del titolo
        if (recipeService.existsByTitle(request.getTitle()))
            return new ResponseEntity<String>("A post with title" + request.getTitle() + " is already present", HttpStatus.BAD_REQUEST);
        //istanziare oggetto Post
        Recipe r = new Recipe(request.getTitle(), request.getContent(), request.getIngredients(),new User(userPrincipal.getId()));
        recipeService.save(r);
        return new ResponseEntity<String>("new recipe created[" + r.getId() + "]", HttpStatus.CREATED);
    }
}
