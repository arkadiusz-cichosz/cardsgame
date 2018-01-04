package org.pwpw.game.game_elements;

public interface PlayerRepository {
 Player getPlayer(String name);
 void createPlayer(String sessionId, String name);
}
