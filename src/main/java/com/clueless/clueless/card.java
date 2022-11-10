package com.clueless.clueless;

public class card {
    String name;
    cardType type;

    // constructor
    public card(String name, cardType type) {
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public cardType getType(){
        return type;
    }
}
