package com.clueless.clueless;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class gameController {
    // server subclass to interface between client subsystem and the rest of the
    // backend 
    private ArrayList<game> gameList = new ArrayList<game>();
    private game main = new game("","");
    private ArrayList<String> chat = new ArrayList<String>();

    @RequestMapping(value = "/game", method = RequestMethod.GET)  
    public ArrayList<gameBoard> json(){
        ArrayList<gameBoard> board = new ArrayList<gameBoard>();
        board.add(main.getGameBoard());
        return board;
        //Returns the following info:
        /**
         * 1. Is game board active?
         * 2. All weapon/place/player cards options
         * 3. All players, including current player
         * 4. All players' cards
         * 5. All players' locations
         * 6. Current player move options
         * 7. Is a player disabled? (made wrong accusation)
         * 8. Player ID (empty if not controlled by real person)
         * 9. Active Players (# of real people playing)
         * 
         */
    }

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    public ArrayList<String> chat(@RequestParam("message") String message){
        chat.add(message);
        return chat;
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public ArrayList<String> chat(){
        return chat;
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public Map<String,String> start(){
        Map<String,String> reply = new HashMap<String,String>();
        if(main.getGameBoard().startGame()){
            reply.put("message","success");
            return reply;
        }
        reply.put("message", "fail");
        return reply;
        // Starts the game on turn 1 if there are enough players
    }

    @RequestMapping(value = "/endturn", method = RequestMethod.GET)
    public void endTurn(){
        main.getGameBoard().endTurn();
        // Moves game to next turn
    }

    @RequestMapping(value = "/move", method = RequestMethod.POST) //send POST with name/codename of player and location
    public Map<String, String> move(@RequestParam("user") String user, @RequestParam("location") String location){
        Map<String,String> reply = new HashMap<String,String>();
        if(!main.getGameBoard().isActive()){ // if game is not active
            reply.put("message", "fail");
            reply.put("reason","game not active");
            return reply;
        }
        player p = main.getGameBoard().getPlayerName(user);
        if(p.moved()){ // if player has moved already
            reply.put("message", "fail");
            reply.put("reason","player already moved");
            return reply;
        }
        location l = main.getGameBoard().getLocationName(location);
        location current = p.getLocation();
        location[] temp = main.getGameBoard().getAdjacent(current); // finds adjacent locations, max 4
        for(int i  = 0; i < 4; i++){
            if(temp[i] != null){
                if(temp[i].name == l.name){ // if new location is adjacent to current location
                    if(l.setOccupant(p)){ //if new location has space
                        current.removeOccupant(p);
                        p.setLocation(l);
                        p.setMoved(true);
                        if(main.getGameBoard().isRoom(l)){ //if entered a room, can suggest
                            p.canSuggest = true;
                        }
                        reply.put("message", "success");
                        reply.put("reason","player moved"); 
                        return reply;
                    }
                    reply.put("message", "fail");
                    reply.put("reason","new location is full");
                    return reply;
                }
            }
        }
        reply.put("message","fail");
        reply.put("reason","location not adjacent");
        return reply;
        // Tries to move specified user to specified location
        // Succeeds if new location is adjacent to current location and has space
    }

    @RequestMapping(value = "/accuse", method = RequestMethod.POST)
    public Map<String,String> accuse(@RequestParam("culprit") int culprit, @RequestParam("weapon") int weapon, @RequestParam("location") int location){
        Map<String,String> reply = new HashMap<String,String>();
        if(main.getGameBoard().accuse(culprit, weapon, location)){
            reply.put("message", "game over");
            reply.put("reason", "Accusation was correct");
            return reply;
        }
        reply.put("message", "fail");
        reply.put("reason", "Accusation was not correct");
        return reply;
    }

    @RequestMapping(value = "/suggest", method = RequestMethod.POST)
    public Map<String,String> suggest(@RequestParam("culprit") int culprit, @RequestParam("weapon") int weapon){
        Map<String,String> reply = new HashMap<String,String>();
        player current = main.getGameBoard().getCurrentPlayer();
        if(!current.canSuggest){
            reply.put("message", "fail");
            reply.put("reason", "Suggestion already made");
            return reply; 
        }
        //MISSING STUFF
        String[] result = main.getGameBoard().suggest(culprit, weapon);
        if(result[0] != ""){ //was disproven
            reply.put("message", "fail");
            reply.put("player", result[0]);
            reply.put("card", result[1]);
        }else{// was not disproven
            reply.put("message", "success");
            reply.put("player", "none");
            reply.put("card", "none");
        }
    
        return reply;

    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public Map<String,String> joinGame(@RequestParam("player") String id){
        Map<String,String> reply = new HashMap<String,String>();
        if(main.getGameBoard().addPlayer(id)){
            reply.put("message", "success");
            reply.put("reason", "Player joined");
            return reply;
        }
        reply.put("message", "fail");
        reply.put("reason", "Player unable to join");
        return reply;
    }

    public game getGameByName(String name) {
		if (gameList != null) {
			for (game g : gameList) {
				//System.out.println("Game available: " + g.getName() + " id: " + g.getId());
				if (g.getName() != null && !g.getName().isEmpty() && g.getName().equals(name))
					return g;
			}
		}
		return null;
	}


}
