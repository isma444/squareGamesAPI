package com.squareGames.squareGamesAPI;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class GameCatalogDummyImpl implements GameCatalog{


    TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory() ;
    @Override
    public Collection<String> getGameIdentifiers() {
        return Collections.singleton(ticTacToeGameFactory.getGameId());

    }

}
