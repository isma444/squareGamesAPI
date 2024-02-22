package com.squareGames.squareGamesAPI.plugin;
import com.squareGames.squareGamesAPI.plugin.GamePluginImpl;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Component;


@Component
public class TicTacToePlugin extends GamePluginImpl {

    public TicTacToePlugin() {
        factory = new TicTacToeGameFactory();
    }
}
