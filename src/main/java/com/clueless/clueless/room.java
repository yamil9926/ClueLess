package com.clueless.clueless;

import java.util.ArrayList;

public class room extends location {
    ArrayList<player> occupants = new ArrayList<player>(); //rooms dont have player limits
    location[] adjLocations = new location[3]; //rooms can have 3 adjacent hallways or 2 hallways and 1 room(corners)

    public room(String name, String codename) {
        // constructor
        super(name, codename);
    }

    public void setAdjacent(location[] locations){ //sets adjacent locations
        adjLocations = locations;
    }

   public location[] getAdjacent(){
        return adjLocations;
   }

   public Boolean addOccupant(player occupant){ //True if succesfull, false if fail
        return occupants.add(occupant);
   }

   public Boolean removeOccupant(player occupant){ //True if removed, false if occupant not in list
        return occupants.remove(occupant);
   }
}
