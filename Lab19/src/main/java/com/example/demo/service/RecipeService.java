package com.example.demo.service;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Recipe;
import com.example.demo.repository.RecipeRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository, EntityManager entityManager) {
        this.recipeRepository = recipeRepository;
    }


    @Transactional(readOnly = true)
    public List<Recipe> findRecipesByPartOfName(String name) {
        return recipeRepository.findByNameContaining(name);
    }

    @Transactional(readOnly = true)
    public List<Recipe> findRecipesByExactName(String name) {
        return recipeRepository.findByName(name);
    }

    public void addRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(Recipe.builder().name("Борщ").ingredients(List.of(
                Ingredient.builder().name("Капуста").quantity(0.5).unit("кг").build(),
                Ingredient.builder().name("Свекла").quantity(0.3).unit("кг").build(),
                Ingredient.builder().name("Картофель").quantity(0.4).unit("кг").build()
        )).build());
        recipes.add(Recipe.builder().name("Оливье").ingredients(List.of(
                Ingredient.builder().name("Картофель").quantity(0.2).unit("кг").build(),
                Ingredient.builder().name("Морковь").quantity(0.1).unit("кг").build(),
                Ingredient.builder().name("Колбаса").quantity(0.2).unit("кг").build()
        )).build());

        recipes.forEach(this::addRecipe);
    }

    @Transactional
    public void addRecipe(Recipe recipe) {
        // Установить для каждого ингредиента ссылку на рецепт
        recipe.getIngredients().forEach(ingredient -> ingredient.setRecipe(recipe));
        recipeRepository.save(recipe);
    }

    @Transactional
    public void deleteRecipeByName(String name) {
        List<Recipe> recipes = recipeRepository.findByName(name);
        recipeRepository.deleteAll(recipes);
    }
}
