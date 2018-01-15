package org.pwpw.game.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.pwpw.game.model.Card;
import org.pwpw.game.model.Game;
import org.pwpw.game.model.GameState;
import org.pwpw.game.model.PanDeck;
import org.pwpw.game.model.Player;
import org.pwpw.game.model.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@RestController
public class RestPullController {

 @Autowired
 Players players;

 @GetMapping("/status/game")
 public String gameStatus(HttpSession session) {
  String sessionID = session.getId();
  if (players.getPlayer(sessionID) != null) {
   ArrayList<Card> stackCards =  new ArrayList<>(players.getPlayer(sessionID).getGame().getGameStack());
   HashMap<String, Player> gamePlayers = players.getPlayer(sessionID).getGame().getPlayers();
   JSONArray stackArray = new JSONArray();
   JSONArray playersArray = new JSONArray();
   JSONObject gameStatus = new JSONObject();
   
   for(Card card : stackCards) {
    stackArray.put(card.getName());
   }
   
   for(String key: gamePlayers.keySet()) {
    JSONObject somePlayer = new JSONObject();
    somePlayer.append("playerName", gamePlayers.get(key).getName());
    List<Card> playerDeck = gamePlayers.get(key).getDeck().getCards();
    JSONArray cardsArray = new JSONArray();
    for(Card card : playerDeck) {
     cardsArray.put(card.getName());
    }
    somePlayer.append("playerDeck", cardsArray);
    
    
   }
   
   gameStatus.append("state", players.getPlayer(sessionID).getGame().getGameState());
   gameStatus.append("stack", stackArray);
   gameStatus.append("players", value)
   return null;
  } else {
   return null;
  }
 }

 @GetMapping("/status/options")
 public String optionsList() throws JSONException, JsonProcessingException {
  HashMap<String, String> waitingPlayers = Players.getWaitingGames(players.getPlayers());
  JSONObject jsonObject = null;
  if (waitingPlayers != null) {
   jsonObject = new JSONObject(waitingPlayers);
   return jsonObject.toString();
  } else {
   return "test";
  }
 }
 
 @GetMapping("/status/init")
 public String gameInit(HttpSession httpSession) throws JSONException {
  String sessionID = httpSession.getId();
  JSONObject jsonObject = new JSONObject();
  if (players.getPlayers() != null) {
   if (players.getPlayer(sessionID) != null) {
    jsonObject.put("name", players.getPlayers().get(sessionID).getName());
    HashMap<String, String> freePlayers = Players.getWaitingGames(players.getPlayers());
    if (freePlayers.containsKey(sessionID)) {
     jsonObject.put("isYourGame", true);
    } else {
     jsonObject.put("isYourGame", false);
    }
    jsonObject.put("gamers", mapToJSONArray(players.getPlayer(sessionID).getGame().getPlayers()));
   } else {
    jsonObject.put("name", "deleted");
   }
  }
  return jsonObject.toString();
 }
 
 public JSONArray mapToJSONArray(HashMap<String, Player> gamePlayers) {
  JSONArray jsonArray = new JSONArray();
  for(String sessionKey : gamePlayers.keySet()) {
     jsonArray.put(gamePlayers.get(sessionKey).getName());
  }
  return jsonArray;
 }
 
}
