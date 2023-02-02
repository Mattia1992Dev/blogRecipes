package com.recipe.blogRecipes.controller;

import com.recipe.blogRecipes.entity.Recipe;
import com.recipe.blogRecipes.security.CurrentUser;
import com.recipe.blogRecipes.security.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("comments")
public class CommentController {

   /* @PutMapping
    @PreAuthorize("hasRole('ROLE_READER')")
    public ResponseEntity<?> save(@RequestBody @Valid CommentRequest request, @CurrentUser UserPrincipal userPrincipal){

        Optional<Recipe> p = postService.findById(request.getPostId());
        if (p.isEmpty())
            return new ResponseEntity("Post not found", HttpStatus.NOT_FOUND);


        Comment c = new Comment(request.getComment(),new User(userPrincipal.getId()),new Post(request.getPostId()));

        commentService.save(c);
        return new ResponseEntity<>("Comment created", HttpStatus.CREATED);

    }*/
}
