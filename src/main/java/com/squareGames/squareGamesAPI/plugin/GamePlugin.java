package com.squareGames.squareGamesAPI.plugin;
import fr.le_campus_numerique.square_games.engine.GameFactory;

import java.util.Collection;



public interface GamePlugin {


   String getName();
   GameFactory getFactory();
}
