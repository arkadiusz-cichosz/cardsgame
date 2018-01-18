/**
 * Ekran oczekiwania na uruchomienie gry
 */

var preData = null;
var curData = null;
var checkInit = function(newOptions) {
	var $dialogBlock = $("#dialogBlock").addClass('myPlayers');
	$dialogBlock.children().remove();
	var isYourGame = "false";
	var gameState = "WAITING";
	var $header = $('<p></p>').text("...");
	var myName = "";
	var $gamers = $('<ul></ul>');
	var numberPlayers = 0;
	var $intro = $('<p></p>');
	var $startMasterButton = $('<a></a>').attr("href", "start").text("START");
	var $startButton = $('<a></a>').attr("href", "").text("START");
	var $clepsydra = $('<img>').attr("alt", "klepsydra").attr("src",
			"images/klepsydra.png");

	$.each(newOptions, function(key, val) {
		if (key === "isYourGame") {
			isYourGame = val;
		} else if (key === "name") {
			myName = val;
			if (myName !== "deleted") {
				$header = $('<h1></h1>').text("Witaj " + myName);
				/*
				 * ten blok wymaga dopracowania jeżeli gracz wycofa się z gry
				 * przed jej rozpoczęciem
				 */
			}
		} else if (key === "gamers") {
			$.each(val, function(session, playerName) {
				++numberPlayers;
				var listItem = $('<li></li>').text(playerName);
				if (playerName !== myName) {
					$gamers.append(listItem);
				}
			});
		} else if (key === "gameState") {
			gameState = val;
		} 
		else {
			$header.text("Wystąpił nieoczekiwany błąd...");
		}
	});

	if (isYourGame === "true") {
		$intro.text("Oto lista twoich graczy:");
		$dialogBlock.append($header);
		$dialogBlock.append($intro);
		$dialogBlock.append($gamers);
		if (numberPlayers >= 2) {
			$dialogBlock.append($startMasterButton);
		}

	} else {
		if(gameState === "IN_PROGRESS") {
			$intro.text("Gra gotowa możesz zaczynac kliknij 'start'.");
			$dialogBlock.append($header);
			$dialogBlock.append($intro);
			$dialogBlock.append($startButton);
		} else {
			$intro.text("Oczekujesz na uruchomienie gry przez innego gracza...");
			$dialogBlock.append($header);
			$dialogBlock.append($intro);
			$dialogBlock.append($clepsydra);
		}
		
	}
}

var pullInit = function() {
	$.getJSON("status/init", function(data) {
			curData = JSON.stringify(data);
			if(curData !== "{}") {
				if (preData !== curData) {
					checkInit(data);
					preData = curData;
				}
			} else {
				console.log("Json is empty..");
			}
	});
}

/*var clickButton = function() {
	$.get("status/start", function() {
		console.log("Start game..");
	});
}

$('a').click(clickButton);*/

pullInit();
setInterval(pullInit, 3000);