package org.pwpw.game.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MainController {
 
 @Autowired

 
 @PostMapping("/addPlayer")
 public String addUser(@RequestParam(value = "table", required = true) String table, @RequestParam(value = "name", required = false, defaultValue = "user#") String name, Model model, HttpSession session) {
  String mySessionId = session.getId();
  
  return "redirect:/";
 }
 
 @GetMapping("/")
 public String start(Model model) {
  
  return "game";
 }
 
 @GetMapping("/cancel")
 public String start() {
  return "redirect: https://www.google.pl/";
 }

}
