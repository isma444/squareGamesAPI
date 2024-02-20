package com.squareGames.squareGamesAPI.DTO;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Token;

public record TokenDTO(
        String name,
        CellPosition position

) {

}
