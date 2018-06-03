package com.company.controllers;

import com.company.domain.Recipe;
import com.company.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping
    public String getRecipes(Model model){
        List<Recipe> recipes = recipeService.getAllRecipes();

        model.addAttribute("recipes", recipes);

        return "recipes";
    }
}
