package com.edgar.taco.repositories;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.edgar.taco.models.Ingredient;
import com.edgar.taco.models.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Taco save(Taco taco) {
		Long tacoId = saveTacoInfo(taco);
		taco.setId(tacoId);
		for (Ingredient ingredient : taco.getIngredients()) {
			saveTacoIngredient(tacoId, ingredient);
		}
		return taco;
	}
	
	private Long saveTacoInfo(Taco taco) {
		taco.setCreatedAt(new Date());
		PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
				"insert into taco (name, createdAt) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP
				);
		preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
		
		PreparedStatementCreator statement =  preparedStatementCreatorFactory.newPreparedStatementCreator(
				Arrays.asList(taco.getName(), taco.getCreatedAt())
		);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int rows = jdbcTemplate.update(statement, keyHolder);
		System.out.println("rows " + rows);
		return keyHolder.getKey().longValue();
	}
	
	private void saveTacoIngredient(Long tacoId, Ingredient ingredientId) {
		jdbcTemplate.update("insert into taco_ingredients (taco_id, ingredient_id) value (?, ?)", tacoId, ingredientId);
	}

}
