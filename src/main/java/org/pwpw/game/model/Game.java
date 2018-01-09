package org.pwpw.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Component;

public class Game  implements PlayersRepository{
 private HashMap<String, Player> players;
 private Stack<Card> gameStack;
 private GameState gameState;
 
 public Game() {
  super();
  players = new HashMap<>();
  gameState = GameState.WAITING;
  gameStack = new Stack<Card>();
 }
 
 public HashMap<String, Player> getPlayers() {
  return players;
 }

 public void setPlayers(HashMap<String, Player> players) {
  this.players = players;
 }

 public Stack<Card> getGameStack() {
  return gameStack;
 }

 public void setGameStack(Stack<Card> gameStack) {
  this.gameStack = gameStack;
 }

 public GameState getGameState() {
  return gameState;
 }

 public void setGameState(GameState gameState) {
  this.gameState = gameState;
 }

  @Override
 public Player getPlayer(String sessionId) {  if(players.size() > 0 && players.containsKey(sessionId)) {
   return players.get(sessionId);
  } else {
   return null;
  }
 }

 @Override
 public void addPlayer(String sessionId, Player player) {  players.put(sessionId, player);
 }
 
}
