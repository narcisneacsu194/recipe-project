package com.company.converters;

import com.company.commands.RecipeCommand;
import com.company.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe>{

    private IngredientCommandToIngredient ingredientCommandToIngredient;
    private NotesCommandToNotes notesCommandToNotes;
    private CategoryCommandToCategory categoryCommandToCategory;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientCommandToIngredient,
                                 NotesCommandToNotes notesCommandToNotes,
                                 CategoryCommandToCategory categoryCommandToCategory){
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.notesCommandToNotes = notesCommandToNotes;
        this.categoryCommandToCategory = categoryCommandToCategory;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if(recipeCommand == null){
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setDirections(recipeCommand.getDirections());

        if(recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
            recipeCommand.getIngredients().forEach(
                    ingredient -> recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredient))
            );
        }

        recipe.setDifficulty(recipeCommand.getDifficulty());

        recipe.setNotes(notesCommandToNotes.convert(recipeCommand.getNotes()));

        if(recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0){
            recipeCommand.getCategories().forEach(
                category -> recipe.getCategories().add(categoryCommandToCategory.convert(category)));
        }


        return recipe;
    }
}
