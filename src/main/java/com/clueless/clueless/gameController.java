package com.clueless.clueless;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class gameController {
    // server subclass to interface between client subsystem and the rest of the
    // backend

    @GetMapping("/")
	public String index() {
		return "Welcome to the ClueLess Backend!";
	}

    @GetMapping("/newgame")
    public String newGame() {
        return "Start a new game.";
    }

    @GetMapping("/myid") //test
    public String myId(HttpSession session){
        return "My id is" + session.getId();
    }

}
