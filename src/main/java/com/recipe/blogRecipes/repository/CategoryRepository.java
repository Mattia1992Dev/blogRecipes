package com.recipe.blogRecipes.repository;

import com.recipe.blogRecipes.entity.Category;
import com.recipe.blogRecipes.payload.response.CategoryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository  extends JpaRepository<Category, String> {


    // SQL nativo
    //SELECT authority_id FROM `user_authorities` WHERE user_id=1
    @Query(value = "SELECT authority_id FROM `user_authorities` WHERE user_id = :user_id", nativeQuery = true)
    long getUserAuthoritySQL(@Param("user_id") long user_id);

    @Query(value = "SELECT new com.recipe.blogRecipes.payload.response.CategoryResponse(" +     //è tutto il package della classe generata e mi serve il costruttore
            "c.categoryName" +
            ")FROM Category c " + // ricordati di lasciare spazio senò viene scritto cWHERE tutto attaccato
            "WHERE c.visible=true")

    List<String> getCategoryByVisibleTrue();//

    @Query(value = "SELECT new com.recipe.blogRecipes.payload.response.CategoryResponse(" +     //è tutto il package della classe generata e mi serve il costruttore
            "c.categoryName" +
            ")FROM Category c ") // ricordati di lasciare spazio senò viene scritto cWHERE tutto attaccato
    List<String> getCategoryByVisibleTrueAndFalse();



}
