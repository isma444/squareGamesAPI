package com.squareGames.squareGamesAPI.plugin;

import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import org.springframework.stereotype.Component;

@Component
public class taquinPlugin extends GamePluginImpl{

    public taquinPlugin() {
        factory = new TaquinGameFactory();
    }
}
