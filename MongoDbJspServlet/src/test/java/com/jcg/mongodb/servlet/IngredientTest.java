package com.jcg.mongodb.servlet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IngredientTest {

	@Test
	void constructorErrorTest() {
		Ingredient testIngredient = new Ingredient();
		assertEquals("error", testIngredient.item);
		assertEquals("error", testIngredient.price);
		assertEquals("error", testIngredient.unit);
		assertEquals("error", testIngredient.image);
	}
}
