package com.clueless.clueless;

import java.util.ArrayList; 

public class player {
    String name;
    ArrayList<card> cards = new ArrayList<card>();
    location location;

    public player(String name) { 
        this.name = name;
    }

    public void addCard(card card){
        cards.add(card);
    }

    public ArrayList<card> getCards(){
        return cards;
    }

    public location getLocation(){
        return location;
    }

    public void setLocation(location location){
        this.location = location;
    }

}
