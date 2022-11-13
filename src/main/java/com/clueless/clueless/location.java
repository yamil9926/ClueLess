package com.clueless.clueless;

//base location class
public class location {
    // declare variables
    String name, codename;
    boolean location_occupied = false;

    // constructors for location, location_occupied,
    public location(String name, String codename) {
        this.name = name;
        this.codename = codename;
    }

    public String getName(){
        return name;
    }

    public String getCodename(){
        return codename;
    }

    public void setOccupied(boolean location_occupied) {
        this.location_occupied = location_occupied;
    }

    public boolean isOccupied() {
        return location_occupied;
    }

    public Boolean setOccupant(player player){
        return false;
    }

    public Boolean removeOccupant(player player){
        return false;
    }
  
}
