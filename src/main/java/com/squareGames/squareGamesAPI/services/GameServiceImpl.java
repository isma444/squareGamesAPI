package com.squareGames.squareGamesAPI.services;
import com.squareGames.squareGamesAPI.plugin.GamePlugin;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {


    @Autowired
    private List<GamePlugin> gamePlugins;
    @Autowired
    private MessageSource messageSource;


    private Map<UUID ,Game> gamesMap = new HashMap<>();


    @Override
    public List<String> getGames(Locale locale) {

        List<String> GamesName = new ArrayList<String>();
        for (GamePlugin gameplugin :  gamePlugins) {
            GamesName.add(messageSource.getMessage(gameplugin.getName().replace(" ", ""), null, locale));
        }
        return GamesName;
    }

    @Override
    public Game addGame(GameCreationParams params) {

        for(GamePlugin plugin : gamePlugins){
            if(params.gameType().equals(plugin.getName())){
                Game game = plugin.getFactory().createGame(params.playerCount(), params.boardSize());
                gamesMap.put(game.getId(), game);
                return game;
            }
        }
        return null;
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

    public Token moveToken(Token token, CellPosition position){

        do {
            try {
                if (token.canMove()) {
                    token.moveTo(position);
                   return token;
                }
            } catch (Exception e) {
                System.out.println("ça va pas !");

            }
        }while(true);

    }





}
