package com.jcg.mongodb.servlet;

abstract class AbstractModel{

    abstract String gettitle();

    abstract boolean isMatch(String s);

    abstract class SortRecipesByName implements Comparable{};



}