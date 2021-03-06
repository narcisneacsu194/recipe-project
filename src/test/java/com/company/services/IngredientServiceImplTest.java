package com.company.services;

import com.company.commands.IngredientCommand;
import com.company.converters.IngredientCommandToIngredient;
import com.company.converters.IngredientToIngredientCommand;
import com.company.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.company.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.company.domain.Ingredient;
import com.company.domain.Recipe;
import com.company.repositories.RecipeRepository;
import com.company.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {
    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    @Before
    public void setUp() throws Exception {
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());

        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(recipeRepository,
                ingredientToIngredientCommand,
                unitOfMeasureRepository, ingredientCommandToIngredient);
    }

    @Test
    public void findByRecipeIdAndRecipeIdHappyPath() throws Exception{
        // given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //when
        IngredientCommand ingredientCommand =
                ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //then
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testSaveRecipeCommand() throws Exception{
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe recipe = new Recipe();
        recipe.addIngredient(new Ingredient());
        recipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(recipe);

        //when
        IngredientCommand ingredientCommand =
                ingredientService.saveIngredientCommand(command);

        //then
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    public void testDeleteIngredient() throws Exception{
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(3L);
        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //when
        ingredientService.deleteIngredient(1L, 3L);

        //then
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
}
