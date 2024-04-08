package com.example.demo.repository;

import com.example.demo.model.Recipe;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    @EntityGraph(value = Recipe.RECIPE_WITH_INGREDIENTS)
    List<Recipe> findByNameContaining(String name);
    @EntityGraph(value = Recipe.RECIPE_WITH_INGREDIENTS)
    List<Recipe> findByName(String name);
    void deleteByName(String name);
}
