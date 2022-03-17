package com.edgar.taco.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.edgar.taco.models.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Iterable<Ingredient> findAll() {
		return jdbcTemplate.query("select id, name, type from ingredient", this::mapRowToIngredient);
	}

	@Override
	public Ingredient findOne(String id) {
		return jdbcTemplate.queryForObject("select id, name, type from ingredient where id=?",
												this::mapRowToIngredient, id);
//		return jdbcTemplate.queryForObject("select id, name, type from Ingredient where id=?", 
//					new RowMapper<Ingredient>() {
//						@Override
//						public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
//							return new Ingredient(rs.getString("id"), rs.getString("name"),
//									Ingredient.Type.valueOf(rs.getString("type")));
//						}
//					}, id);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbcTemplate.update("insert into ingredient (id, name, type) values (?, ?, ?)",
								ingredient.getId(),
								ingredient.getName(),
								ingredient.getType().toString());
		return ingredient;
	}
	
	private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNumber) throws SQLException {
		return new Ingredient(resultSet.getString("id"), resultSet.getString("name"),
						Ingredient.Type.valueOf(resultSet.getString("type")));
	}

}
