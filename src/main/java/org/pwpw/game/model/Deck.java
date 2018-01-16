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
 
 public void removeCard(String name) {
  for (Card card : cards) {
   int index = 0;
   if (card.getName().equals(name)) {
    index = cards.indexOf(card);
    cards.remove(index);
   }
  }
 }
 
 public Card getCard(String name) {
  Card c = null;
  for (Card card : cards) {
   int index = 0;
   if (card.getName().equals(name)) {
    index = cards.indexOf(card.getName());
    c = cards.get(index);
   } 
  }
  return c;
 }

}
