package com.squareGames.squareGamesAPI;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class GameController {

    @Autowired
    private GameCatalog gameCatalog;
    private Map<String ,Game> gamesMap = new HashMap<>();

    @GetMapping("/games")
    public Collection<String> getGames() {
        return gameCatalog.getGameIdentifiers();
    }
    @PostMapping("/games")
    public GameDto createGame(@RequestBody GameCreationParams params){

        Game game = gameCatalog.getFactoryById(params.gameType()).createGame(params.playerCount(), params.boardSize());
        String gameId = UUID.randomUUID().toString();
        GameDto gameDto = new GameDto(game, gameId);
        gamesMap.put(gameId, game);
        System.out.println(gameId);
        return gameDto;
    };
    @GetMapping("/games/{gameId}")
    public Object getGame(@PathVariable String gameId){
        return gamesMap.get(gameId);
    }


}
