package org.pwpw.game.controller;

import java.util.ArrayList;
import java.util.List;

import org.pwpw.game.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RestPullController {
Game game = new Game();

 @GetMapping("/status")
 public String gameStatus() {
  ObjectMapper mapper = new ObjectMapper();

  try {
   String jsonString = mapper.writeValueAsString(game);
   System.out.println("jsonString = " + jsonString);
   return jsonString;
  } catch (JsonProcessingException e) {
   e.printStackTrace();
   return null;
  }
 }
 
}
