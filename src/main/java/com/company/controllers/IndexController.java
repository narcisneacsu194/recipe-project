package com.company.controllers;

import com.company.domain.Category;
import com.company.domain.Recipe;
import com.company.domain.UnitOfMeasure;
import com.company.repositories.CategoryRepository;
import com.company.repositories.UnitOfMeasureRepository;
import com.company.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
public class IndexController {

    private RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        log.debug("Getting Index page");
        Set<Recipe> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "index";
    }
}
