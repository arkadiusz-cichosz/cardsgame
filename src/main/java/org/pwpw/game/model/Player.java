package org.pwpw.game.model;

public class Player {

 private String name;
 private Deck deck;
 private boolean playNow;
 private String sessionId;
 private Game game;

 public Player(String name, String sessionId, Game game) {
  super();
  this.name = name;
  this.sessionId = sessionId;
  this.game = game;
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

 public String getSessionId() {
  return sessionId;
 }

 public void setSessionId(String sessionId) {
  this.sessionId = sessionId;
 }

 public Game getGame() {
  return game;
 }

 public void setGame(Game game) {
  this.game = game;
 }

}
