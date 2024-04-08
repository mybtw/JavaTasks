package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@NamedEntityGraph(
        name = Recipe.RECIPE_WITH_INGREDIENTS,
        attributeNodes = {@NamedAttributeNode("ingredients")}
)
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients;

    public static final String RECIPE_WITH_INGREDIENTS = "withIngredients";

}
