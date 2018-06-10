package com.company.controllers;

import com.company.commands.RecipeCommand;
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

    public IngredientController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String getIngredients(@PathVariable Long id, Model model){
        RecipeCommand recipeCommand =
                recipeService.findCommandById(id);
        model.addAttribute("recipe", recipeCommand);
        return "recipe/ingredient/list";
    }
}
