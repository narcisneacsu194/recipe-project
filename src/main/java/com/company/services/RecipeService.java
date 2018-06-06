package com.company.services;

import com.company.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getAllRecipes();
    Recipe findById(Long id);
}
