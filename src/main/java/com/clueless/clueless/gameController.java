package com.clueless.clueless;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class gameController {
    // server subclass to interface between client subsystem and the rest of the
    // backend

    @RequestMapping(value = "/index")
    public String home(ModelMap model) {

        return "index";
    }

    @RequestMapping(value = "/show")
    public ModelAndView show() {

        var mav = new ModelAndView();

        var now = LocalDateTime.now();
        var formatter = DateTimeFormatter.ISO_DATE_TIME;
        var dateTimeFormatted = formatter.format(now);

        mav.addObject("now", dateTimeFormatted);
        mav.setViewName("show");

        return mav;
    }

    @RequestMapping("/newgame")
    public String newGame() {
        return "Start a new game.";
    }

    @RequestMapping("/myid") //test
    public String myId(HttpSession session){
        return "My id is" + session.getId();
    }

}
