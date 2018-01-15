package org.pwpw.game.model;

import java.util.List;

public class PlayerDeck extends Deck {

 public PlayerDeck() {
  super();
 }

 public PlayerDeck(List<Card> cards) {
  super(cards);
 }

 public List<Card> getCards() {
  return super.cards;
 }
}
