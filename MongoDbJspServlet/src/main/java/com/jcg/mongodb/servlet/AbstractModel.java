package com.jcg.mongodb.servlet;

import java.util.Comparator;

abstract class AbstractModel{

    String title;
    String image;

    String gettitle(){
        return title;
    };

    String getimage(){
        return image;
    };

    abstract boolean isMatch(String s);


}

class SortByName implements Comparator<AbstractModel> {
    // Used for sorting in ascending order of name
    public int compare(AbstractModel a, AbstractModel b) {
        return a.title.toLowerCase().compareTo(b.title.toLowerCase());
    }
}