package com.company.converters;

import com.company.commands.RecipeCommand;
import com.company.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    public static final Long ID = new Long(1L);
    public static final Long INGREDIENT_ID = new Long(2L);
    public static final Long NOTES_ID = new Long(3L);
    public static final Long CATEGORY_ID = new Long(4L);
    public static final String DESCRIPTION = "description";
    public static final Integer PREP_TIME = 15;
    public static final Integer COOK_TIME = 20;
    public static final Integer SERVINGS = 7;
    public static final String SOURCE = "source";
    public static final String URL = "http://www.example.com";
    public static final String DIRECTIONS = "directions";
    public static final Difficulty DIFFICULTY = Difficulty.MODERATE;

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    IngredientToIngredientCommand ingredientToIngredientCommand;
    NotesToNotesCommand notesToNotesCommand;
    CategoryToCategoryCommand categoryToCategoryCommand;
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
        ingredientToIngredientCommand = new IngredientToIngredientCommand(unitOfMeasureToUnitOfMeasureCommand);
        notesToNotesCommand = new NotesToNotesCommand();
        categoryToCategoryCommand = new CategoryToCategoryCommand();
        recipeToRecipeCommand = new RecipeToRecipeCommand(ingredientToIngredientCommand,
                notesToNotesCommand, categoryToCategoryCommand);
    }

    @Test
    public void testNullObj() throws Exception{
        assertNull(recipeToRecipeCommand.convert(null));
    }

    @Test
    public void testEmptyObj() throws Exception{
        assertNotNull(recipeToRecipeCommand.convert(new Recipe()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);

        Ingredient ingredient = new Ingredient();
        Set<Ingredient> ingredientSet = new HashSet<>();
        ingredient.setId(INGREDIENT_ID);
        ingredientSet.add(ingredient);
        recipe.setIngredients(ingredientSet);

        recipe.setDifficulty(DIFFICULTY);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        Category category = new Category();
        category.setId(CATEGORY_ID);
        Set<Category> categorySet = new HashSet<>();
        categorySet.add(category);
        recipe.setCategories(categorySet);

        //when
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(recipe);

        //then
        assertNotNull(recipeCommand);
        assertEquals(ID, recipeCommand.getId());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertNotNull(recipeCommand.getNotes());
        assertEquals(NOTES_ID, notes.getId());
        assertNotNull(recipeCommand.getIngredients());
        assertNotNull(recipeCommand.getCategories());
        assertEquals(1, recipeCommand.getIngredients().size());
        assertEquals(1, recipeCommand.getCategories().size());
        assertEquals(INGREDIENT_ID, recipeCommand.getIngredients()
            .iterator().next().getId());
        assertEquals(CATEGORY_ID, recipeCommand.getCategories()
            .iterator().next().getId());
    }

}