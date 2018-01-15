package org.pwpw.game.controller;

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
 
 @GetMapping("/")
 public String main(HttpSession session) {
  String sessionID = session.getId();

  if ((players.getPlayer(sessionID)) == null) {
   System.out.println("...nie odnaleziono gracza " + sessionID);
   return "login";
  } else {
   if(players.getPlayer(sessionID).getGame().getGameState().equals(GameState.WAITING)) {
    System.out.println("Inicjalizacja...");
    //session.setMaxInactiveInterval(5 * 60);
    return "init";
   } else {
    System.out.println("Gra...");
    return "game";
   }
  }
 }
 
 @GetMapping("/start")
 public String start(HttpSession session) {
  String sessionID = session.getId();
  System.out.println("SessionID is=" + sessionID);
  if (players.getPlayer(sessionID) != null) {
   //session.setMaxInactiveInterval(45 * 60);
   players.getPlayer(sessionID).getGame().setGameState(GameState.IN_PROGRESS);
   final PanDeck panDeck = new PanDeck();
   players.getPlayer(sessionID).getGame().init(panDeck);
   System.out.println("Gra zainicjowana");
  } else {
   System.out.println("Nie ma ciebie na liście graczy");
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
  if(players.removePlayer(sessionID)) {
   System.out.println("Usunięto gracza z listy");
   return "redirect: https://www.google.pl/";
  } else {
   System.out.println("Coś poszło nie tak :-)");
   return null;
  } 
 }

}
