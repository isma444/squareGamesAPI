package com.squareGames.squareGamesAPI.services;
import com.squareGames.squareGamesAPI.services.GameCatalog;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class GameCatalogDummyImpl implements GameCatalog {

    private GameFactory ticTacToeGameFactory = new TicTacToeGameFactory();
    private GameFactory connectFourGameFactory = new ConnectFourGameFactory();
    private GameFactory taquinGameFactory = new TaquinGameFactory();
    GameCatalogDummyImpl(){

    }

    @Override
    public Collection<String> getGameIdentifiers() {
        return List.of(ticTacToeGameFactory.getGameFactoryId(), connectFourGameFactory.getGameFactoryId(), taquinGameFactory.getGameFactoryId() );
    }

    @Override
    public GameFactory getFactoryById(String id) {

        switch (id){
            case "tictactoe" : return ticTacToeGameFactory;
            case "connect4" : return connectFourGameFactory;
            case "15 puzzle" : return taquinGameFactory;
            default: return null ;
        }

    }
}
