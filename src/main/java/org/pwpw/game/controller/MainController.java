package org.pwpw.game.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.pwpw.game.model.Game;
import org.pwpw.game.model.GameState;
import org.pwpw.game.model.Player;
import org.pwpw.game.model.Players;
import org.pwpw.game.model.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MainController {
 
 @Autowired
 Players players;
 
 @GetMapping("/")
 public String start(HttpSession session, Model model) {
  String sessionID = session.getId();

  if ((players.getPlayer(sessionID)) == null) {

   System.out.println("...nie odnaleziono gracza " + sessionID);
   
   return "login";

  } else {
   if(players.getPlayer(sessionID).getGame().getGameState().equals(GameState.WAITING)) {
    if (players.getPlayers() != null) {
     model.addAttribute("user", players.getPlayers().get(sessionID).getName());
     HashMap<String, String> freePlayers = Players.getWaitingGames(players.getPlayers());
     if(freePlayers.containsKey(sessionID)) {
      model.addAttribute("isYourGame", true);
      model.addAttribute("games", players.getPlayer(sessionID).getGame().getPlayers());
     } else {
      model.addAttribute("isYourGame", false);
     }
    }
    return "init";
   } else {
    return "game";
   }
  }
 }
 
 @PostMapping("/addPlayer")
 public String addUser(@RequestParam(value = "table", required = true) String table, @RequestParam(value = "name", required = false, defaultValue = "user#") String name, Model model, HttpSession session) {
  String sessionID = session.getId();
  Player newPlayer;
  if(table.equals("new")) {
   players.addPlayer(session.getId(), newPlayer = new Player(sessionID, name, true));
   System.out.println("Dodanie nowego gracza: " + name);
   System.out.println("Utworzenie nowego stolika z grą");
  } else {
   players.addPlayer(session.getId(), newPlayer = new Player(sessionID, name, false));
   System.out.println("Wyszukanie stolika istniejacego gracza... ");
   Player existingPlayer = players.getPlayer(table);
   System.out.println("Dodanie nowego gracza " + name + " do stolika gracza " + existingPlayer.getName());
   newPlayer.setGame(existingPlayer.getGame());
   System.out.println("Stolik gracza: " + existingPlayer.getGame().getPlayer(0));
   existingPlayer.getGame().addPlayer(sessionID, name);
   System.out.println("Dodano: " + name + " do stolika gracza");
  } 
  return "redirect:/";
 }
 
 @GetMapping("/cancel")
 public String start(HttpSession session) {
  String sessionID = session.getId();
  if(players.removePlayer(sessionID)) {
   System.out.println("Usunięto gracza z listy");
   session.invalidate();
   return "redirect: https://www.google.pl/";
  } else {
   System.out.println("Coś poszło nie tak :-)");
   return null;
  } 
 }

}
