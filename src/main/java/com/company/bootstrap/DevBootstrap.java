package com.company.bootstrap;

import com.company.domain.*;
import com.company.repositories.CategoryRepository;
import com.company.repositories.RecipeRepository;
import com.company.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
        initData();
    }

    private void initData(){
        Recipe perfectGuacamoleRecipe = new Recipe();
        perfectGuacamoleRecipe.setDescription("The BEST guacamole! So easy to make with ripe avocados, salt, serrano chiles, cilantro and lime. Garnish with red radishes or jicama. Serve with tortilla chips. Watch how to make guacamole - it's easy!");
        perfectGuacamoleRecipe.setPrepTime(10);
        perfectGuacamoleRecipe.setCookTime(0);
        perfectGuacamoleRecipe.setServings(3);
        perfectGuacamoleRecipe.setSource("Simply Recipes");
        perfectGuacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        perfectGuacamoleRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
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

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setDescription("ripe avocados");
        ingredient1.setAmount(new BigDecimal(2));
        ingredient1.setUnitOfMeasure(null);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setDescription("Kosher salt");
        ingredient2.setAmount(new BigDecimal(0.5));
        Optional<UnitOfMeasure> unitOfMeasureOptional1 = unitOfMeasureRepository.getUnitOfMeasureByDescription("teaspoon");
        UnitOfMeasure unitOfMeasure1 = unitOfMeasureOptional1.get();
        ingredient2.setUnitOfMeasure(unitOfMeasure1);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setDescription("of fresh lime juice or lemon juice");
        ingredient3.setAmount(new BigDecimal(1));
        Optional<UnitOfMeasure> unitOfMeasureOptional2 = unitOfMeasureRepository.getUnitOfMeasureByDescription("Tbsp");
        UnitOfMeasure unitOfMeasure2 = unitOfMeasureOptional2.get();
        ingredient3.setUnitOfMeasure(unitOfMeasure2);

        Ingredient ingredient4 = new Ingredient();
        ingredient4.setDescription("of minced red onion or thinly sliced green onion");
        ingredient4.setAmount(new BigDecimal(2));
        ingredient4.setUnitOfMeasure(unitOfMeasure2);

        Ingredient ingredient5 = new Ingredient();
        ingredient5.setDescription("serrano chiles, stems and seeds removed, minced");
        ingredient5.setAmount(new BigDecimal(2));

        Ingredient ingredient6 = new Ingredient();
        ingredient6.setDescription("cilantro (leaves and tender stems), finely chopped");
        ingredient6.setAmount(new BigDecimal(2));
        Optional<UnitOfMeasure> unitOfMeasureOptional3 = unitOfMeasureRepository.getUnitOfMeasureByDescription("tablespoons");
        ingredient6.setUnitOfMeasure(unitOfMeasureOptional3.get());

        Ingredient ingredient7 = new Ingredient();
        ingredient7.setDescription("of freshly grated black pepper");
        ingredient7.setAmount(new BigDecimal(1));
        Optional<UnitOfMeasure> unitOfMeasureOptional4 = unitOfMeasureRepository.getUnitOfMeasureByDescription("dash");
        ingredient7.setUnitOfMeasure(unitOfMeasureOptional4.get());

        Ingredient ingredient8 = new Ingredient();
        ingredient8.setDescription("ripe tomato, seeds and pulp removed, chopped");
        ingredient8.setAmount(new BigDecimal(0.5));
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(ingredient1);ingredients.add(ingredient2);
        ingredients.add(ingredient3);ingredients.add(ingredient4);
        ingredients.add(ingredient5);ingredients.add(ingredient6);
        ingredients.add(ingredient7);ingredients.add(ingredient8);
        perfectGuacamoleRecipe.setIngredients(ingredients);
        ingredient1.setRecipe(perfectGuacamoleRecipe);ingredient2.setRecipe(perfectGuacamoleRecipe);
        ingredient3.setRecipe(perfectGuacamoleRecipe);ingredient4.setRecipe(perfectGuacamoleRecipe);
        ingredient5.setRecipe(perfectGuacamoleRecipe);ingredient6.setRecipe(perfectGuacamoleRecipe);
        ingredient7.setRecipe(perfectGuacamoleRecipe);ingredient8.setRecipe(perfectGuacamoleRecipe);


        perfectGuacamoleRecipe.setDifficulty(Difficulty.EASY);

        Optional<Category> categoryOptional1 = categoryRepository.getCategoryByDescription("Mexican");
        Category category = categoryOptional1.get();
        Set<Category> categories = new HashSet<>();
        categories.add(category);
        perfectGuacamoleRecipe.setCategories(categories);
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(perfectGuacamoleRecipe);
        category.setRecipes(recipes);

        Notes notes = new Notes();
        notes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!\n" +
                "\n");
        perfectGuacamoleRecipe.setNotes(notes);
        notes.setRecipe(perfectGuacamoleRecipe);

        


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

        Ingredient ingredient21 = new Ingredient();
        ingredient21.setDescription("ancho chili powder");
        ingredient21.setAmount(new BigDecimal(2));
        ingredient21.setUnitOfMeasure(unitOfMeasureRepository.getUnitOfMeasureByDescription("tablespoons").get());

        Ingredient ingredient22 = new Ingredient();
        ingredient22.setDescription("dried oregano");
        ingredient22.setAmount(new BigDecimal(1));
        Optional<UnitOfMeasure> unitOfMeasureOptional21 = unitOfMeasureRepository.getUnitOfMeasureByDescription("teaspoon");
        UnitOfMeasure unitOfMeasure21 = unitOfMeasureOptional21.get();
        ingredient22.setUnitOfMeasure(unitOfMeasure21);

        Ingredient ingredient23 = new Ingredient();
        ingredient23.setDescription("dried cumin");
        ingredient23.setAmount(new BigDecimal(1));
        ingredient23.setUnitOfMeasure(unitOfMeasure21);

        Ingredient ingredient24 = new Ingredient();
        ingredient24.setDescription("sugar");
        ingredient24.setAmount(new BigDecimal(1));
        ingredient24.setUnitOfMeasure(unitOfMeasure21);

        Ingredient ingredient25 = new Ingredient();
        ingredient25.setDescription("salt");
        ingredient25.setAmount(new BigDecimal(0.5));
        ingredient25.setUnitOfMeasure(unitOfMeasure21);

        Ingredient ingredient26 = new Ingredient();
        ingredient26.setDescription("clove garlic, finely chopped");
        ingredient26.setAmount(new BigDecimal(1));

        Ingredient ingredient27 = new Ingredient();
        ingredient27.setDescription("finely grated orange zest");
        ingredient27.setAmount(new BigDecimal(1));
        Optional<UnitOfMeasure> unitOfMeasureOptional24 = unitOfMeasureRepository.getUnitOfMeasureByDescription("tablespoon");
        ingredient27.setUnitOfMeasure(unitOfMeasureOptional24.get());

        Ingredient ingredient28 = new Ingredient();
        ingredient28.setDescription("fresh-squeezed orange juice");
        ingredient28.setAmount(new BigDecimal(3));
        ingredient28.setUnitOfMeasure(unitOfMeasureRepository.getUnitOfMeasureByDescription("tablespoons").get());

        Ingredient ingredient29 = new Ingredient();
        ingredient29.setDescription("olive oil");
        ingredient29.setAmount(new BigDecimal(2));
        ingredient29.setUnitOfMeasure(unitOfMeasureRepository.getUnitOfMeasureByDescription("tablespoons").get());

        Ingredient ingredient210 = new Ingredient();
        ingredient210.setDescription("skinless, boneless chicken thighs (1 1/4 pounds)");
        ingredient210.setAmount(new BigDecimal(6));

        Ingredient ingredient211 = new Ingredient();
        ingredient211.setDescription("small corn tortillas");
        ingredient211.setAmount(new BigDecimal(8));

        Ingredient ingredient212 = new Ingredient();
        ingredient212.setDescription("packed baby arugula (3 ounces)");
        ingredient212.setAmount(new BigDecimal(3));
        ingredient212.setUnitOfMeasure(unitOfMeasureRepository.getUnitOfMeasureByDescription("cups").get());

        Ingredient ingredient213 = new Ingredient();
        ingredient213.setDescription("medium ripe avocados, sliced");
        ingredient213.setAmount(new BigDecimal(2));

        Ingredient ingredient214 = new Ingredient();
        ingredient214.setDescription("radishes, thinly sliced");
        ingredient214.setAmount(new BigDecimal(4));

        Ingredient ingredient215 = new Ingredient();
        ingredient215.setDescription("cherry tomatoes, halved");
        ingredient215.setAmount(new BigDecimal(0.5));
        ingredient215.setUnitOfMeasure(unitOfMeasureRepository.getUnitOfMeasureByDescription("pint").get());

        Ingredient ingredient216 = new Ingredient();
        ingredient216.setDescription("red onion, thinly sliced");
        ingredient216.setAmount(new BigDecimal(0.25));

        Ingredient ingredient217 = new Ingredient();
        ingredient217.setDescription("Roughly chopped cilantro");

        Ingredient ingredient218 = new Ingredient();
        ingredient218.setDescription("sour cream thinned with 1/4 cup milk");
        ingredient218.setAmount(new BigDecimal(0.5));
        ingredient218.setUnitOfMeasure(unitOfMeasureRepository.getUnitOfMeasureByDescription("cup").get());

        Ingredient ingredient219 = new Ingredient();
        ingredient219.setDescription("lime, cut into wedges");
        ingredient219.setAmount(new BigDecimal(1));

        Set<Ingredient> ingredients2 = new HashSet<>();

        ingredients2.add(ingredient21);ingredients2.add(ingredient22);
        ingredients2.add(ingredient23);ingredients2.add(ingredient24);
        ingredients2.add(ingredient25);ingredients2.add(ingredient26);
        ingredients2.add(ingredient27);ingredients2.add(ingredient28);
        ingredients2.add(ingredient29);ingredients2.add(ingredient210);
        ingredients2.add(ingredient211);ingredients2.add(ingredient212);
        ingredients2.add(ingredient213);ingredients2.add(ingredient214);
        ingredients2.add(ingredient215);ingredients2.add(ingredient216);
        ingredients2.add(ingredient217);ingredients2.add(ingredient218);
        ingredients2.add(ingredient219);

        chickenTacosRecipe.setIngredients(ingredients2);ingredient21.setRecipe(chickenTacosRecipe);
        ingredient22.setRecipe(chickenTacosRecipe);ingredient23.setRecipe(chickenTacosRecipe);
        ingredient24.setRecipe(chickenTacosRecipe);ingredient25.setRecipe(chickenTacosRecipe);
        ingredient26.setRecipe(chickenTacosRecipe);ingredient27.setRecipe(chickenTacosRecipe);
        ingredient28.setRecipe(chickenTacosRecipe);ingredient29.setRecipe(chickenTacosRecipe);
        ingredient210.setRecipe(chickenTacosRecipe);ingredient211.setRecipe(chickenTacosRecipe);
        ingredient212.setRecipe(chickenTacosRecipe);ingredient213.setRecipe(chickenTacosRecipe);
        ingredient214.setRecipe(chickenTacosRecipe);ingredient215.setRecipe(chickenTacosRecipe);
        ingredient216.setRecipe(chickenTacosRecipe);ingredient217.setRecipe(chickenTacosRecipe);
        ingredient218.setRecipe(chickenTacosRecipe);ingredient219.setRecipe(chickenTacosRecipe);

        chickenTacosRecipe.setDifficulty(Difficulty.EASY);

        Optional<Category> categoryOptional21 = categoryRepository.getCategoryByDescription("Mexican");
        Category category2 = categoryOptional21.get();
        Set<Category> categories2 = new HashSet<>();
        categories2.add(category2);
        chickenTacosRecipe.setCategories(categories2);
        Set<Recipe> recipes2 = new HashSet<>();
        recipes2.add(chickenTacosRecipe);
        category2.setRecipes(recipes2);

        Notes notes2 = new Notes();
        notes2.setRecipeNotes("Nothing to add.");
        chickenTacosRecipe.setNotes(notes2);
        notes2.setRecipe(chickenTacosRecipe);

        recipeRepository.save(perfectGuacamoleRecipe);
        recipeRepository.save(chickenTacosRecipe);
    }
}
