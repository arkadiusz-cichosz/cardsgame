package org.pwpw.game.model;

import java.util.List;

public class PlayerDeck extends Deck {

 public PlayerDeck() {
  super();
 }

 public PlayerDeck(List<Card> cards) {
  this.cards = cards;
 }

 public List<Card> getCards() {
  return cards;
 }
 
 public void removeCard(String name) {
  super.removeCard(name);
 }
}
