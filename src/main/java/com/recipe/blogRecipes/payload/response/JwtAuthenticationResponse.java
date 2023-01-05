package com.recipe.blogRecipes.payload.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter @Getter @NoArgsConstructor
public class JwtAuthenticationResponse {

	private long id;
	private String username;
	private Set<String> authorities;
	private String token;
	
	public JwtAuthenticationResponse(long id, String username, Set<String> authorities, String token) {
		super();
		this.id = id;
		this.username = username;
		this.authorities = authorities;
		this.token = "Bearer "+token;
	}
}
