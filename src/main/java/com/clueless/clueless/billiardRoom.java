package com.clueless.clueless;

import java.util.ArrayList;

public class billiardRoom extends location {
    ArrayList<player> occupants = new ArrayList<player>(); //rooms dont have player limits
    location[] adjLocations = new location[4]; //billiard room only has 4 hallways

    public billiardRoom(String name, String codename) {
        // constructor
        super(name, codename);
    }

    public void setAdjacent(location[] locations){ //sets adjacent locations
        adjLocations = locations;
    }

   public location[] getAdjacent(String plis){
        return adjLocations;
   }

   public Boolean setOccupant(player occupant){ //True if succesfull, false if fail
        return occupants.add(occupant);
   }

   public Boolean removeOccupant(player occupant){ //True if removed, false if occupant not in list
        return occupants.remove(occupant);
   }
}
