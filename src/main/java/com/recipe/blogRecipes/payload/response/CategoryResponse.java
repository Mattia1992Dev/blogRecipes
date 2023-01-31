package com.recipe.blogRecipes.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CategoryResponse {

    private String categoryname; // per distinguere da entità

    @Override
    public String toString() {
        return "nome categoria: " + categoryname;
    }
}
