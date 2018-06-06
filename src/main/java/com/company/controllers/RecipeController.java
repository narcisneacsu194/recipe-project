package com.company.controllers;

import com.company.domain.Recipe;
import com.company.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Slf4j
@Controller
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        return "recipe/show";
    }

    @RequestMapping("/recipes")
    public String getRecipes(Model model){
        log.info("Displaying a list of recipes from the database.");
        Set<Recipe> recipes = recipeService.getAllRecipes();

        model.addAttribute("recipes", recipes);

        return "recipes";
    }
}
