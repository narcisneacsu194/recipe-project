package com.company.bootstrap;

import com.company.domain.*;
import com.company.repositories.CategoryRepository;
import com.company.repositories.RecipeRepository;
import com.company.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DevBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
                        UnitOfMeasureRepository unitOfMeasureRepository){
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(initData());
    }

    private List<Recipe> initData(){

        List<Recipe> recipes = new ArrayList<>();

        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository
                .getUnitOfMeasureByDescription("teaspoon");

        if(!teaspoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tbspUomOptional = unitOfMeasureRepository
                .getUnitOfMeasureByDescription("Tbsp");

        if(!tbspUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tablespoonsUomOptional = unitOfMeasureRepository
                .getUnitOfMeasureByDescription("tablespoons");

        if(!tablespoonsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository
                .getUnitOfMeasureByDescription("dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository
                .getUnitOfMeasureByDescription("tablespoon");

        if(!tablespoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository
                .getUnitOfMeasureByDescription("cups");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository
                .getUnitOfMeasureByDescription("pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository
                .getUnitOfMeasureByDescription("cup");

        if(!cupUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }


        Optional<Category> mexicanCategoryOptional = categoryRepository.getCategoryByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category Not Found");
        }


        UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasure tbspUom = tbspUomOptional.get();
        UnitOfMeasure tablespoonsUom = tablespoonsUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();

        BigDecimal oneHalfDecimal = new BigDecimal(0.5);
        BigDecimal oneDecimal = new BigDecimal(1);
        BigDecimal twoDecimal = new BigDecimal(2);
        BigDecimal threeDecimal = new BigDecimal(3);


        Category mexicanCategory = mexicanCategoryOptional.get();

        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("The BEST guacamole! So easy to make with ripe avocados, salt, serrano chiles, cilantro and lime. Garnish with red radishes or jicama. Serve with tortilla chips. Watch how to make guacamole - it's easy!");
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTime(0);
        guacamoleRecipe.setServings(3);
        guacamoleRecipe.setSource("Simply Recipes");
        guacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamoleRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this perfectGuacamoleRecipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n");



        Set<Ingredient> guacamoleRecipeIngredients = new HashSet<>();
        guacamoleRecipeIngredients.add(new Ingredient("ripe avocados", twoDecimal, null,
                guacamoleRecipe));
        guacamoleRecipeIngredients.add(new Ingredient("Kosher salt", oneHalfDecimal, teaspoonUom,
                guacamoleRecipe));
        guacamoleRecipeIngredients.add(new Ingredient("of fresh lime juice or lemon juice",
                oneDecimal, tbspUom, guacamoleRecipe));
        guacamoleRecipeIngredients.add(new Ingredient("of minced red onion or thinly sliced green onion",
                twoDecimal, tbspUom, guacamoleRecipe));
        guacamoleRecipeIngredients.add(new Ingredient("serrano chiles, stems and seeds removed, minced",
                twoDecimal, null, guacamoleRecipe));
        guacamoleRecipeIngredients.add(new Ingredient("cilantro (leaves and tender stems), finely chopped",
                twoDecimal, tablespoonsUom, guacamoleRecipe));
        guacamoleRecipeIngredients.add(new Ingredient("of freshly grated black pepper", oneDecimal,
                dashUom, guacamoleRecipe));
        guacamoleRecipeIngredients.add(new Ingredient("ripe tomato, seeds and pulp removed, chopped",
                oneHalfDecimal, null, guacamoleRecipe));

        guacamoleRecipe.setIngredients(guacamoleRecipeIngredients);

        guacamoleRecipe.setDifficulty(Difficulty.EASY);

        Set<Category> guacamoleRecipeCategories = new HashSet<>();
        guacamoleRecipeCategories.add(mexicanCategory);
        guacamoleRecipe.setCategories(guacamoleRecipeCategories);

        Notes guacamoleRecipeNotes = new Notes();
        guacamoleRecipeNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!\n" +
                "\n");
        guacamoleRecipe.setNotes(guacamoleRecipeNotes);

        


        Recipe chickenTacosRecipe = new Recipe();
        chickenTacosRecipe.setDescription("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.");
        chickenTacosRecipe.setPrepTime(20);
        chickenTacosRecipe.setCookTime(15);
        chickenTacosRecipe.setServings(6);
        chickenTacosRecipe.setSource("Simply Recipes");
        chickenTacosRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chickenTacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");


        Set<Ingredient> chickenTacosIngredients = new HashSet<>();

        chickenTacosIngredients.add(new Ingredient("ancho chili powder", twoDecimal,
                tablespoonsUom, chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("dried oregano", oneDecimal, teaspoonUom,
                chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("dried cumin", oneDecimal, teaspoonUom,
                chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("sugar", oneDecimal, teaspoonUom,
                chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("salt", oneHalfDecimal, teaspoonUom,
                chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("clove garlic, finely chopped",
                oneDecimal, null, chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("finely grated orange zest", oneDecimal,
                tablespoonUom, chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("fresh-squeezed orange juice", threeDecimal,
                tablespoonsUom, chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("olive oil", twoDecimal, tablespoonsUom,
                chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)",
                new BigDecimal(6), null, chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("small corn tortillas", new BigDecimal(8), null,
                chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("packed baby arugula (3 ounces)", threeDecimal,
                cupsUom, chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("medium ripe avocados, sliced", twoDecimal, null,
                chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("radishes, thinly sliced", new BigDecimal(4), null,
                chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("cherry tomatoes, halved", oneHalfDecimal, pintUom,
                chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("red onion, thinly sliced", new BigDecimal(0.25),
                null, chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("Roughly chopped cilantro", null, null,
                chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("sour cream thinned with 1/4 cup milk",
                oneHalfDecimal, cupUom, chickenTacosRecipe));
        chickenTacosIngredients.add(new Ingredient("lime, cut into wedges", oneDecimal,
                null, chickenTacosRecipe));

        chickenTacosRecipe.setIngredients(chickenTacosIngredients);

        chickenTacosRecipe.setDifficulty(Difficulty.EASY);

        Set<Category> chickenTacosRecipeCategories = new HashSet<>();
        chickenTacosRecipeCategories.add(mexicanCategory);
        chickenTacosRecipe.setCategories(chickenTacosRecipeCategories);

        Notes chickenTacosRecipeNotes = new Notes();
        chickenTacosRecipeNotes.setRecipeNotes("Nothing to add.");
        chickenTacosRecipe.setNotes(chickenTacosRecipeNotes);

        recipes.add(guacamoleRecipe);
        recipes.add(chickenTacosRecipe);

        return recipes;
    }
}
