package org.pwpw.game.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import org.springframework.ui.Model;

public class Game implements Serializable{

 private static final long serialVersionUID = 1L;
 private HashMap<String, Player> players;
 //private HashMap<String, String> players;
 private Stack<Card> gameStack;
 private GameState gameState;
 
 public Game() {
  super();
  players = new HashMap<>();
  gameState = GameState.WAITING;
  gameStack = new Stack<Card>();
 }
 
 public Game(String session, Player player) {
  super();
  players = new HashMap<>();
  players.put(session, player);
  gameState = GameState.WAITING;
  gameStack = new Stack<Card>();
 }

 public HashMap<String,Player> getPlayers() {
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

 public void addPlayer(String sessionId, Player player) {  players.put(sessionId, player);
 }

 public Player getPlayer(String sessionId) {
  if(players.size() > 0 && players.containsKey(sessionId)) {
   return players.get(sessionId);
  } else {
   return null;
  }
 }
 
 public void init(Deck gamedeck) {
  List<Card> tempCardsList = new ArrayList<>(gamedeck.getCards());
  while(tempCardsList.size() > 0) {
   for(String key : players.keySet()) {
    Card card = tempCardsList.remove(0);
    Player player = players.get(key);
    if(card.getName().equals("9Kier")) {
     player.setPlayNow(true);
    }
    player.getDeck().addCard(card);
   }
  }
 }

}
