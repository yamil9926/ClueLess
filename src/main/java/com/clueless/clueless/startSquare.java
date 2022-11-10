package com.clueless.clueless;

public class startSquare extends location {
    hallway adjacent;
    player occupant;

    public startSquare(String name, String codename) {
        // constructor
        super(name, codename);
    }

    public void setAdjacent(hallway hall){
        adjacent = hall;
    }

    public hallway getAdjacent(String plis){
        return adjacent;
    }

    public Boolean setOccupant(player occupant){ //True if succesfull, false if fail
        if (this.occupant != null){
            this.occupant = occupant;
            return true;
        }
        return false;
   }

   public Boolean removeOccupant(player occupant){ //True if removed, false if hallway empty or player not in hallway
        if(this.occupant != occupant || this.occupant == null){
            return false;
        }
        this.occupant = null;
        return true;
   }


}
