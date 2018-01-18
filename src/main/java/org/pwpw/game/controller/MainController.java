package org.pwpw.game.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.pwpw.game.model.Game;
import org.pwpw.game.model.GameState;
import org.pwpw.game.model.PanDeck;
import org.pwpw.game.model.Player;
import org.pwpw.game.model.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MainController {
 
 @Autowired
 Players players;
 
 @Autowired
 PanDeck panDeck;
 
 @GetMapping("/")
 public String main(HttpSession session) {
  String sessionID = session.getId();
  Player player = players.getPlayer(sessionID);
  if (player == null) {
   System.out.println("...nie odnaleziono gracza " + sessionID);
   return "login";
  } else {
   if(player.getGame().getGameState().equals(GameState.WAITING)) {
    System.out.println("Inicjalizacja...");
    //session.setMaxInactiveInterval(5 * 60);
    return "init";
   } else if(player.getGame().getGameState().equals(GameState.IN_PROGRESS)) {
    System.out.println("Gra...");
    return "game";
   } else {
    System.out.println("Koniec gry");
    return "game";
   }
  }
 }
 
 @GetMapping("/start")
 public String start(HttpSession session) {
  String sessionID = session.getId();
  Player player = players.getPlayer(sessionID);
  if (player != null) {
   Game game = player.getGame();
   //session.setMaxInactiveInterval(45 * 60);
   if(game.getGameState().equals(GameState.WAITING)) {
    game.setGameState(GameState.IN_PROGRESS);
    game.init(panDeck);
   }
   System.out.println("Gra zainicjowana");
  } 
  return "redirect:/";
 }
 
 @PostMapping("/addPlayer")
 public String addUser(@RequestParam(value = "table", required = true) String table, @RequestParam(value = "name", required = false, defaultValue = "user#") String name, Model model, HttpSession session) {
  String sessionID = session.getId();
  Player newPlayer;
  if(table.equals("new")) {
   players.addPlayer(sessionID, newPlayer = new Player(sessionID, name, true));
   System.out.println("Dodanie nowego gracza: " + name);
   System.out.println("Utworzenie nowego stolika z grą");
  } else {
   players.addPlayer(session.getId(), newPlayer = new Player(sessionID, name, false));
   System.out.println("Wyszukanie stolika istniejacego gracza... ");
   Player existingPlayer = players.getPlayer(table);
   System.out.println("Dodanie nowego gracza " + name + " do stolika gracza " + existingPlayer.getName());
   newPlayer.setGame(existingPlayer.getGame());
   System.out.println("Stolik gracza: " + existingPlayer.getName());
   existingPlayer.getGame().addPlayer(sessionID, newPlayer);
   System.out.println("Dodano: " + newPlayer.getName() + " do stolika gracza");
  } 
  return "redirect:/";
 }
 
 @GetMapping("/cancel")
 public String cancel(HttpSession session) {
  String sessionID = session.getId();
  Player player = players.getPlayer(sessionID);
  Game game = player.getGame();
  if(game.getGameState().equals(GameState.WAITING)) {
   HashMap<String, Player> gameplayers = game.getPlayers();
   gameplayers.remove(sessionID);
   
   if(player.isInitiator() && !gameplayers.isEmpty()) {
     Entry<String, Player> entry = gameplayers.entrySet().iterator().next();
     String key = entry.getKey();
     gameplayers.get(key).setInitiator(true);
   } 
   
   if(players.removePlayer(sessionID)) {
    System.out.println("Usunięto gracza z listy");
    return "redirect: https://www.google.pl/";
   } else {
    System.out.println("Coś poszło nie tak :-), nie można wylogować gracza!");
    return "redirect:/";
   }
   
  } else if (game.getGameState().equals(GameState.IN_PROGRESS)) {
   game.setGameState(GameState.WAITING);
   return "redirect:/";
  } else {
   return "redirect:/";
  }
 }
}
