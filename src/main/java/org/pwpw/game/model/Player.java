package org.pwpw.game.model;

public class Player {

 private String name;
 private Deck deck;
 private boolean playNow;
 private Game game;

 public Player(String name, Game game) {
  super();
  this.name = name;
  this.game = game;
 }

 public Player(String name) {
  super();
  this.name = name;
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
