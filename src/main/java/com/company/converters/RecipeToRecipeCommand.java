package com.company.converters;

import com.company.commands.RecipeCommand;
import com.company.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{

    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private NotesToNotesCommand notesToNotesCommand;
    private CategoryToCategoryCommand categoryToCategoryCommand;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 NotesToNotesCommand notesToNotesCommand, CategoryToCategoryCommand categoryToCategoryCommand){
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.notesToNotesCommand = notesToNotesCommand;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if(recipe == null){
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipe.getId());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setDirections(recipe.getDirections());

        if(recipe.getIngredients() != null && recipe.getIngredients().size() > 0){
            recipe.getIngredients().forEach(
                    ingredient -> recipeCommand.getIngredients().add(
                            ingredientToIngredientCommand.convert(ingredient)
                    )
            );
        }

        recipeCommand.setDifficulty(recipe.getDifficulty());

        recipeCommand.setImage(recipe.getImage());

        recipeCommand.setNotes(notesToNotesCommand.convert(recipe.getNotes()));

        if(recipe.getCategories() != null && recipe.getCategories().size() > 0){
            recipe.getCategories().forEach(
                    category -> recipeCommand.getCategories().add(
                            categoryToCategoryCommand.convert(category)
                    )
            );
        }

        return recipeCommand;
    }
}
