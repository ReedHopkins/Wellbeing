package com.jcg.mongodb.servlet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaginatorTest {

	@Test
	void constructorTest() {
		Paginator testP = new Paginator("model", "2", 1, 2);
		assertEquals("ModelServlet?model=model", testP.servlet);
		assertEquals("2", testP.spageid);
		assertEquals(2, testP.pageId);
		assertEquals(1, testP.size);
		assertEquals(2, testP.total);
		assertEquals("ModelServlet?model=model&page=1", testP.previous);
		assertEquals("#", testP.next);
	}
	
	@Test
	void setSearchTermTest() {
		Paginator testP = new Paginator();
		String test = "hello";
		testP.setSearchTerm(test);
		assertEquals("hello", testP.searchTerm);
	}
	
	@Test
	void setSortTermTest() {
		Paginator testP = new Paginator();
		String test = "hello";
		testP.setSortTerm(test);
		assertEquals("hello", testP.sortTerm);
	}

	@Test
	void setFilterTermTest() {
		Paginator testP = new Paginator();
		String test = "hello";
		testP.setFilterTerm(test);
		assertEquals("hello", testP.filterTerm);
	}

}
