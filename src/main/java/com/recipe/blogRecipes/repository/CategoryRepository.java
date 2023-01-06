package com.recipe.blogRecipes.repository;

import com.recipe.blogRecipes.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository  extends JpaRepository<Category, String> {


    // SQL nativo
    //SELECT authority_id FROM `user_authorities` WHERE user_id=1
    @Query(value = "SELECT authority_id FROM `user_authorities` WHERE user_id = :user_id", nativeQuery = true)
    long getUserAuthoritySQL(@Param("user_id") long user_id);
}
