package com.company.converters;

import com.company.commands.CategoryCommand;
import com.company.commands.IngredientCommand;
import com.company.commands.NotesCommand;
import com.company.commands.RecipeCommand;
import com.company.domain.Difficulty;
import com.company.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    public static final Long ID = new Long(1L);
    public static final String DESCRIPTION = "description";
    public static final Integer PREP_TIME = 5;
    public static final Integer COOK_TIME = 10;
    public static final Integer SERVINGS = 4;
    public static final String SOURCE = "source";
    public static final String URL = "http://example.com";
    public static final String DIRECTIONS = "directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Long INGREDIENT_ID = new Long(2L);
    public static final Long NOTES_ID = new Long(3L);
    public static final Long CATEGORY_ID = new Long(4L);

    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
    IngredientCommandToIngredient ingredientCommandToIngredient;
    NotesCommandToNotes notesCommandToNotes;
    CategoryCommandToCategory categoryCommandToCategory;
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureCommandToUnitOfMeasure = new UnitOfMeasureCommandToUnitOfMeasure();
        ingredientCommandToIngredient =
                new IngredientCommandToIngredient(unitOfMeasureCommandToUnitOfMeasure);
        notesCommandToNotes = new NotesCommandToNotes();
        categoryCommandToCategory = new CategoryCommandToCategory();
        recipeCommandToRecipe = new RecipeCommandToRecipe(ingredientCommandToIngredient,
                notesCommandToNotes, categoryCommandToCategory);
    }

    @Test
    public void testNullObj() throws Exception{
        assertNull(recipeCommandToRecipe.convert(null));
    }

    @Test
    public void testEmptyObj() throws Exception{
        assertNotNull(recipeCommandToRecipe.convert(new RecipeCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID);
        Set<IngredientCommand> ingredientCommandSet = new HashSet<>();
        ingredientCommandSet.add(ingredientCommand);
        recipeCommand.setIngredients(ingredientCommandSet);

        recipeCommand.setDifficulty(DIFFICULTY);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        recipeCommand.setNotes(notesCommand);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CATEGORY_ID);
        Set<CategoryCommand> categoryCommandSet = new HashSet<>();
        categoryCommandSet.add(categoryCommand);
        recipeCommand.setCategories(categoryCommandSet);

        //when
        Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);

        //then
        assertNotNull(recipe);
        assertEquals(ID, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertNotNull(recipe.getIngredients());
        assertNotNull(recipe.getCategories());
        assertEquals(1, recipe.getIngredients().size());
        assertEquals(1, recipe.getIngredients().size());
        assertEquals(INGREDIENT_ID,
                recipe.getIngredients().iterator().next().getId());
        assertEquals(CATEGORY_ID,
                recipe.getCategories().iterator().next().getId());
    }

}