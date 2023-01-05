package com.recipe.blogRecipes.payload.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class SigninRequest {
	
    @NotBlank @Size(min=5)
    private String username; // Dichiaro una variabile scritta così per comprendere cosa passo. Devi inserire Username oppure EMail

    @NotBlank @Size(min=5, max=15)
    private String password;

}
