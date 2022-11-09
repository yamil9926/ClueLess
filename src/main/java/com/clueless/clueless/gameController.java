package com.clueless.clueless;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class gameController {
    // server subclass to interface between client subsystem and the rest of the
    // backend
    private String errorMessage = ""; 
    private ArrayList<game> gameList = new ArrayList<game>();

    @RequestMapping(value = "/index") //for testing
    public ModelAndView home(ModelMap model) {
        return new ModelAndView("index");
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
    public String home(){
        return "home";
    }

    @RequestMapping("/home")
    public ModelAndView goHome(){
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String getcreate(HttpSession session){
        return "create";
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

    @RequestMapping("/join")
    public String getjoin(){
        return "join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
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
