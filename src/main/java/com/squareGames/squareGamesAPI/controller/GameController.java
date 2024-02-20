package com.squareGames.squareGamesAPI.controller;
import com.squareGames.squareGamesAPI.DTO.TokenDTO;
import com.squareGames.squareGamesAPI.services.GameCreationParams;
import com.squareGames.squareGamesAPI.DTO.GameDto;
import com.squareGames.squareGamesAPI.services.GameService;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;


@RestController
public class GameController {


    @Autowired
    private GameService gameService;

    private GameDto gameToDTO(Game game){
        return new GameDto(game.getId(),game.getFactoryId());
    }
    private TokenDTO gameToDTO(Token token){
        return new TokenDTO(token.getName(),token.getPosition());
    }

    @GetMapping("/games")
    public Collection<String> getGames() {
        return gameService.getGames();
    }
    @PostMapping("/games")
    public GameDto createGame(@RequestBody GameCreationParams params){

        Game game = gameService.addGame(params);
        GameDto gameDto = new GameDto(game.getId(), game.getFactoryId());
        System.out.println(game.getId());
        return gameDto;
    }

    @GetMapping("/savedGames")
    public Collection<GameDto> getSavedGames(){
        ArrayList<GameDto> savedGameDto = new ArrayList<GameDto>();
        for(Game game: gameService.getSavedGames()){
            savedGameDto.add(gameToDTO(game));
        }
        return savedGameDto;

    };
    @GetMapping("/games/{gameId}")
    public Object getGame(@PathVariable UUID gameId){
        return gameToDTO(gameService.getGame(gameId));
    }

    @PutMapping("/movetoken/{gameId}/{tokenName}")
    public TokenDTO moveToken(@PathVariable UUID gameId , @PathVariable String tokenName, @RequestBody CellPosition position){
        Token token = gameService.getToken(gameId, tokenName);
        if(token != null){
            gameService.moveToken(token, position);
        }else{
            System.out.println("ton token est null !");
        }

        return new TokenDTO(token.getName(), position);
    }



}
