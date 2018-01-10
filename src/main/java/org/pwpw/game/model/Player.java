package org.pwpw.game.model;

public class Player {

 private String name;
 private Deck deck;
 private boolean playNow;
 private Game game;
 private boolean initiator;

 public Player(String session, String name, Boolean initiator) {
  super();
  this.name = name;
  this.initiator = initiator;
  game = new Game();
  game.addPlayer(session, this.name);
 }
 
 public Player() {
  super();
 }

 public boolean isInitiator() {
  return initiator;
 }

 public void setInitiator(boolean initiator) {
  this.initiator = initiator;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public Deck getDeck() {
  return deck;
 }

 public void setDeck(Deck deck) {
  this.deck = deck;
 }

 public boolean isPlayNow() {
  return playNow;
 }

 public void setPlayNow(boolean playNow) {
  this.playNow = playNow;
 }

 public Game getGame() {
  return game;
 }

 public void setGame(Game game) {
  this.game = game;
 }
}
