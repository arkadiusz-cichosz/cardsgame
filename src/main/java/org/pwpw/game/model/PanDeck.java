package org.pwpw.game.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public final class PanDeck extends Deck {
 public PanDeck() {
  for (Color color : Color.values()) {
   for (Rank rank : Rank.values()) {
    cards.add(new Card(rank, color));
   }
  }
  shuffleDeck();
 }

 public void shuffleDeck() {
  Collections.shuffle(cards);
 }
}
