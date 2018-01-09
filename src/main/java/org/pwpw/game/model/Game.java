package org.pwpw.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Game {
 //private HashMap<String, Player> players;
 private List<Player> players;
 private Stack<Card> gameStack;
 private GameState gameState;
 
 public Game() {
  super();
  players = new ArrayList<Player>();
  gameState = GameState.WAITING;
  gameStack = new Stack<Card>();
 }
 
 public Game(Player somePlayer) {
  super();
  players = new ArrayList<Player>();
  players.add(somePlayer);
  gameState = GameState.WAITING;
  gameStack = new Stack<Card>();
 }
 
 public List<Player> getPlayers() {
  return players;
 }

 public void setPlayers(List<Player> players) {
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

 public Player getPlayer(int index) {  if(index < players.size()) {
   return players.get(index);
  } else {
   return null;
  }
 }

 public void addPlayer(Player player) {  players.add(player);
 }
 
}
