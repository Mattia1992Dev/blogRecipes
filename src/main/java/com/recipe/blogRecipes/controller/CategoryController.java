package com.recipe.blogRecipes.controller;

import com.recipe.blogRecipes.entity.Category;
import com.recipe.blogRecipes.entity.User;
import com.recipe.blogRecipes.payload.response.CategoryResponse;
import com.recipe.blogRecipes.security.CurrentUser;
import com.recipe.blogRecipes.security.UserPrincipal;
import com.recipe.blogRecipes.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("category")
@Validated
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PutMapping("/{category}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_WRITER')")
    public ResponseEntity<?> save(@PathVariable @NotBlank @Size(max = 50, min = 2) String category, @CurrentUser UserPrincipal userPrincipal) {
        //verificare he non esiste gia la categoria che andremmo ad'inserire
        //if ((categoryService.getUserAuthoritySQL(userPrincipal.getId())==1)||(userPrincipal.getId()==2)){ Implementato codice in caso non volessi usare Spring Security
            Optional<Category> categoria = categoryService.findById(category);
            if (categoria.isPresent())
                return new ResponseEntity<String>("Category already present", HttpStatus.BAD_REQUEST);
            //se non esiste, persisterla sul db
            Category c = new Category(
                    category,
                    new User(userPrincipal.getId())
            );
            categoryService.save(c);

            return new ResponseEntity<String>("New Category " + category + " added", HttpStatus.CREATED);


      /*  else {
            return new ResponseEntity<String>("User not autorizated", HttpStatus.FORBIDDEN);

        }*/

    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')") //Peremetto cambio visibilità solo al admin
    //è utile con la rollback per non perdere coerenza dei dati con questo non c'è bisogno del save perchè una volta ottenuto l'oggetto è gia persistente
    @PatchMapping("/{categoria}")
    @Transactional
    public ResponseEntity<?> cambiaVisibilita(@PathVariable @NotBlank @Size(max = 50, min = 2) String categoria) {
        Optional<Category> categorie = categoryService.findById(categoria);
        if (categorie.isPresent()) {
            categorie.get().setVisible(!categorie.get().isVisible()); // eseguo lo switch fra categorie fra vero o falso
            return new ResponseEntity<>("change visibility to " + categorie.get().isVisible(), HttpStatus.CREATED);
        } else
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/public")
    public ResponseEntity<?> ottieniCategorieVisibili(){

        List<String> categories = categoryService.findByVisibleTrue();
        return new ResponseEntity<>(categories,HttpStatus.OK);//
    }


}
