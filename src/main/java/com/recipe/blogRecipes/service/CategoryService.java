package com.recipe.blogRecipes.service;

import com.recipe.blogRecipes.entity.Category;
import com.recipe.blogRecipes.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Optional<Category> findById (String category){
        return categoryRepository.findById(category);
    }

    public void save(Category saveCategory){categoryRepository.save(saveCategory);}
}
