package com.recipe.blogRecipes.service;

import com.recipe.blogRecipes.entity.Category;
import com.recipe.blogRecipes.payload.response.CategoryResponse;
import com.recipe.blogRecipes.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired CategoryRepository categoryRepository; //istanzio l'oggetto già disponibile

    public Optional<Category> findById (String category){
        return categoryRepository.findById(category);
    }

    public void save(Category cat){
        categoryRepository.save(cat);}

    public List<String> findByVisibleTrue (){
        return categoryRepository.getCategoryByVisibleTrue();
    }

    public long getUserAuthoritySQL(long user_id){
        return categoryRepository.getUserAuthoritySQL(user_id);
    }


    public List<String> getCategoryByVisibleTrueAndFalse (){
        return categoryRepository.getCategoryByVisibleTrueAndFalse();
    }



}
