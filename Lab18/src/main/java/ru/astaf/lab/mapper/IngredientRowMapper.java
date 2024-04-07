package ru.astaf.lab.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.astaf.lab.model.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientRowMapper implements RowMapper<Ingredient> {
    @Override
    public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rs.getInt("id"));
        ingredient.setRecipeId(rs.getInt("recipe_id"));
        ingredient.setName(rs.getString("name"));
        ingredient.setQuantity(rs.getDouble("quantity"));
        ingredient.setUnit(rs.getString("unit"));
        return ingredient;
    }
}
