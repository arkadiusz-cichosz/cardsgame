package org.pwpw.game.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.pwpw.game.game_elements.Game;
import org.pwpw.game.game_elements.Player;
import org.pwpw.game.game_elements.PlayerRepository;
import org.pwpw.game.game_elements.PlayersStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MainController {
 
 @Autowired
 private PlayerRepository playerRepository;
 
 @PostMapping("/addUser")
 public String addUser(@RequestParam(value = "name", required = false, defaultValue = "user#") String name, Model model, HttpSession session) {
  playerRepository.createPlayer(session.getId(), name);
  model.addAttribute("test", ((PlayersStorage) playerRepository).getPlayers());
  return "redirect:/";
 }

 /*@PostMapping("/start")
 public String start(@RequestParam(value = "name", required = false, defaultValue = "user#") String name, Model model, HttpSession session) {
  playerRepository.createPlayer(session.getId(), name);
  model.addAttribute("test", ((PlayersStorage) playerRepository).getPlayers());
  return "redirect:/";
 }*/
 
 @GetMapping("/")
 public String start(Model model) {
  model.addAttribute("test", ((PlayersStorage) playerRepository).getPlayers());
  return "game";
 }
 
 @GetMapping("/cancel")
 public String start() {
  return "redirect: https://www.google.pl/";
 }

}
