package com.jcg.mongodb.servlet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NutrientTest {

	@Test
	void constructorErrorTest() {
		Nutrient testNutrient = new Nutrient();
		assertEquals("error", testNutrient.title);
		assertEquals("error", testNutrient.description);
		assertEquals("error", testNutrient.dailyIntake);
		assertEquals("error", testNutrient.pictureURL);
	}

}
