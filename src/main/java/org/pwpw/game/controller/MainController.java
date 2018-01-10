package org.pwpw.game.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.pwpw.game.model.Game;
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
  if((players.getPlayer(sessionID)) == null) {
   System.out.println("...nie odnaleziono gracza " + sessionID);
   model.addAttribute("user", null);
   model.addAttribute("games", players.getPlayers());
  } else {
   model.addAttribute("user", players.getPlayer(sessionID));
   System.out.println("Gracz: " + players.getPlayer(sessionID).getName() + " jest na liście");
  }
  
  if (players.getPlayers() != null) {
   HashMap<String, String> freePlayers;
   System.out.println("*************START*******************");
   if ((freePlayers = Players.getWaitingGames(players.getPlayers())) != null) {
    for (String mysession : freePlayers.keySet()) {
     System.out.println("Gracz: " + freePlayers.get(mysession) + " jest na liście");
    }
   }
   System.out.println("**************END********************");
  }
  
  return "game";
 }
 
 @PostMapping("/addPlayer")
 public String addUser(@RequestParam(value = "table", required = true) String table, @RequestParam(value = "name", required = false, defaultValue = "user#") String name, Model model, HttpSession session) {
  Player newPlayer;
  players.addPlayer(session.getId(), newPlayer = new Player(session.getId(), name));
  if(table.equals("new")) {
   System.out.println("Dodanie nowego gracza: " + name);
   System.out.println("Utworzenie nowego stolika z grą");
  } else {
   System.out.println("Wyszukanie stolika istniejacego gracza... ");
   Player existingPlayer = players.getPlayer(table);
   System.out.println("Dodanie nowego gracza " + name + " do stolika gracza " + existingPlayer.getName());
   newPlayer.setGame(existingPlayer.getGame());
   System.out.println("Stolik gracza: " + existingPlayer.getGame().getPlayer(0));
   existingPlayer.getGame().addPlayer(session.getId(), name);
   System.out.println("Dodano: " + name + " do stolika gracza");
  } 
  return "redirect:/";
 }
 
 @GetMapping("/cancel")
 public String start() {
  return "redirect: https://www.google.pl/";
 }

}
