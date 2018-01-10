package org.pwpw.game.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.pwpw.game.model.Player;
import org.pwpw.game.model.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class RestPullController {

 @Autowired
 Players players;

 @GetMapping("/status/game")
 public String gameStatus(HttpSession session) {
  /*
   * ObjectMapper mapper = new ObjectMapper(); System.out.println("Sesja rest: "
   * + session.getId()); Player currentPlayer; try { String jsonString = "";
   * if((currentPlayer = players.getPlayer(session.getId())) != null) {
   * System.out.println("Gracz rest: " + currentPlayer.getName()); jsonString =
   * mapper.writeValueAsString(currentPlayer.getGame()); } else { Game emptyGame
   * = new Game(); jsonString = mapper.writeValueAsString(emptyGame); }
   * System.out.println("jsonString = " + jsonString); return jsonString; }
   * catch (JsonProcessingException e) { e.printStackTrace(); return null; }
   */
  return null;
 }

 @GetMapping("/status/options")
 public String optionsList(HttpSession session) throws JSONException, JsonProcessingException {
  ObjectMapper mapper = new ObjectMapper();
  HashMap waitingPlayers = Players.getWaitingGames(players.getPlayers());
  JSONObject jsonObject = null;
  if (waitingPlayers != null) {
   jsonObject = new JSONObject(waitingPlayers);
  } else {
   jsonObject = new JSONObject().put(session.getId(), "Test");
  }
  return jsonObject.toString();
 }
}
