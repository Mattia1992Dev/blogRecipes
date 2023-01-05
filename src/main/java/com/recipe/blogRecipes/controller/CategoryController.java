package com.recipe.blogRecipes.controller;

import com.recipe.blogRecipes.entity.Category;
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

    /*@PutMapping({"/{category}"})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_WRITER') or hasRole('ROLE_GUEST')")
    public ResponseEntity<?> salvaCategoria(@PathVariable @NotBlank @Size(max =20, min=3) String category){

        Optional<Category> categoria = categoryService.findById(category);
        if (categoria.isPresent())
            return new ResponseEntity<String>("Category already present", HttpStatus.BAD_REQUEST);
        Category saveCategory = new Category(category);
        categoryService.save(saveCategory);

        return new ResponseEntity<String>("New Category " + category + " added", HttpStatus.CREATED);

    }*/


}
