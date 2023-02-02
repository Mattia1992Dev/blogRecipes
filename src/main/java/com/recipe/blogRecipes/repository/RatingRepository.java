package com.recipe.blogRecipes.repository;


import com.recipe.blogRecipes.entity.Rating;
import com.recipe.blogRecipes.entity.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository <Rating, RatingId> {
}
