package com.recipe.blogRecipes.repository;

import com.recipe.blogRecipes.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, String> {


}
