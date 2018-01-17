package org.pwpw.game.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {
 protected List<Card> cards = new ArrayList<>();

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
 
 public void removeCard(String name) {
  int index = 0;
  for (Card card : cards) {
   if (card.getName().equals(name)) {
    cards.remove(index);
    break;
   }
   index++;
  }
 }
 
 public Card getCard(String name) {
  Card c = null;
  int index = 0;
  for (Card card : cards) {
   if (card.getName().equals(name)) {
    c = cards.get(index);
    break;
   }
   index++;
  }
  return c;
 }
 
 public void addCard(Card card) {
  cards.add(card);
 }

}
