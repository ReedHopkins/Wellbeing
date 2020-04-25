package com.jcg.mongodb.servlet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecipeTest {

	@Test
	void constructorErrorTest() {
		Recipe testRecipe = new Recipe();
		assertEquals("error", testRecipe.id);
		assertEquals("error", testRecipe.title);
		assertEquals("error", testRecipe.readyInMinutes);
		assertEquals("error", testRecipe.servings);
		assertEquals("error", testRecipe.healthScore);
		assertEquals("error", testRecipe.carbs);
		assertEquals("error", testRecipe.calories);
		assertEquals("error", testRecipe.fat);
		assertEquals("error", testRecipe.protein);
		assertEquals("error", testRecipe.instructions);
	}

}
