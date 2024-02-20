package com.squareGames.squareGamesAPI;

public record GameCreationParams(
     String gameType,
     int playerCount,
     int boardSize
) {


}
