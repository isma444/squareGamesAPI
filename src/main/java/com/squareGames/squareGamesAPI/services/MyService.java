package com.squareGames.squareGamesAPI.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyService {
    private final RestTemplate restTemplate;
    private final String BaseUrl = "http://172.22.114.56:9191";

    public MyService(){
        this.restTemplate = new RestTemplate();
    }

    public String callEndPoint(){
        ResponseEntity<String> result = restTemplate.getForEntity(BaseUrl + "/Matches", String.class);
        return result.getBody();
    }
}
