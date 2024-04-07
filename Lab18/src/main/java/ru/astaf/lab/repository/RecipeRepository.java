package ru.astaf.lab.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.astaf.lab.mapper.IngredientRowMapper;
import ru.astaf.lab.mapper.RecipeRowMapper;
import ru.astaf.lab.model.Ingredient;
import ru.astaf.lab.model.Recipe;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@EnableTransactionManagement
@Component
public class RecipeRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RecipeRepository(DriverManagerDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Борщ", List.of(
                new Ingredient("Капуста", 0.5, "кг"),
                new Ingredient("Свекла", 0.3, "кг"),
                new Ingredient("Картофель", 0.4, "кг")
        )));
        recipes.add(new Recipe("Оливье", List.of(
                new Ingredient("Картофель", 0.2, "кг"),
                new Ingredient("Морковь", 0.1, "кг"),
                new Ingredient("Колбаса", 0.2, "кг")
        )));

        recipes.forEach(this::addRecipe);
    }

    @Transactional
    public void addRecipe(Recipe recipe) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO recipes (name) VALUES (?)", new String[]{"id"});
            ps.setString(1, recipe.getName());
            return ps;
        }, keyHolder);

        int recipeId = keyHolder.getKey().intValue();

//        recipe.getIngredients().forEach(ingredient -> {
//            jdbcTemplate.update("INSERT INTO ingredients (recipe_id, name, quantity, unit) VALUES (?, ?, ?, ?)",
//                    recipeId, ingredient.getName(), ingredient.getQuantity(), ingredient.getUnit());
//        });
        List<Object[]> batchArgs = new ArrayList<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            Object[] values = {
                    recipeId,
                    ingredient.getName(),
                    ingredient.getQuantity(),
                    ingredient.getUnit()
            };
            batchArgs.add(values);
        }

        jdbcTemplate.batchUpdate("INSERT INTO ingredients (recipe_id, name, quantity, unit) VALUES (?, ?, ?, ?)", batchArgs);
    }

    public void initDb() {
        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS recipes (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(255) NOT NULL)"
        );


        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS ingredients (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        " recipe_id INT," +
                        "FOREIGN KEY (recipe_id) REFERENCES recipes(id), " +
                        "name VARCHAR(255) NOT NULL, " +
                        "quantity REAL, " +
                        "unit VARCHAR(100) NOT NULL" +
                        ")"
        );
    }

    @Transactional
    public List<Recipe> findRecipesByPartOfName(String name) {
        String sql = "SELECT * FROM recipes WHERE name LIKE ?";
        List<Recipe> recipes = jdbcTemplate.query(sql, new Object[]{"%" + name + "%"}, new RecipeRowMapper());

        recipes.forEach(this::loadIngredientsForRecipe);

        return recipes;
    }

    @Transactional
    public List<Recipe> findRecipesByExactName(String name) {
        String sql = "SELECT * FROM recipes WHERE name LIKE ?";
        List<Recipe> recipes = jdbcTemplate.query(sql, new Object[]{name}, new RecipeRowMapper());

        recipes.forEach(this::loadIngredientsForRecipe);

        return recipes;
    }

    public void loadIngredientsForRecipe(Recipe recipe) {
        List<Ingredient> ingredients = jdbcTemplate.query(
                "SELECT * FROM ingredients WHERE recipe_id = ?",
                new Object[]{recipe.getId()},
                new IngredientRowMapper());
        recipe.setIngredients(ingredients);
    }

    @Transactional
    public void deleteRecipeByName(String recipeName) {
        List<Integer> recipeIds = jdbcTemplate.query(
                "SELECT id FROM recipes WHERE name = ?",
                new Object[]{recipeName},
                (rs, rowNum) -> rs.getInt("id")
        );

        if (recipeIds.isEmpty()) {
            throw new IllegalArgumentException("Not found recipe name = " + recipeName);
        } else {
            // Для каждого найденного ID удаляем связанные ингредиенты и сам рецепт
            for (Integer recipeId : recipeIds) {
                jdbcTemplate.update("DELETE FROM ingredients WHERE recipe_id = ?", recipeId);
                jdbcTemplate.update("DELETE FROM recipes WHERE id = ?", recipeId);
            }
        }
    }

}
