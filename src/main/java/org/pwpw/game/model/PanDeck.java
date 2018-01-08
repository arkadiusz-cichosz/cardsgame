package org.pwpw.game.model;

import java.util.Random;

public class PanDeck extends Deck {
 public PanDeck() {
  for (Color color : Color.values()) {
   for (Rank rank : Rank.values()) {
    cards.add(new Card(rank, color));
   }
  }
  shuffleDeck();
 }

 public void shuffleDeck() {
  Random random = new Random();
  for (int i = deckSize - 1; i > 0; i--) {
   int index = random.nextInt(i + 1);
   Card c = cards.get(index);
   cards.add(index, cards.get(i));
   cards.add(i, c);
  }
 }
}
