package com.squareGames.squareGamesAPI.plugin;

import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.stereotype.Component;

@Component
public class Connect4Plugin extends GamePluginImpl{
    public Connect4Plugin() {
        factory = new ConnectFourGameFactory();
    }
}
