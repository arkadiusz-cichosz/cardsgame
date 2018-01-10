package org.pwpw.game.model;

public class Player {

 private String name;
 private Deck deck;
 private boolean playNow;
 private Game game;

 public Player(String session, String name) {
  super();
  this.name = name;
  game = new Game();
  game.addPlayer(session, this.name);
 }
 
 public Player() {
  super();
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
