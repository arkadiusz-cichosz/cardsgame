package org.pwpw.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
  player.getGame().addPlayer(sessionId, player);
  players.put(sessionId, player);
 }
 
 @Override
 public void addPlayer(String sessionId, String name, Boolean isInitiator) {
  Player player = new Player(sessionId, name, isInitiator);
  this.addPlayer(sessionId, player);
 }

 @Override
 public Player getPlayer(String sessionId) {
  if(players.size() > 0 & players.containsKey(sessionId)) {
   return players.get(sessionId);
  } else {
   return null;
  }
 }

 public static void loopGame(HashMap<String, Player> players, Player currentPlayer) {
  currentPlayer.setPlayNow(false);
  List<Player> playersList = new ArrayList<>(players.values());
  int indexOfLastItem = (playersList.size() - 1);
  int currentIndex = playersList.indexOf(currentPlayer);
  if (currentIndex == indexOfLastItem) {
   playersList.get(0).setPlayNow(true);
  } else {
   playersList.get(++currentIndex).setPlayNow(true);
  }
 }
 
 public static HashMap<String, String> getWaitingGames(HashMap<String, Player> players) {
  HashMap<String, String> waitingGameList = new HashMap<>();
  if(players != null && !players.isEmpty()) {
   for (String sessionID : players.keySet()) { 
    Player p = players.get(sessionID);
    if(p.getGame().getGameState().equals(GameState.WAITING)) {
     if(p.getGame().getPlayers().size() < 4) {
      if(p.isInitiator()) {
       waitingGameList.put(sessionID, p.getName());
      }
     }
    }
   }
   return waitingGameList;
  } else {
   return null;
  }
 }

 @Override
 public boolean removePlayer(String sessionId) {
  if(players.size() > 0 && players.containsKey(sessionId)) {
   Player player = players.get(sessionId);
   player.getGame().getPlayers().remove(sessionId);
   players.remove(sessionId);
   return true;
  } else {
   return false;
  }
  
 }
}
