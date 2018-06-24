package com.company.services;

import com.company.converters.RecipeCommandToRecipe;
import com.company.converters.RecipeToRecipeCommand;
import com.company.domain.Recipe;
import com.company.exceptions.NotFoundException;
import com.company.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe,
                recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdTest() throws Exception{
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        //then
        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, never()).findAll();
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdTestNotFound() throws Exception{
        //given
        Optional<Recipe> recipeOptional = Optional.empty();
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //when
        Recipe recipe = recipeService.findById(1L);
    }

    @Test
    public void getAllRecipes() throws Exception {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesDate = new HashSet<>();
        recipesDate.add(recipe);

        when(recipeService.getAllRecipes()).thenReturn(recipesDate);

        Set<Recipe> recipes = recipeService.getAllRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, never()).findById(anyLong());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() throws Exception{
        //given
        Long idToDelete = Long.valueOf(2L);

        //when
        recipeService.deleteById(idToDelete);

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}