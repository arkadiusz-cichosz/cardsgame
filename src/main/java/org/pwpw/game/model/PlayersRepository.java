package org.pwpw.game.model;

import java.util.HashMap;

public interface PlayersRepository {
 
 public void addPlayer(String sessionId, Player player);

 public Player getPlayer(String sessionId);
 
}
