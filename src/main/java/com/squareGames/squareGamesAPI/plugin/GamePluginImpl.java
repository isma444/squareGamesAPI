package com.squareGames.squareGamesAPI.plugin;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class  GamePluginImpl implements GamePlugin{

    @Autowired
    private MessageSource messageSource;

    protected GameFactory factory;

    @Value("${game.tictactoe.default-player-count}")
    private int defaultPlayerCount;
    public int getDefaultPlayerCount(){
        return defaultPlayerCount;
    }

    public String getName(){
        return factory.getGameFactoryId();
    }

    public GameFactory getFactory() {
        return factory;
    }
}
