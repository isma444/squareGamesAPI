package com.squareGames.squareGamesAPI.services;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

public interface GameService {


    Map<String ,Game> gamesMap = new HashMap<>();

    List<String> getGames(Locale locale);

    Game addGame(@RequestBody GameCreationParams params);

    Game getGame(UUID gameId);

    Token getToken(UUID gameId, String TokenName);
    Token moveToken(Token token, CellPosition position);

    ArrayList<Game> getSavedGames();

}
