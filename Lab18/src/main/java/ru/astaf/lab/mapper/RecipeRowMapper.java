package ru.astaf.lab.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.astaf.lab.model.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeRowMapper implements RowMapper<Recipe> {
    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(rs.getInt("id"));
        recipe.setName(rs.getString("name"));
        return recipe;
    }
}
