package com.squareGames.squareGamesAPI.services;

import fr.le_campus_numerique.square_games.engine.GameFactory;

import java.util.Collection;

public interface GameCatalog {
    Collection<String> getGameIdentifiers();

    GameFactory getFactoryById(String id);

}
