package org.pwpw.game.model;

public enum Rank {

 Nine(9), Ten(10), Jack(11), Queen(12), King(13), Ace(14);

 private int value;

 Rank(int value) {
  this.value = value;
 }

 int getValue() {
  return value;
 }
}
