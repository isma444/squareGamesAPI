package com.squareGames.squareGamesAPI.services;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameCatalog gameCatalog;
    private Map<UUID ,Game> gamesMap = new HashMap<>();
    @Override
    public Collection<String> getGames() {
        return gameCatalog.getGameIdentifiers();
    }
    @Override
    public Game addGame(GameCreationParams params) {

        Game game = gameCatalog.getFactoryById(params.gameType()).createGame(params.playerCount(), params.boardSize());
        gamesMap.put(game.getId(), game);
        return game;
    }


    public ArrayList<Game> getSavedGames(){
        ArrayList<Game> savedGame = new ArrayList<Game>();
        gamesMap.entrySet().stream()
                .map(entry ->entry.getValue())
                .forEach(savedGame::add);
        return savedGame;
    }

    public Game getGame(UUID gameId){
        Game game = gamesMap.get(gameId);
        return game;
    }


    @Override
    public Token getToken(UUID gameId, String tokenName) {

         Collection<Token> tokens = getGame(gameId).getRemainingTokens();
         for(Token token : tokens){
             if(token.getName().equals(tokenName)){
                 return token;
             }
         }
         return null;
    }

    public Void moveToken(Token token, CellPosition position){

        do {
            try {
                if (token.canMove()) {
                    token.moveTo(position);
                    break;
                }
            } catch (Exception e) {
                System.out.println("ça va pas !");
                break;
            }
        }while(true);

    }





}
