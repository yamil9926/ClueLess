package com.clueless_game.clueless;

import java.util.ArrayList;
import java.util.List;

public class billiardRoom extends location {
    public billiardRoom(String name, String codename) {
        // constructor
        super(name, codename);
    }

    public void getMoveOptions(location op1, location op2, location op3, location op4) {
        // list with options to loop through
        List<location> options = new ArrayList<location>();
        options.add(op1);
        options.add(op2);
        options.add(op3);
        options.add(op4);
        // initialize adjacent rooms to null
        for (int i = 0; i < 4; i++) {
            adj_rooms[i] = null;
        }
        // loop through options
        for (int j = 0; j <= options.size(); j++) {
            adj_rooms[j] = options.get(j);
        }
    }
}
