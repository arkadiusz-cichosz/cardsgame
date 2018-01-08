package org.pwpw.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.stereotype.Component;

public class Game {
 private List<Player> players;
 private Stack<Card> gameStack;
 private GameState gameState;
 private int numberOfPlayers;
 
 public Game() {
  super();
  players = new ArrayList<>();
  gameState = GameState.WAITING;
  numberOfPlayers = 0;
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
 public void setNumberOfPlayers() {
  numberOfPlayers = players.size();
 }
 public int getNumberOfPlayers() {
  return numberOfPlayers;
 }
 
}
