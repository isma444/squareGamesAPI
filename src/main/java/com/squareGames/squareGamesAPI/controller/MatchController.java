package com.squareGames.squareGamesAPI.controller;

import com.squareGames.squareGamesAPI.services.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/login/")
public class MatchController {

    private MyService myService;


    public MatchController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/matches")
   public String getMatches(){
        return myService.callEndPoint();
   }
}
