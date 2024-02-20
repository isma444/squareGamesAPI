package com.squareGames.squareGamesAPI.services;

public record GameCreationParams(
     String gameType,
     int playerCount,
     int boardSize
) {


}
