package org.pwpw.game.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.pwpw.game.model.Game;
import org.pwpw.game.model.Player;
import org.pwpw.game.model.PlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MainController {
 
 @Autowired
 PlayersRepository playersRepository;
 
 @PostMapping("/addPlayer")
 public String addUser(@RequestParam(value = "table", required = true) String table, @RequestParam(value = "name", required = false, defaultValue = "user#") String name, Model model, HttpSession session) {
  if(table.equals("new")) {
   playersRepository.addPlayer(session.getId(), new Player(name, new Game()));
   System.out.println("Dodanie nowego gracza: " + name);
   System.out.println("Utworzenie nowego stolika z grą");
  } else {
   System.out.println("Wyszukanie stolika istniejacego gracza... ");
   Player existingPlayer = playersRepository.getPlayer(table);
   System.out.println("Dodanie nowego gracza " + name + " do stolika gracza " + existingPlayer.getName());
   Player newPlayer = new Player(name, existingPlayer.getGame());
   newPlayer.getGame().addPlayer(session.getId(), newPlayer);
   //existingPlayer.getGame().addPlayer(session.getId(), newPlayer);
   System.out.println("Gracz: " + existingPlayer.getGame().getPlayer(session.getId()));
   System.out.println("Dodanie gracza: " + name + " do stolika gracza: " + existingPlayer.getName());
  }
  
  return "redirect:/";
 }
 
 @GetMapping("/")
 public String start(HttpSession session, Model model) {
  String sessionID = session.getId();
  Player player;
  if((player = playersRepository.getPlayer(sessionID)) == null) {
   model.addAttribute("user", null);
   //model.addAttribute(waitingGames, arg1)
  } else {
   //model.addAttribute("user", player);
  }
   
  return "game";
 }
 
 @GetMapping("/cancel")
 public String start() {
  return "redirect: https://www.google.pl/";
 }
 
 

}
