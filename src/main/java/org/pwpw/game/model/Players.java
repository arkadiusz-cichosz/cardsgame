package org.pwpw.game.model;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class Players implements PlayersRepository {

 HashMap<String, Player> players;
 
 public Players() {
  super();
  players = new HashMap<>();
 }

 public Players(HashMap<String, Player> players) {
  super();
  this.players = players;
 }

 public HashMap<String, Player> getPlayers() {
  if (players.size() > 0) {
   return players;
  } else {
   return null;
  }
 }

 public void setPlayers(HashMap<String, Player> players) {
  this.players = players;
 }

 @Override
 public void addPlayer(String sessionId, Player player) {
  player.getGame().addPlayer(player);
  players.put(sessionId, player);
 }

 @Override
 public Player getPlayer(String sessionId) {
  if(players.size() > 0 && players.containsKey(sessionId)) {
   return players.get(sessionId);
  } else {
   return null;
  }
 }

 public static HashMap<String, Player> getWaitingGames(HashMap<String, Player> players) {
  HashMap<String, Player> freeGameList = new HashMap<>();
  if(players != null && !players.isEmpty()) {
   for (String sessionID : players.keySet()) { 
    Player p = players.get(sessionID);
    if(p.getGame().getGameState().equals(GameState.WAITING)) {
     if(p.getGame().getPlayers().size() < 4) {
      freeGameList.put(sessionID, p);
     }
    }
   }
   return freeGameList;
  } else {
   return null;
  }
 }

}
