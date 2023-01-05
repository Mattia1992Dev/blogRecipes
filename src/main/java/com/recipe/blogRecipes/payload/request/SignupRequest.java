package com.recipe.blogRecipes.payload.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class SignupRequest {

	@NotBlank @Size(max=20, min=5) //annotazioni di validazione che fanno parte del javax.validation.constraint
	private String username;       //ci deve essere almeno un carattere che non sia spazio. Possiamo mettere minimo e massimo lunghezza

	@Pattern(regexp = "^[a-zA-Z0-9]{5,15}$",
			message = "Password must be of 5 to 15 length with no special characters")
	private String password;

}
