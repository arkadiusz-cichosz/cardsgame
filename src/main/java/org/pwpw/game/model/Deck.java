package org.pwpw.game.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
 protected List<Card> cards = new ArrayList<>();
 protected int deckSize;

 public Deck() {
  super();
 }

 public Deck(List<Card> cards) {
  super();
  setCards(cards);
 }
 
 public List<Card> getCards() {
  return cards;
 }

 public void setCards(List<Card> cards) {
  this.cards = cards;
 }

 public void setDeckSize() {
  deckSize = cards.size();
 }

}
