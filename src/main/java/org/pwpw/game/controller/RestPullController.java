package org.pwpw.game.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.pwpw.game.model.Card;
import org.pwpw.game.model.Deck;
import org.pwpw.game.model.Game;
import org.pwpw.game.model.GameState;
import org.pwpw.game.model.PanDeck;
import org.pwpw.game.model.Player;
import org.pwpw.game.model.PlayerDeck;
import org.pwpw.game.model.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class RestPullController {

	@Autowired
	Players players;

	@Autowired
	PanDeck panDeck;

	@GetMapping("/status/game")
	public String gameStatus(HttpSession session) {
		String sessionID = session.getId();
		if (players.getPlayer(sessionID) != null) {
			return createJsonGameStatus(sessionID, players.getPlayer(sessionID).getGame());
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
				jsonObject.put("gameState", players.getPlayers().get(sessionID).getGame().getGameState());
				HashMap<String, String> freePlayers = Players.getWaitingGames(players.getPlayers());
				if (freePlayers.containsKey(sessionID)) {
					jsonObject.put("isYourGame", "true");
				} else {
					jsonObject.put("isYourGame", "false");
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
		for (String sessionKey : gamePlayers.keySet()) {
			jsonArray.put(gamePlayers.get(sessionKey).getName());
		}
		return jsonArray;
	}

	public String createJsonGameStatus(String sessionID, Game game) {
		ArrayList<Card> stackCards = new ArrayList<>(game.getGameStack());
		HashMap<String, Player> gamePlayers = game.getPlayers();
		JSONObject gameStatus = new JSONObject();

		for (Card card : stackCards) {
			gameStatus.append("stack", card.getName());
		}

		for (String key : gamePlayers.keySet()) {
			JSONObject somePlayer = new JSONObject();
			somePlayer.accumulate("playerName", gamePlayers.get(key).getName());
			List<Card> playerDeck = gamePlayers.get(key).getDeck().getCards();

			for (Card card : playerDeck) {
				somePlayer.append("playerDeck", card.getName());
			}

			somePlayer.accumulate("playNow", gamePlayers.get(key).isPlayNow());
			gameStatus.append("players", somePlayer);
		}

		gameStatus.accumulate("state", players.getPlayer(sessionID).getGame().getGameState());
		gameStatus.accumulate("myName", players.getPlayer(sessionID).getName());

		return gameStatus.toString();
	}

	@GetMapping("/status/addCard/{id}")
	public String addCard(HttpSession httpSession, @PathVariable("id") String id) {
		String sessionID = httpSession.getId();
		Player player = players.getPlayer(sessionID);
		Deck deck = player.getDeck();
		System.out.println("Deck size is: " + deck.getCards().size());
		Game game = player.getGame();
		Stack<Card> stack = game.getGameStack();
		JSONObject jsonObject = new JSONObject();
		if (stack.isEmpty()) {
   if (id.equals("9Kier")) {
    Card c = deck.getCard(id);
    String cardName = c.getName(); 
    stack.push(c);// połóż na stosie swoja kartę
    deck.removeCard(id); // zdejmij kartę ze swojej talii
    jsonObject.accumulate("validation", "true");// dobra karta
    jsonObject.accumulate("statement", "Karta wydana prawidłowo.");
    Players.loopGame(game.getPlayers(), player);
    return jsonObject.toString();
   } else {
				jsonObject.accumulate("validation", "false");// zła karta
				jsonObject.accumulate("statement", "Położyłeś niewłaściwą kartę !");
				return jsonObject.toString();
			}
		} else {
			Card pushedCard = deck.getCard(id);
			if ((pushedCard.getValue().compareTo(stack.peek().getValue())) >= 0) {
				stack.push(deck.getCard(id));// połóż na stosie swoja kartę
				deck.removeCard(id); // zdejmij kartę ze swojej talii
				jsonObject.accumulate("validation", "true");// dobra karta
				jsonObject.accumulate("statement", "Karta wydana prawidłowo.");
				Players.loopGame(game.getPlayers(), player);
				return jsonObject.toString();
			} else {
				jsonObject.accumulate("validation", "false");// zła karta
				jsonObject.accumulate("statement", "Położyłeś niewłaściwą kartę !");
				return jsonObject.toString();
			}
		}
	}
	
 @GetMapping("/status/takeCards")
 public String takeCards(HttpSession httpSession) {
  String sessionID = httpSession.getId();
  JSONObject jsonObject = new JSONObject();
  if (players.getPlayer(sessionID) != null) {
   Player player = players.getPlayer(sessionID);
   Deck deck = player.getDeck();
   Game game = player.getGame();
   Stack<Card> stack = game.getGameStack();
   for (int i = 0; i < 3; i++) {
    deck.addCard(stack.pop());
   }
   Players.loopGame(game.getPlayers(), player);
   jsonObject.accumulate("validation", "true");
   jsonObject.accumulate("statement", "Karty zostały dodane.");
   return jsonObject.toString();
  } else {
   jsonObject.accumulate("validation", "false");
   jsonObject.accumulate("statement", "Wystąpił bład, karty nie zostały dodane do talli.");
   return jsonObject.toString();
  }
 }
}