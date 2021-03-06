	const REVERS_CARD = "images/rewers.png";
	const CARDS_DECK = 24; 
	const FONT = "14px Arial";
	var MY_NAME = "Arek";
	var selectedCardId = null;
	var choised = false;
	var preData = null;
	var curData = null;

class player {
	constructor(deck, name, playNow) {
		this.deck = deck;
		this.name = name;
		this.playNow = playNow; 
	}
	getDeck() {
		return this.deck;
	}

	getName() {
		return this.name;
	}

	getPlayNow() {
		return this.playNow;
	}
}

/*---------------*/

var readGameStatus = function() {
	$.getJSON("status/game" , function(data) {

		curData = JSON.stringify(data);

		if (preData !== curData) {
			var stack = null;
			var players = null;
			var state = "WAITING";
			$.each(data, function(index, val) {
				if(index === "myName") {
					MY_NAME = val;
					console.log("myName=" + MY_NAME);
				} else if(index === "stack") {
					stack = val;
					console.log("Stack=" + stack);
				} else if(index === "state") {
					state = val;
					console.log("State=" + state);
				} else if(index === "players") {
					players = toObjectPlayerArray(val);
					console.log("Players=" + players);
				}
			});
			if(state === "END") {
				var winner = "";
				for(var i = 0; i < players.length; i++) {
					if(players[i].getDeck() === null) {
						winner = players[i].getName();
						break;
					}
				}
				endGame(winner);
			} else {
				drawGameBoard(players, stack);	
				preData = curData;
			}

		}
	});
}

/*---------------*/

var toObjectPlayerArray = function(JsonPlayersArray) {
	var list =[];
	$.each(JsonPlayersArray, function() {
		var name = "";
		var deck = null;
		var play = 0;
		$.each(this, function(index, val) {
			if(index === "playerName") {
				name = val;
				console.log("playerName=" + name);
			}
			
			else if(index === "playerDeck") {
				deck = val;
			}

			else if (index === "playNow") {
				if(val === false) {
					play = 0;
				} else if (val === true) {
					play = 1;
				} else {
					console.log("nieprawidłowe dane lub brak danych w obszarze playNow !");
				}
			}

		});
		list.push(new player(deck,name,play));
	});
	return list;
}

/*---------------*/

var toArray = function(JsonArray) {
	var array = [];
	$.each(JsonArray, function() {
		$.each(this, function(index, val) {
			 array.push(val);
		});
	});
	return array;
}

/*---------------*/

var drawGameBoard = function(playersArray, stack) {
	
	var cardWidth = 160;
	var cardHeight = 250;
	var topPlayerDeck = null;
	var stackCardImage = null;
	var reversImage = null;
	var X_TOP_POSITION = 300;// 150;
	var X_LEFT_POSITION = 0;
	var Y_LEFT_POSITION = Y_RIGHT_POSITION = 190;
	var X_RIGHT_POSITION = 715;
	context.clearRect(0, 0, canvas.width, canvas.height);
	context.font = FONT;
	switch(playersArray.length) {

		case 2:
			for(var i = 0; i < 2; i++) {
				if (playersArray[i].getName() === MY_NAME) {
					console.log("rysuję moją talię gracza " + playersArray[i].getName());
					if(playersArray[i].getPlayNow() === 1) {
						$('.gamebuttons').show();
					} else {
						$('.gamebuttons').hide();
					}
					drawMyDeck(playersArray[i].getDeck());
					console.log("talia gracza " + playersArray[i].getName() + " gotowa" );
				} 

				else {
					console.log("rysuję talię gracza " + playersArray[i].getName());
					
					if(playersArray[i].getPlayNow() === 1) {
							
						context.fillText("Teraz gra: " + playersArray[i].getName(),420,14);
						console.log("Teraz gra: " + playersArray[i].getName());
					}

					else {
						
						context.fillText(playersArray[i].getName(),420,14);
					}

					X_TOP_POSITION = (920 - (cardWidth + ((((playersArray[i].getDeck()).length)-1)*10)))/2;

					$.each(playersArray[i].getDeck(), function() {
						reversImage = new Image();
						reversImage.onload = function() {
							context.drawImage(reversImage, X_TOP_POSITION += 10, 20, cardWidth, cardHeight);
						}
						reversImage.src = REVERS_CARD;	
					});
				}
			}
			break;

		case 3:
			var x = 0, y = 185;
			var leftIsBussy = false;
			for(var i = 0; i < 3; i++) {
				if (playersArray[i].getName() === MY_NAME) {
					console.log("rysuję moją talię gracza " + playersArray[i].getName());
					
					if(playersArray[i].getPlayNow() === 1) {
						$('.gamebuttons').show();
					} else {
						$('.gamebuttons').hide();
					}
					
					drawMyDeck(playersArray[i].getDeck());
					console.log("talia gracza " + playersArray[i].getName() + " gotowa" );
				} 

				else {
					console.log("rysuję talię gracza " + playersArray[i].getName());
				
						if(playersArray[i].getPlayNow() === 1) {
							
							context.fillText("Teraz gra: " + playersArray[i].getName(),x,y);
							console.log("Teraz gra: " + playersArray[i].getName());
						}

						else {
							context.fillText(playersArray[i].getName(),x,y);
						}
					
					x = x + 760;

					$.each(playersArray[i].getDeck(), function() {
	
						reversImage = new Image();
						
						if(!leftIsBussy) {
							
							reversImage.onload = function() {
								context.drawImage(reversImage, X_LEFT_POSITION , Y_LEFT_POSITION = Y_LEFT_POSITION + 10, cardWidth, cardHeight);
							}
						}
						
						else {
							reversImage.onload = function() {
								context.drawImage(reversImage, X_RIGHT_POSITION, Y_RIGHT_POSITION = Y_RIGHT_POSITION + 10, cardWidth, cardHeight);
							}
						}

						reversImage.src = REVERS_CARD;
						});

					if(!leftIsBussy) {
						leftIsBussy = true;
					}

					console.log("talia gracza " + playersArray[i].getName() + " gotowa" );	
				}
			}
			break;

		case 4:
			var x = 0, y = 185;
			var leftIsBussy = false;
			var rightIsBussy = false;
			for (var i = 0; i < 4; i++) {

				if (playersArray[i].getName() === MY_NAME) {
					console.log("rysuję moją talię gracza " + playersArray[i].getName());
					
					if(playersArray[i].getPlayNow() === 1) {
						$('.gamebuttons').show();
					} else {
						$('.gamebuttons').hide();
					}
					
					drawMyDeck(playersArray[i].getDeck());
					console.log("talia gracza " + playersArray[i].getName() + " gotowa" );
				}

				else {
						
						console.log("rysuję talię gracza " + playersArray[i].getName());
						if(leftIsBussy && rightIsBussy) {
							
							if(playersArray[i].getPlayNow() === 1) {

								context.fillText("Teraz gra: " + playersArray[i].getName(),420,14);
								console.log("Teraz gra: " + playersArray[i].getName());

							} 

							else {
						
								context.fillText(playersArray[i].getName(),420,14);
							
							}
							
							X_TOP_POSITION = (920 - (cardWidth + ((toArray(playersArray[i].getDeck()).length-1)*10)))/2;

						}

						else {
								

							if(playersArray[i].getPlayNow() === 1) {

								context.fillText("Teraz gra: " + playersArray[i].getName(),x,y);
								console.log("Teraz gra: " + playersArray[i].getName());

							} 

							else {

								context.fillText(playersArray[i].getName(),x,y)
							
							}

							x = x + 760;

						}

						$.each(playersArray[i].getDeck(), function() {

							reversImage = new Image();
							
							if(!leftIsBussy) {

								reversImage.onload = function() {
									context.drawImage(reversImage, X_LEFT_POSITION , Y_LEFT_POSITION = Y_LEFT_POSITION + 10, cardWidth, cardHeight);
								}
	
							}

							else if (!rightIsBussy) {

								reversImage.onload = function() {
									context.drawImage(reversImage, X_RIGHT_POSITION, Y_RIGHT_POSITION = Y_RIGHT_POSITION + 10, cardWidth, cardHeight);
								}

							}

							else if (leftIsBussy && rightIsBussy) {

								reversImage.onload = function() {
									context.drawImage(reversImage, X_TOP_POSITION = X_TOP_POSITION + 10, 20, cardWidth, cardHeight);
								}

							}

							reversImage.src = REVERS_CARD;

						});

						if(!leftIsBussy) {

							leftIsBussy = true;

						}
						
						else if(leftIsBussy && !rightIsBussy) {
							rightIsBussy = true;
						}
				}

				console.log("talia gracza " + playersArray[i].getName() + " gotowa" );
			}
			break;

		default:
			
	}
		
	drawStack(stack, cardWidth, cardHeight);
}

/*---------------*/

var drawStack = function(gameStack, cardW, cardH) {
	if(gameStack != null) {
		console.log('Rysuję stos..');
		var X_TOP_POS = 380;
		$.each(gameStack, function(index,val) {
			drawStackCard(val, X_TOP_POS, cardW, cardH);
			X_TOP_POS += 5;		
		});		
	} else {
		console.log('Stos jest pusty!');
	}
}

/*---------------*/

var drawStackCard = function(card, xPos, width, height) {
	stackCardImage = new Image();
	stackCardImage.src = "images/" + card + ".png";
	stackCardImage.onload = function() {
		context.drawImage(stackCardImage, xPos, 350, width, height);
	}
}

/*---------------*/

var drawMyDeck = function (lista) {
	var shift = 0;
	var len = 0;
	if(lista.length != null) {
		var len = lista.length;
	}
	
	if(len < 6) {
		shift = 800;
	} else if (len >= 6 & len < 8) {
		shift = 700;
	} else if (len >= 8 & len < 10) {
		shift = 600;
	} else if (len >= 10 & len <= 12) {
		shift = 500;
	} 
	
	$('.myDeck ul').empty();
	if(lista != null) {
		$.each(lista, function(index, val) {
			var $list = $('<li></li>');
			var $img = $('<img>');
			var url = "images/" + val + ".png";
			$list.attr("id", val).attr('title', val);
			$img.attr("src", url).attr("alt", "card");
			$list.append($img);
			$list.css({
				'position' : 'absolute',
				'left' : shift
			});
			
			$list.appendTo('.myDeck ul');
			shift += 80;	
		});
		
		var isClicked = false;
		
		$('.myDeck ul li').click(function() {
			if(!$(this).hasClass("clicked")) {
				if(isClicked === false) {
					$(this).addClass("clicked");
					selectedCardId = $(this).attr("id");
					isClicked = true;
				} 
			} else {
				$(this).removeClass("clicked");
				isClicked = false;
				selectedCardId = null;
			}
		});
	}
}

/*---------------*/

var playCard = function(selectedCardId) {
	console.log("Klik OK:" + selectedCardId);
	var urlDataAddress = "status/addCard/" + selectedCardId;
	$.getJSON(urlDataAddress , function(data) {
		var validation = null;
		var statement = null;
		$.each(data, function(index, value) {
			if(index === "validation") {
				validation = value;
			} else if (index === "statement") {
				statement = value;
			}
		});
		pullValidation(validation, statement);
	});
}

/*---------------*/

var takeCards = function() {
	var urlDataAddress = "status/takeCards";
	$.getJSON(urlDataAddress , function(data) {
		var validation;
		var statement;
		$.each(data, function(index, value) {
			if(index === "validation") {
				validation = value;
			} else if (index === "statement") {
				statement = value;
			}
			
			if(validation === "false") {
				alert(statement);
			} else {
				readGameStatus();
			}
			console.log("Validation:" + validation);
			console.log(statement);
		});
	});
}

/*---------------*/

var getGamerName = function(playersArray) {
	var gamerName = "";
	$.each(playersArray, function(index, value) {
		if(playersArray[index].getPlayNow() === 1) {
			gamerName = playersArray[index].getName();
		}
	});
	return gamerName;
}

/*---------------*/

var pullValidation = function(v,s) {
	if(v === "true") {
		console.log(s);
	} else {
		console.log(s);
		alert(s);
	}
	readGameStatus();
}

/*---------------*/

var endGame = function(winner) {
	 var txt;
	    if (confirm("Koniec gry ! Wygrał: " + winner)) {
	    	location.reload();
	    } else {
	        txt = "Rozumiem chcesz jeszcze popatrzeć!";
	    }
}

/*
 * ------------------------------------- 
 * 				START GAME
 * -------------------------------------
 */

$( document ).ready(function() {
	canvas = document.getElementById('canvas');
	context = canvas.getContext('2d');

	var $button_ok = $('<button></button>').text("Kładę").attr('id','ok');
	var $button_take = $('<button></button>').text("Biorę").attr('id','take');
	$('.gamebuttons').append($button_ok );
	$('.gamebuttons').append($button_take);
	$('.gamebuttons').hide();
	readGameStatus();
	setInterval(readGameStatus, 3000);
	$('#ok').click(function() {
		playCard(selectedCardId);
	});
	$('#take').click(function() {
		takeCards();
	});
});







