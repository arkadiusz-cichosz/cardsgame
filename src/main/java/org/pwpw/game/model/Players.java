package org.pwpw.game.model;

import java.util.ArrayList;
import java.util.List;

public class Players {
 private List<Player> players = new ArrayList<>();

 public Players() {
  super();
 }

 public List<Player> getPlayers() {
  return players;
 }

 public void setPlayers(List<Player> players) {
  this.players = players;
 }

}
