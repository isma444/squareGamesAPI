package com.squareGames.squareGamesAPI.services;

import com.squareGames.squareGamesAPI.HeartbeatSensor;
import org.springframework.stereotype.Service;

@Service
public class RandomHeartbeat implements HeartbeatSensor {

    @Override
    public int get() {
        return (int) ((Math.random()*(230 - 40))+40);
    }
}
