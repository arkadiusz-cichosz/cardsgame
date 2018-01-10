package org.pwpw.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Game {
 //private HashMap<String, Player> players;
 private HashMap<String, String> players;
 private Stack<Card> gameStack;
 private GameState gameState;
 
 public Game() {
  super();
  players = new HashMap<>();
  gameState = GameState.WAITING;
  gameStack = new Stack<Card>();
 }
 
 public Game(String session, String name) {
  super();
  players = new HashMap<>();
  players.put(session, name);
  gameState = GameState.WAITING;
  gameStack = new Stack<Card>();
 }

 public HashMap<String, String> getPlayers() {
  return players;
 }

 public void setPlayers(HashMap<String, String> players) {
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

 /*public Player getfirstPlayer(int index) {  if(index < players.size()) {
   return players.get(index);
  } else {
   return null;
  }
 }*/

 public void addPlayer(String session, String Name) {  players.put(session, Name);
 }

 public String getPlayer(int i) {
  if(i < players.size()) {
   return players.get(players.keySet().toArray()[0]);
  } else {
   return null;
  }
 }
 
}
