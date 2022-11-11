package com.clueless.clueless;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class gameController {
    // server subclass to interface between client subsystem and the rest of the
    // backend
    private String errorMessage = ""; 
    private ArrayList<game> gameList = new ArrayList<game>();
    private game main = new game("","");
    private ArrayList<String> chat = new ArrayList<String>();

    @RequestMapping(value = "/index") //for testing
    public ModelAndView home(ModelMap model) {
        return new ModelAndView("index");
    }

    @RequestMapping("/game")  
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

    @RequestMapping("/start")
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

    @RequestMapping("/endturn")
    public void endTurn(){
        main.getGameBoard().endTurn();
        // Moves game to next turn
    }

    @RequestMapping("/move") //send POST with name/codename of player and location
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

    @RequestMapping("/accuse")
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

    @RequestMapping("/suggest")
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

    @RequestMapping("/join")
    public Map<String,String> joinGame(HttpSession session){
        Map<String,String> reply = new HashMap<String,String>();
        if(main.getGameBoard().addPlayer(session.getId())){
            reply.put("message", "success");
            reply.put("reason", "Player joined");
            return reply;
        }
        reply.put("message", "fail");
        reply.put("reason", "Player unable to join");
        return reply;
    }

    @RequestMapping(value = "/show") //for testing
    public ModelAndView show() {

        var mav = new ModelAndView();

        var now = LocalDateTime.now();
        var formatter = DateTimeFormatter.ISO_DATE_TIME;
        var dateTimeFormatted = formatter.format(now);

        mav.addObject("now", dateTimeFormatted);
        mav.setViewName("show");

        return mav;
    }

    @RequestMapping("/myid") //test
    public String myId(HttpSession session){
        return "My id is" + session.getId();
    }

    @RequestMapping("/")
    public ModelAndView home(){
        return new ModelAndView("home");
    }

    @RequestMapping("/home")
    public ModelAndView goHome(){
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getcreate(HttpSession session){
        return new ModelAndView("create");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam(value = "type", required = false) String type,
                        @RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "password", required = false) String password,
                        HttpSession session){
        if (type != null && !type.isEmpty() && name != null && !name.isEmpty() && password != null) {
			if (type.equals("private") && password.isEmpty()) {
				errorMessage = "You must enter a password for a private game!";
			} else {
				if (getGameByName(name) != null) {
					errorMessage = "This game name already exists!";
				} else {
					game game = new game(name, password);
					
					try {
						session.removeAttribute("s");
						session.removeAttribute("r");
						session.removeAttribute("w");
						session.removeAttribute("notes");

						game.getGameBoard().addPlayer(session.getId());
						game.getGameBoard().getPlayer(session.getId()).makeAdmin();
						session.setAttribute("gameId", game.getId());
						gameList.add(game);
						
                        return new ModelAndView("redirect:/gamescreen"); //for testing
					} catch (Exception e) {
						errorMessage = e.getMessage();
					}
				}
			}
		} else if (type != null && !type.isEmpty() && name != null && name.isEmpty()) {
			errorMessage = "Please enter a name!";
		}

        System.out.println(errorMessage);
		
		ModelAndView mv = new ModelAndView("create");
		mv.addObject("type", type);
		mv.addObject("name", name);
		mv.addObject("password", password);
		mv.addObject("errorMessage", errorMessage);
		errorMessage = "";
		return mv;
    }

    @RequestMapping("/joinGame")
    public ModelAndView getjoin(){
        return new ModelAndView("join");
    }

    @RequestMapping(value = "/joinGame", method = RequestMethod.POST)
    public ModelAndView join(	@RequestParam(value = "type", required = false) String type,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "password", required = false) String password,
                                HttpSession session) {

        if (type != null && !type.isEmpty() && name != null && !name.isEmpty() && password != null) {
            if (type.equals("private") && password.isEmpty()) {
                errorMessage = "You must enter a password for a private game!";
            } else {
                game game = getGameByName(name);
                if (game != null) {
                    if (game.login(name, password)) {
                        try {
                            if (game.getGameBoard().getPlayer(session.getId()) == null) {
                                session.removeAttribute("s");
                                session.removeAttribute("r");
                                session.removeAttribute("w");
                                session.removeAttribute("notes");
                                
                                game.getGameBoard().addPlayer(session.getId());
                                session.setAttribute("gameId", game.getId());
                            }
                            return new ModelAndView("redirect:/gamescreen");
                        } catch (Exception e) {
                            errorMessage = "Error: " + e.getMessage();
                        }
                    } else
                        errorMessage = "Name and/or password is incorrect!";
                } else {
                    errorMessage = "Game does not exist yet!";
                }
            }
        } else if (type != null && !type.isEmpty() && name != null && name.isEmpty()) {
            errorMessage = "Please enter a name!";
        }

        System.out.println(errorMessage);

        ModelAndView mv = new ModelAndView("join");
        mv.addObject("type", type);
        mv.addObject("name", name);
        mv.addObject("password", password);		
        mv.addObject("errorMessage", errorMessage);
        errorMessage = "";
        return mv;
    }

    @RequestMapping("/gamescreen")
    public String gamescreen(HttpSession session){
        return "gamescreen";
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
