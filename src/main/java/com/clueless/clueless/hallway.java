package com.clueless.clueless;

public class hallway extends location{
    player occupants; //hallway only holds one player, kept plural for consistency
    location[] adjLocations = new location[2]; //hallways connect 2 rooms

    public hallway(String name, String codename){
        super(name, codename);
    }

    public void setAdjacent(location[] locations){ //sets adjacent locations
        adjLocations = locations;
    }

   public location[] getAdjacent(String plis){
        return adjLocations;
   }

   public Boolean setOccupant(player occupant){ //True if succesfull, false if fail
        if (occupants != null){
            occupants = occupant;
            return true;
        }
        return false;
   }

   public Boolean removeOccupant(player occupant){ //True if removed, false if hallway empty or player not in hallway
        if(occupants != occupant || occupants == null){
            return false;
        }
        occupants = null;
        return true;
   }
    
}
