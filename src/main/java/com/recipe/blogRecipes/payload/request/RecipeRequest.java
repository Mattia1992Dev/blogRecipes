package com.recipe.blogRecipes.payload.request;


import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class RecipeRequest {

    @NotBlank @Size(max=100, min = 3)
    private String title;

    @NotBlank @Size(max=100, min = 3)
    private String ingredients;

    @NotBlank @Size(min=100, max = 65535) //il massimo deve corrispondere al text del DB 65535
    private String content;


}
