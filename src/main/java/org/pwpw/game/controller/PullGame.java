package org.pwpw.game.controller;

import javax.json.JsonObject;

import org.pwpw.game.game_elements.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")

public class PullGame {

 private final Game game = new Game();

@GetMapping
public Game gameStatus() {
 return game;
}

}
