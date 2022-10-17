package com.clueless_game.clueless;

import java.util.ArrayList;
import java.util.List;

public class room extends location {

    public room(String name, String codename) {
        // constructor
        super(name, codename);
    }

    // for setting adjacent rooms
    public void getMoveOptions(location op1, location op2, location op3) {
        // list with options to loop through
        List<location> options = new ArrayList<location>();
        options.add(op1);
        options.add(op2);
        options.add(op3);

        // initialize all adj_rooms to null
        for (int i = 0; i < 4; i++) {
            adj_rooms[i] = null;
        }
        // loop through options
        for (int j = 0; j <= options.size(); j++) {
            adj_rooms[j] = options.get(j);
        }
    }
}
