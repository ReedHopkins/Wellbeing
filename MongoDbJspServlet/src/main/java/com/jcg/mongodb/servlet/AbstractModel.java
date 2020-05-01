package com.jcg.mongodb.servlet;

abstract class AbstractModel{

    String title;

    abstract String gettitle();

    abstract String getimage();

    abstract boolean isMatch(String s);


}