package org.pwpw.game.model;

import java.util.List;

public class Card {
 private String name;
 private Rank value;
 private Color color;
 private int id;

 public Card(Rank value, Color color) {
  super();
  this.value = value;
  this.color = color;
  setName();
  setId();
 }

 public Card(Card card) {
  super();
  this.value = card.getValue();
  this.color = card.getColor();
  setName();
  setId();
 }

 public String getName() {
  return name;
 }

 public void setName() {
  StringBuilder stringBuilder = new StringBuilder();
  stringBuilder.append(value.getValue());
  stringBuilder.append(color.name());
  name = stringBuilder.toString();
 }

 public Rank getValue() {
  return value;
 }

 public void setValue(Rank value) {
  this.value = value;
 }

 public Color getColor() {
  return color;
 }

 public void setColor(Color color) {
  this.color = color;
 }

 public int getId() {
  return id;
 }

 public void setId() {
  id = Integer.parseInt((String.valueOf(value.getValue()) + color.name().hashCode()));
  //System.out.println("Id="+id);
 }
 
 public static boolean compareTwoCards(Card card1, List<Card> cards) {
  boolean isTrue = false;
  for (Card card : cards) {
   if (card.getName().equals(card1.name)) {
    isTrue = true;
   }
  }
  return isTrue;
 }

}
