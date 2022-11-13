package com.clueless.clueless;

import java.util.ArrayList; 

public class player {
    String name;
    String codename;
    ArrayList<card> cards = new ArrayList<card>();
    location location;
    Boolean disabled = false;
    Boolean moved = false;
    boolean hasTurn = false;
    boolean canSuggest;
    private boolean Admin = false;
    String id = " ";

    card[] suggestion = new card[3];
	card[] accusation = new card[3];

    public player(String name, String codeName, location initial) { 
        this.name = name;
        this.codename = codeName;
        location = initial;
    }

    public void move(location newLocation) {
        if(hasTurn || moved) {
            location = newLocation;
            canSuggest = true;
        }
        else
            System.out.println("You cannot move because it is not your turn.");
        moved = false;
    }

    public String getName(){
        return name;
    }

    public String getCodename(){
        return codename;
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

    public Boolean isDisabled(){
        return disabled;
    }

    public void setDisabled(Boolean disable){
        disabled = disable;
    }

    public Boolean moved(){
        return moved;
    }

    public void setMoved(Boolean move){
        moved = move;
    }

    public void endTurn() {
        hasTurn = false;
        moved = false;
    }

    public void disable(){
        disabled = true;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public void makeAdmin(){
        Admin = true;
    }

    public Boolean isAdmin(){
        return Admin;
    }

    public void makeSuggestion(card player, card weapon, card location) {
        if(hasTurn && canSuggest) {
            suggestion[0] = player;
            suggestion[1] = weapon;
            suggestion[2] = location;
            
            canSuggest = false;
        }
        else {
            System.out.println("You cannot make a suggestion because it is not your turn,");
            System.out.println("or you have already made a suggestion in this location.");
        }
    }

    public void makeAccusation(card accusedPlayer, card accusedWeapon, card accusedLocation) {
        if(hasTurn) {
            accusation[0] = accusedPlayer;
            accusation[1] = accusedWeapon;
            accusation[2] = accusedLocation;
        }
        else
            System.out.println("You cannot make an accusation because it is not your turn.");
    }

    public card[] proveOrDisproveSuggestion(card[] attemptedSuggestion) {
        card[] matches = new card[3];
        for (int i = 0; i < attemptedSuggestion.length; i++) {
            for (int n = 0; n < cards.size(); n++) {
                if (attemptedSuggestion[i] == cards.get(n)) {
                    matches[i] = attemptedSuggestion[i];
                }
            }
        }
        if ((matches[0] == null) && (matches[1] == null) && (matches[2] == null))
            System.out.println(name + " cannot disprove the suggestion");
        return matches;
    }

}
