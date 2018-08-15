package com.company.controllers;

import com.company.commands.RecipeCommand;
import com.company.domain.Recipe;
import com.company.exceptions.NotFoundException;
import com.company.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

@Slf4j
@Controller
public class RecipeController {
    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        return "recipe/show";
    }

    @GetMapping("/recipes")
    public String getRecipes(Model model){
        log.info("Displaying a list of recipes from the database.");
        Set<Recipe> recipes = recipeService.getAllRecipes();

        model.addAttribute("recipes", recipes);

        return "recipes";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return RECIPE_RECIPEFORM_URL;
    }

    @PostMapping("/recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command,
                               BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(
                    objectError -> {
                        log.debug(objectError.toString());
                    }
            );

            return RECIPE_RECIPEFORM_URL;
        }

        RecipeCommand recipeCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + recipeCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));

        model.addAttribute("recipe", recipeCommand);

        return RECIPE_RECIPEFORM_URL;
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id){
        log.debug("Deleting id: " + id);
        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }

}
