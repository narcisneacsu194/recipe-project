package com.company.converters;

import com.company.commands.IngredientCommand;
import com.company.commands.UnitOfMeasureCommand;
import com.company.domain.Ingredient;
import com.company.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class IngredientCommandToIngredientTest {
    public static final Long ID = new Long(1L);
    public static final String DESCRIPTION = "Description";
    public static final BigDecimal AMOUNT = new BigDecimal(5);
    public static final Long UOM_ID = new Long(2L);

    @Mock
    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    IngredientCommandToIngredient ingredientCommandToIngredient;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientCommandToIngredient =
                new IngredientCommandToIngredient(unitOfMeasureCommandToUnitOfMeasure);
    }

    @Test
    public void testNullObject() throws Exception{
        assertNull(ingredientCommandToIngredient.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(ingredientCommandToIngredient.convert(new IngredientCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        ingredientCommand.setUnitOfMeasure(unitOfMeasureCommand);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID);
        when(unitOfMeasureCommandToUnitOfMeasure.convert(unitOfMeasureCommand))
                .thenReturn(unitOfMeasure);

        //when
        Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);

        //then
        assertNotNull(ingredient);
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
        verify(unitOfMeasureCommandToUnitOfMeasure, times(1)).convert(any());
    }

    @Test
    public void convertWithNullUOM() throws Exception{
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);

        //when
        Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
    }

}