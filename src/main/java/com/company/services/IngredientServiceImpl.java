package com.company.services;

import com.company.commands.IngredientCommand;
import com.company.converters.IngredientCommandToIngredient;
import com.company.converters.IngredientToIngredientCommand;
import com.company.domain.Ingredient;
import com.company.domain.Recipe;
import com.company.repositories.RecipeRepository;
import com.company.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    private RecipeRepository recipeRepository;
    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand, UnitOfMeasureRepository unitOfMeasureRepository, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()){
            log.error("recipe id not found. Id: " +
                recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional =
                recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
                .findFirst();

        if(!ingredientCommandOptional.isPresent()){
            log.error("ingredient id not found: " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());
        if(!recipeOptional.isPresent()){
            log.error("Recipe not found for id: " + ingredientCommand.getRecipeId());
            return new IngredientCommand();
        }else{
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional =
                    recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredient = ingredientOptional.get();
                ingredient.setDescription(ingredientCommand.getDescription());
                ingredient.setAmount(ingredientCommand.getAmount());
                ingredient.setUnitOfMeasure(unitOfMeasureRepository
                        .findById(ingredientCommand.getUnitOfMeasure().getId())
                .orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));
            }else{
                Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients()
                    .stream().filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientCommand.getId()))
                    .findFirst();

            if(!savedIngredientOptional.isPresent()){
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredient -> recipeIngredient.getDescription().equals(ingredientCommand.getDescription()))
                        .filter(recipeIngredient -> recipeIngredient.getAmount().equals(ingredientCommand.getAmount()))
                        .filter(recipeIngredient -> recipeIngredient.getUnitOfMeasure().getId().equals(ingredientCommand.getUnitOfMeasure().getId()))
                        .findFirst();
            }

            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }
    }

    @Override
    public void deleteIngredient(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isPresent()){
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional= recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientId))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredient = ingredientOptional.get();
                recipe.getIngredients().remove(ingredient);
                ingredient.setRecipe(null);

                recipeRepository.save(recipe);
            }

        }else{
            log.debug("Recipe doesn't exist. Id: " + recipeId);
        }
    }
}
