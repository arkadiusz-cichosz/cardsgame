package org.pwpw.game.controller;

import java.util.ArrayList;
import java.util.List;

import org.pwpw.game.game_elements.Game;
import org.pwpw.game.game_elements.Player;
import org.pwpw.game.game_elements.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RestPullController {

 @Autowired
 private PlayerRepository playerRepository;
 
 int i = 0;
 
 @Autowired
 Game game;

 @GetMapping("/status")
 public String gameStatus() {
  ObjectMapper mapper = new ObjectMapper();
  List<Player> players = new ArrayList<>();
  players.add(new Player("Arek", "000AF11BA"));
  game.setPlayers(players);
  try {
   String jsonString = mapper.writeValueAsString(playerRepository);
   System.out.println("jsonString = " + jsonString + " " + i++);
   return jsonString;
  } catch (JsonProcessingException e) {
   e.printStackTrace();
   return null;
  }
 }
 
}
