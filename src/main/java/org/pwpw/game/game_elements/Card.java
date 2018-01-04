package org.pwpw.game.game_elements;

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

 public String getName() {
  return name;
 }

 public void setName() {
  StringBuilder stringBuilder = new StringBuilder();
  stringBuilder.append(value.getValue());
  stringBuilder.append(" ");
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
  id = Integer.parseInt((String.valueOf(value.getValue()) + String.valueOf(color)));
 }

}
