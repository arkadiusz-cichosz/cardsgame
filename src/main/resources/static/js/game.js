const MY_NAME = "arek";
const REVERS_CARD = "images/rewers.png";

//var state = "WAITING";
const CARDS_DECK = 24; 
const FONT = "bold 14px Arial";
var canvas = document.getElementById('canvas');
var context = canvas.getContext('2d');	

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

/*var gameStatus = {
	"state" : "WAITING",
	"stack":
			[
				
				"10Trefl",
			],
	
	"players":
				[
					{	"playerName":"arek", 
					 	"playerDeck":
					 				[
										{"image":"9trefl"},
										{"image":"damatrefl"},
										{"image":"astrefl"},
										{"image":"walettrefl"},
										{"image":"10trefl"},
										{"image":"walettrefl"},
										{"image":"10trefl"}
									],
						"playNow": 0
					},

					{	"playerName":"wacek", 
		 				"playerDeck":
		 							[
										{"image":"10trefl"},
										{"image":"kroltrefl"}
									],
						"playNow": 0
					},

					{	"playerName":"ola", 
		 				"playerDeck":
		 							[
										{"image":"10trefl"}

									],
						"playNow": 0
					},
					{	"playerName":"zdzichu", 
		 				"playerDeck":
		 							[
										{"image":"astrefl"},
										{"image":"walettrefl"},
										{"image":"10trefl"},

									],
						"playNow": 1
					}
				]
}*/


var readGameStatus = function() {
	$.getJSON("status/game" , function(data) {
		var stack = [null];
		var players = [null];
		$.each(JSON.stringify(data), function(index, val) {
			
		}
		drawGameBoard(players, stack);
	});
}

var refresh = function() {
	readGameStatus();
	
}

var toObjectPlayerArray = function(JsonPlayersArray) {
	var list =[];
	$.each(JsonPlayersArray, function() {
		var name;
		var deck;
		var play;
		$.each(this, function(index, val) {
			if(index === "playerName") {
				name = val;
			}
			
			else if(index === "playerDeck") {
				deck = val;
			}

			else if (index === "playNow") {
				play = val; 
			}

		});
		list.push(new player(deck,name,play));
	});
	return list;
}

var toArray = function(JsonArray) {
	var array = [];
	$.each(JsonArray, function() {
		$.each(this, function(index, val) {
			 array.push(val);
		});
	});
	return array;
}

var drawGameBoard = function(playersArray, stack) {
	
	var cardWidth = 160;
	var cardHeight = 250;
	var topPlayerDeck = null;
	var stackCardImage = null;
	var reversImage = null;
	var X_TOP_POSITION = 150;
	var X_LEFT_POSITION = 0;
	var Y_LEFT_POSITION = Y_RIGHT_POSITION = 190;
	var X_RIGHT_POSITION = 715;
	context.font = FONT;

	switch(playersArray.length) {

		case 2:
			for(var i = 0; i < 2; i++) {
				if (playersArray[i].getName() === MY_NAME) {
					console.log("rysuję moją talię gracza " + playersArray[i].getName());
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

						context.fillText("Teraz gra: " + playersArray[i].getName(),420,14);
					}

					X_TOP_POSITION = (920 - (cardWidth + ((toArray(playersArray[i].getDeck()).length-1)*10)))/2;

					$.each(playersArray[i].getDeck(), function() {
	
						reversImage = new Image();
						
						reversImage.onload = function() {
								context.drawImage(reversImage, X_TOP_POSITION = X_TOP_POSITION + 10, 20, cardWidth, cardHeight);
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
			
	drawStack(stack, cardWidth, cardHeight, 380);	
}

var drawStack = function(stack, cardW, cardH, X_TOP_POSITION) {
	//X_TOP_POSITION = 150;
	$.each(stack, function() {
		$.each(this, function(index,val) {
			stackCardImage = new Image();
			console.log('stos');
			stackCardImage.onload = function() {
				context.drawImage(stackCardImage, X_TOP_POSITION = X_TOP_POSITION + 5, 350, cardW, cardH);
			}
			stackCardImage.src = "images/" + val + ".png";
			console.log("src="+stackCardImage.src);
		});
	});
}

var drawMyDeck = function (lista) {
	var shift = 700;
	//$('.myDeck ul').empty();
	$.each(lista, function() {
		$.each(this, function(index, val) {
		$('<li id="' + val + '"><img src="images/' + val + ".png" + '" alt="' + index + '" ></li>').css({
			'position' : 'absolute',
			'left' : shift
		}).appendTo('.myDeck ul');
		shift = shift + 80;
		});		
	});
	
}

var playCard = function() {
	var id = $(this).attr('id');
	console.log(id);
}

/*
-------------------------------------
			START GAME
-------------------------------------
*/
readGameStatus();
drawGameBoard(players, stack);
/*if (state !== "WAITING") {
	drawGameBoard(players, stack);
}*/
setInterval(refresh, 5000);
$('.myDeck li').click(playCard);





