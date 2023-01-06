package com.recipe.blogRecipes.controller;

import com.recipe.blogRecipes.entity.Category;
import com.recipe.blogRecipes.entity.User;
import com.recipe.blogRecipes.security.CurrentUser;
import com.recipe.blogRecipes.security.UserPrincipal;
import com.recipe.blogRecipes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

@RestController
@RequestMapping("category")
@Validated
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PutMapping("/{category}")
    public ResponseEntity<?> save(@PathVariable @NotBlank @Size(max = 50, min = 2) String category, @CurrentUser UserPrincipal userPrincipal) {
        //verificare he non esiste gia la categoria che andremmo ad'inserire
        if ((categoryService.getUserAuthoritySQL(userPrincipal.getId())==1)||(userPrincipal.getId()==2)){
            Optional<Category> cat = categoryService.findById(category);
            if (cat.isPresent())
                return new ResponseEntity<String>("Category already present", HttpStatus.BAD_REQUEST);
            //se non esiste, persisterla sul db
            Category c = new Category(
                    category,
                    new User(userPrincipal.getId())
            );
            categoryService.save(c);

            return new ResponseEntity<String>("New Category " + category + " added", HttpStatus.CREATED);
        }

        else {
            return new ResponseEntity<String>("User not autorizated", HttpStatus.FORBIDDEN);
        }

    }

    /*@PutMapping("/{category}")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity<?> save(@PathVariable @NotBlank @Size(max = 50, min = 2) String category){
        return new ResponseEntity<String>("User not autorizated" , HttpStatus.FORBIDDEN);
    }*/


}
