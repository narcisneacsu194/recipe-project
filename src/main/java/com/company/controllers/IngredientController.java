package com.company.controllers;

import com.company.commands.RecipeCommand;
import com.company.services.IngredientService;
import com.company.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {
    private RecipeService recipeService;
    private IngredientService ingredientService;

    public IngredientController(RecipeService recipeService,
                                IngredientService ingredientService){
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String getIngredients(@PathVariable String id, Model model){
        RecipeCommand recipeCommand =
                recipeService.findCommandById(Long.valueOf(id));
        model.addAttribute("recipe", recipeCommand);
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String ingredientId,
                                       Model model){
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),
                        Long.valueOf(ingredientId)));

        return "recipe/ingredient/show";
    }
}
