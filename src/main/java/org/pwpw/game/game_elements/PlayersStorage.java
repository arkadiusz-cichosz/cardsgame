package org.pwpw.game.game_elements;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PlayersStorage implements PlayerRepository {

 private List<Player> players = new ArrayList<>();

 public List<Player> getPlayers() {
  return players;
 }

 public void setPlayers(List<Player> players) {
  this.players = players;
 }

 @Override
 public Player getPlayer(String name) {
  for (Player player : players) {
   if (player.getName().compareTo(name) == 0) {
    return player;
   }
  }
  return null;
 }

 @Override
 public void createPlayer(String sessionId, String name) {
  Player player = new Player(name, sessionId);
  players.add(player);
 }

}
