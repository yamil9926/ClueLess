package com.clueless.clueless;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class gameBoard {
	private boolean active;
	private boolean endgame;
    private card studyCard;
	private card hallCard;
	private card loungeCard;
	private card libraryCard;
	private card billiardRoomCard;
	private card diningRoomCard;
	private card conservatoryCard;
	private card ballRoomCard;
	private card kitchenCard;
	private card candleStick;
	private card wrench;
	private card rope;
	private card revolver;
	private card knife;
	private card leadPipe;
	private card missS;
	private card profP;
	private card colM;
	private card mrsP;
	private card mrG;
	private card mrsW;
	private room study;
	private room hall;
	private room lounge;
	private room library;
	private billiardRoom billiardRoom;
	private room diningRoom;
	private room conservatory;
	private room ballroom;
	private room kitchen;
	private player player1;
	private player player2;
	private player player3;
	private player player4;
	private player player5;
	private player player6;
	private player winner;
	private hallway hallway1;
	private hallway hallway2;
	private hallway hallway3;
	private hallway hallway4;
	private hallway hallway5;
	private hallway hallway6;
	private hallway hallway7;
	private hallway hallway8;
	private hallway hallway9;
	private hallway hallway10;
	private hallway hallway11;
	private hallway hallway12;
	private startSquare square1;
	private startSquare square2;
	private startSquare square3;
	private startSquare square4;
	private startSquare square5;
	private startSquare square6;
    private player[] playerList;
	private location[] locationList;
    private caseFile CaseFile;
    private card[] toPlayers;
	private int turn;
	private int activePlayers;
	private ArrayList<String> status;
	private ArrayList<String> moveHistory;
	card[] rooms;
	card[] weapons;
	card[] players;

    public gameBoard(){
		status = new ArrayList<String>();

        //rooms
        study = new room("Study","study");
		hall = new room("Hall","hall");
		lounge = new room("Lounge","lounge");
		library = new room("Library","library");
		billiardRoom = new billiardRoom("Billard Room","billiard");
		diningRoom = new room("Dining Room","dining");
		conservatory = new room("Conservatory","conservatory");
		ballroom = new room("Ballroom","ballroom");
		kitchen = new room("Kitchen","kitchen");

        //hallways
		hallway1 = new hallway("Hallway One","hwy1");
		hallway2 = new hallway("Hallway Two","hwy2");
		hallway3 = new hallway("Hallway Three","hwy3");
		hallway4 = new hallway("Hallway Four","hwy4");
		hallway5 = new hallway("Hallway Five","hwy5");
		hallway6 = new hallway("Hallway Six","hwy6");
		hallway7 = new hallway("Hallway Seven","hwy7");
		hallway8 = new hallway("Hallway Eight","hwy8");
		hallway9 = new hallway("Hallway Nine","hwy9");
		hallway10 = new hallway("Hallway Ten","hwy10");
		hallway11 = new hallway("Hallway Eleven","hwy11");
		hallway12 = new hallway("Hallway Twelve","hwy12");

		//start squares
		square1 = new startSquare("Miss Scarlet Square", "scarlet");
		square2 = new startSquare("Prof. Plum Square", "plum");
		square3 = new startSquare("Col. Mustard Square", "mustard");
		square4 = new startSquare("Mrs. Peacock Square", "peacock");
		square5 = new startSquare("Mr. Green Square", "green");
		square6 = new startSquare("Mrs. White Square", "white");

		//all locations
        locationList = new location[21];
		locationList[0] = study;
		locationList[1] = hall;
		locationList[2] = lounge;
		locationList[3] = library;
		locationList[4] = billiardRoom;
		locationList[5] = diningRoom;
		locationList[6] = conservatory;
		locationList[7] = ballroom;
		locationList[8] = kitchen;
		
		locationList[9] = hallway1;
		locationList[10] = hallway2;
		locationList[11] = hallway3;
		locationList[12] = hallway4;
		locationList[13] = hallway5;
		locationList[14] = hallway6;
		locationList[15] = hallway7;
		locationList[16] = hallway8;
		locationList[17] = hallway9;
		locationList[18] = hallway10;
		locationList[19] = hallway11;
		locationList[20] = hallway12;

        //set adjacent rooms
        study.setAdjacent(new location[]{hallway1, hallway3, kitchen});
        hall.setAdjacent(new location[]{hallway1, hallway2, hallway4});
        lounge.setAdjacent(new location[]{hallway2, hallway5, conservatory});
        library.setAdjacent(new location[]{hallway3, hallway6, hallway8});
        billiardRoom.setAdjacent(new location[]{hallway4, hallway6, hallway7, hallway9});
        diningRoom.setAdjacent(new location[]{hallway5, hallway7, hallway10});
        conservatory.setAdjacent(new location[]{hallway8, hallway11, lounge});
        ballroom.setAdjacent(new location[]{hallway11, hallway9, hallway12});
        kitchen.setAdjacent(new location[]{hallway12, hallway10, study});
        hallway1.setAdjacent(new location[]{study, hall});
        hallway2.setAdjacent(new location[]{hall, lounge});
        hallway3.setAdjacent(new location[]{study, library});
        hallway4.setAdjacent(new location[]{hall, billiardRoom});
        hallway5.setAdjacent(new location[]{lounge, diningRoom});
        hallway6.setAdjacent(new location[]{library, billiardRoom});
        hallway7.setAdjacent(new location[]{billiardRoom, diningRoom});
        hallway8.setAdjacent(new location[]{library, conservatory});
        hallway9.setAdjacent(new location[]{billiardRoom, ballroom});
        hallway10.setAdjacent(new location[]{diningRoom, kitchen});
        hallway11.setAdjacent(new location[]{conservatory, ballroom});
        hallway12.setAdjacent(new location[]{ballroom, kitchen});
		square1.setAdjacent(hallway2);
		square2.setAdjacent(hallway3);
		square3.setAdjacent(hallway5);
		square4.setAdjacent(hallway8);
		square5.setAdjacent(hallway11);
		square6.setAdjacent(hallway12);

    	//players
        player1 = new player("Miss Scarlet", "scarlet", square1);
		player1.disabled = true;
		
		player2 = new player("Prof. Plum", "plum", square2);
		player2.disabled = true;
		
		player3 = new player("Col. Mustard", "mustard", square3);
		player3.disabled = true;
		
		player4 = new player("Mrs. Peacock", "peacock", square4);
		player4.disabled = true;
		
		player5 = new player("Mr. Green", "green", square5);
		player5.disabled = true;
		
		player6 = new player("Mrs. White", "white", square6);
		player6.disabled = true;
		
		playerList = new player[6];
		playerList[0] = player1;
		playerList[1] = player2;
		playerList[2] = player3;
		playerList[3] = player4;
		playerList[4] = player5;
		playerList[5] = player6;
		
		winner = null;

		activePlayers = 0;

        //cards
        studyCard = new card("Study", cardType.room);
		hallCard = new card("Hall", cardType.room);
		loungeCard = new card("Lounge", cardType.room);
		libraryCard = new card("Library", cardType.room);
		billiardRoomCard = new card("Billiard Room", cardType.room);
		diningRoomCard = new card("Dining Room", cardType.room);
		conservatoryCard = new card("Conservatory", cardType.room);
		ballRoomCard = new card("Ballroom", cardType.room);
		kitchenCard = new card("Kitchen", cardType.room);
		candleStick = new card("Candle Stick", cardType.weapon);
		wrench = new card("Wrench", cardType.weapon);
		rope = new card("Rope", cardType.weapon);
		revolver = new card("Revolver", cardType.weapon);
		knife = new card("Knife", cardType.weapon);
		leadPipe = new card("Lead Pipe", cardType.weapon);
		missS = new card("Miss Scarlet", cardType.player);
		profP = new card("Prof. Plum", cardType.player);
		colM = new card("Col. Mustard", cardType.player);
		mrsP = new card("Mrs. Peacock", cardType.player);
		mrG = new card("Mr. Green", cardType.player);
		mrsW = new card("Mrs. White", cardType.player);

        rooms = new card[]{studyCard, hallCard, loungeCard, libraryCard, billiardRoomCard, diningRoomCard, conservatoryCard, ballRoomCard, kitchenCard};
		weapons = new card[]{candleStick, wrench, rope, revolver, knife, leadPipe};
		players = new card[]{missS, profP, colM, mrsP, mrG, mrsW};
		

        shuffle(rooms);
		shuffle(weapons);
		shuffle(players);

		//winning combo of cards
        CaseFile = new caseFile(players[0], weapons[0], rooms[0]);

        //21 total cards - 3 for case file
        toPlayers = new card[18];
		int x = 0;
		for (int i = 1; i < rooms.length; i++){
			toPlayers[x] = rooms[i];
			x++;
		}
		for (int i = 1; i < weapons.length; i++){
			toPlayers[x] = weapons[i];
			x++;
		}
		for (int i = 1; i < players.length; i++){
			toPlayers[x] = players[i];
			x++;
		}
        shuffle(toPlayers);

		turn = 1;
		active = false;
		endgame = false;

    }

	public player getCurrentPlayer() {
		switch(turn){
			case 1: return player1;
			case 2: return player2;
			case 3: return player3;
			case 4: return player4;
			case 5: return player5;
			case 6: return player6;
		}
		return null;
	}

	public ArrayList<player> getOtherPlayers(){
		player current = getCurrentPlayer();
		ArrayList<player> players = new ArrayList<player>();
		for(player p: playerList){
			if(p.name != current.name){
				players.add(p);
			}
		}
		return players;
	}

	public void  endTurn(ArrayList<message> chat) {
		player player = getCurrentPlayer();
		String statusMessage = "";
		
		player.endTurn();
		
		statusMessage = "***End " + player.name + "'s turn***";
		message msg = new message("system", statusMessage);

		chat.add(msg);
		System.out.println(statusMessage);
		System.out.println();
		
		if (turn == 6)
			turn = 1;
		else
			turn++;
		
		if (getNumActivePlayers() == 0)
			endGame(chat);
		else
			beginTurn(chat);
	}
	
	public void beginTurn(ArrayList<message> chat) {
		player player = getCurrentPlayer();
		String statusMessage = "";
		
		if(player.disabled){
			statusMessage = "***" + player.name + " has been skipped ***";
			message msg = new message("system", statusMessage);
			chat.add(msg);
			System.out.println(statusMessage);
			System.out.println();
			this.endTurn(chat);
		}
		else {
			player.hasTurn = true;
			player.moved = false;
			
			statusMessage = "***Begin " + player.name + "'s turn***";
			message msg = new message("system", statusMessage);

			chat.add(msg);
			System.out.println(statusMessage);
			System.out.println();
			
		}
	}

	public void endGame(ArrayList<message> chat) {
		String statusMessage = "***GAME OVER***";
		message msg = new message("system", statusMessage);
		
		chat.add(msg);
		System.out.println(statusMessage);
		System.out.println();
		
		active = false;
		endgame = true;
	}

	public Boolean startGame(ArrayList<message> chat){
		if(activePlayers > 2){
			sortPlayerCards();
			active = true;
			beginTurn(chat);
			return true;
		}
		return false;	
	}

	private void sortPlayerCards() {
		int max = 18/activePlayers;
		if(activePlayers == 3 || activePlayers == 6){
			for(int i = 0; i < activePlayers; i++){
				for(int j = 0; j < max; j++){
					playerList[i].addCard(toPlayers[(i*max) + j]);
				}
			}
		}else if(activePlayers == 4){
			for(int i = 0; i < 4; i++){
				if (i<2){
					for(int j = 0; j <= max; j++){
						playerList[i].addCard(toPlayers[(i*(max+1)) + j]);
					}
				}else{
					for(int j = 0; j < max; j++){
						playerList[i].addCard(toPlayers[(i*max) + j]);
					}
				}
			}
		}else if(activePlayers == 5){
			for(int i = 0; i < 5; i++){
				if (i<3){
					for(int j = 0; j <= max; j++){
						playerList[i].addCard(toPlayers[(i*(max+1)) + j]);
					}
				}else{
					for(int j = 0; j < max; j++){
						playerList[i].addCard(toPlayers[(i*max) + j]);
					}
				}
			}
		}
	}

	public ArrayList<String> getMoveOptions() {
		player player = getCurrentPlayer();
		
		if(player.hasTurn){
			location current =  player.getLocation();
			location[] options = getAdjacent(current);
			
			ArrayList<String> outputOptions = new ArrayList<String>();
			location one = options[0];
			location two = options [0];
			location three = options [0];
			location four = options[0];
		
//			System.out.print("1 = " + one.name + ", 2 = " + two.name);
			if(options[0] != null) {
				one = options[0];
				two = options[0];
				three = options[0];
				four = options[0];
				outputOptions.add(one.name);
				System.out.print("1 = " + one.name);
			}
			
			if (options[1] != null){
				two = options[1];
				outputOptions.add(two.name);
				System.out.print(", 2 = " + two.name);
			}
			
			
			if (options[2] != null){
				three = options[2];
				System.out.print(", 3 = " + three.name);
				
				outputOptions.add(three.name);
			}
			if (options[3] != null){
				four = options[3];
				System.out.print(", 4 = " + four.name);
				
				outputOptions.add(four.name);
			}
			
			System.out.println();	
			
			return outputOptions;
		}
		
		return null;
	}

	public void move(int movePlace) throws Exception {
		player player = getCurrentPlayer();
		String errorMessage = "";
		String statusMessage = "";
		
		if(player.hasTurn){
			System.out.println("");
			
			if (player.moved) {
				errorMessage = "You can only move once per turn";
				
				System.out.println(errorMessage);
				System.out.println();
				
				throw new Exception(errorMessage);
			}
			else {
				
				System.out.println("Can Move to:");
				System.out.println();
				
				location current =  player.getLocation();
				location[] options = getAdjacent(current);
				
				location one = options[0];
				location two = options [0];
				location three = options [0];
				boolean threeTrue = false;
				location four = options[0];
				boolean fourTrue = false;
				
//				System.out.print("1 = " + one.name + ", 2 = " + two.name);
				if(options[0] != null) {
					one = options[0];
					two = options[0];
					three = options[0];
					four = options[0];
					System.out.print("1 = " + one.name);
				}
				
				if (options[1] != null){
					two = options[1];
					System.out.print(", 2 = " + two.name);
				}

				if (options[2] != null){
					three = options[2];
					threeTrue = true;
					System.out.print(", 3 = " + three.name);
				}
				if (options[3] != null){
					four = options[3];
					fourTrue = true;
					System.out.print(", 4 = " + four.name);
				}
				System.out.println();

				if (movePlace == 1){
					current.removeOccupant(player);
					one.setOccupant(player);					
					player.move(one);
					player.moved = true;

					statusMessage = player.name + " has moved from " + current.name + " to the " + one.name;
					
					moveHistory.add(statusMessage);
					System.out.println(statusMessage);
					
				}
				else if (movePlace == 2 && options[1] != null){
					current.removeOccupant(player);
					two.setOccupant(player);	
					player.move(two);
					player.moved = true;
					
					statusMessage = player.name + " has moved from " + current.name + " to the " + two.name;
					
					moveHistory.add(statusMessage);
					System.out.println(statusMessage);
					
				}
				else if (movePlace == 3 && threeTrue && options[2] != null){
					current.removeOccupant(player);
					three.setOccupant(player);
					player.move(three);
					player.moved = true;
					
					statusMessage = player.name + " has moved from " + current.name + " to the " + three.name;
					
					moveHistory.add(statusMessage);
					System.out.println(statusMessage);
					
				}
				else if (movePlace == 4 &&  fourTrue && options[3] != null){
					current.removeOccupant(player);
					four.setOccupant(player);
					player.move(four);
					player.moved = true;
					
					statusMessage = player.name + " has moved from " + current.name + " to the " + four.name;
					
					moveHistory.add(statusMessage);
					System.out.println(statusMessage);
					
				}
				else {
					errorMessage = "Invalid input, try again";
					
					System.out.println(errorMessage);
					System.out.println();
					
					throw new Exception(errorMessage);
				}
			}
		}
	}

		
	//verify
	public ArrayList<String> getPlayerCardOptions() {
		player player = getCurrentPlayer();
		
		if(player.hasTurn){
			ArrayList<String> outputOptions = new ArrayList<String>();
			
			System.out.println("Select cards to suggest/accuse:");
			System.out.println("Player: 1 = Miss Scarlet, 2 = Prof. Plum");
			System.out.println("3 = Col. Mustard, 4 = Mrs. Peacock");
			System.out.println("5 = Mr. Green, 6 = Mrs. White");
			
			System.out.println();
			
			outputOptions.add("Miss Scarlet");
			outputOptions.add("Prof. Plum");
			outputOptions.add("Col. Mustard");
			outputOptions.add("Mrs. Peacock");
			outputOptions.add("Mr. Green");
			outputOptions.add("Mrs. White");
			
			return outputOptions;
		}
		
		return null;
	}
	
	public ArrayList<String> getWeaponCardOptions() {
		player player = getCurrentPlayer();
		
		if(player.hasTurn){
			ArrayList<String> outputOptions = new ArrayList<String>();
			
			System.out.println("Weapon: 1 = Candle Stick, 2 = Wrench");
			System.out.println("3 = Rope, 4 = Revolver");
			System.out.println("5 = Knife, 6 = Lead Pipe");
			
			System.out.println();
			
			outputOptions.add("Candle Stick");
			outputOptions.add("Wrench");
			outputOptions.add("Rope");
			outputOptions.add("Revolver");
			outputOptions.add("Knife");
			outputOptions.add("Lead Pipe");	
			
			return outputOptions;
		}
		
		return null;
	}
	
	public ArrayList<String> getPlaceCardOptions() {
		player player = getCurrentPlayer();
		
		if(player.hasTurn){
			ArrayList<String> outputOptions = new ArrayList<String>();
			
			System.out.println("Room: 1 = Study, 2 = Hall");
			System.out.println("3 = Lounge, 4 = Library");
			System.out.println("5 = Billard Room, 6 = Dining Room");
			System.out.println("7 = Conservatory, 8 = Ballroom");
			System.out.println("9 = Kitchen");
			
			System.out.println();
			
			outputOptions.add("Study");
			outputOptions.add("Hall");
			outputOptions.add("Lounge");
			outputOptions.add("Library");
			outputOptions.add("Billiard Room");
			outputOptions.add("Dining Room");	
			outputOptions.add("Conservatory");
			outputOptions.add("Ballroom");
			outputOptions.add("Kitchen");
			
			return outputOptions;
		}
		
		return null;
	}

	public String[] suggest(String suggestPlayer, String suggestWeapon, ArrayList<message> chat) {
		String[] result = new String[]{"",""};
		player player = getCurrentPlayer();
		String errorMessage = "";
		String statusMessage = "";
		
		if(player.hasTurn){
			if (!player.canSuggest) {
				errorMessage = "You cannot make a suggestion.";
				
				System.out.println(errorMessage);
				System.out.println();
				
				return result;
			}
			else {
				card playerS = getCardName(suggestPlayer);

				card playerW = getCardName(suggestWeapon);
				
				location place = player.getLocation();
				card playerR;

				if (place == study)
					playerR = studyCard;
				else if (place == hall)
					playerR = hallCard;
				else if (place == lounge)
					playerR = loungeCard;
				else if (place == library)
					playerR = libraryCard;
				else if (place == billiardRoom)
					playerR = billiardRoomCard;
				else if (place == diningRoom)
					playerR = diningRoomCard;
				else if (place == conservatory)
					playerR = conservatoryCard;
				else if (place == ballroom)
					playerR = ballRoomCard;
				else if (place == kitchen)
					playerR = kitchenCard;
				else{
					errorMessage = "You must be in a room to make a suggestion";
					
					System.out.println(errorMessage);
					System.out.println();
					
					return result;
				}
				
				if(inRoom()){
					player.makeSuggestion(playerS, playerW, playerR);
					
					statusMessage = player.name + " has suggested: " + playerS.name + " with the " + playerW.name + " in the " + playerR.name;
					message msg = new message("system", statusMessage);
						
					chat.add(msg);
					System.out.println(statusMessage);
					System.out.println();
					
					// Moving players based on suggestion
					location fromHere;
					location toHere = player.getLocation();
					
					player culprit = getPlayerName(suggestPlayer);
					
					fromHere = culprit.getLocation();
					fromHere.removeOccupant(culprit);
					toHere.setOccupant(culprit);
					culprit.setLocation(toHere);
					culprit.canSuggest = true;

					statusMessage = suggestPlayer + " has been moved to " + playerR.name + " because of this suggestion.";
					msg = new message("system", statusMessage);
						
					chat.add(msg);
					System.out.println(statusMessage);
					System.out.println();
					//UP TO HERE IS GOOD
					for (int i = 0; i < activePlayers; i++) { // verify ignoring og player
						player p = playerList[i];
						if(p.name != player.name){
							card c = p.proveOrDisproveSuggestion(player.suggestion);
							if(c != null) {
								statusMessage = "Suggestion disproved by " + p.name + "'s " + c.name + " card";
								msg = new message(player.name, statusMessage);
								
								chat.add(msg);
								System.out.println(statusMessage);
								System.out.println();

								statusMessage = "Suggestion was dirproven.";
								msg = new message("system", statusMessage);
								
								chat.add(msg);
								System.out.println(statusMessage);
								System.out.println();
								
								result[0] = p.name;
								result[1] = c.getName();
								
								return result;
							}
						}
					}
					
					statusMessage = "No player was able to disprove your suggestion";
					msg = new message("system", statusMessage);
					
					chat.add(msg);
					System.out.println(statusMessage);
					System.out.println();

				}
			}
		}
		return result;
	}	

	public boolean accuse(String suggestPlayer, String suggestWeapon, String place, ArrayList<message> chat) { //only open case file
		player player = getCurrentPlayer();
		String statusMessage = "";
		
		if(player.hasTurn){
			card playerS = getCardName(suggestPlayer);
	
			card playerW = getCardName(suggestWeapon);
			
			card playerR = getCardName(place);
			
			player.makeAccusation(playerS, playerW, playerR);
			
			statusMessage = player.name + " has accused: " + playerS.name + " with the " + playerW.name + " in the " + playerR.name;
			message msg = new message("system", statusMessage);
			
			chat.add(msg);
			System.out.println(statusMessage);
			System.out.println();

			card[] finale = CaseFile.reveal();
			if(player.accusation[0] == finale[0] && player.accusation[1] == finale[1] && player.accusation[2] == finale[2]){
				statusMessage = finale[0].name + " committed the murder " + " with the " + finale[1].name + " in the " + finale[2].name;
				msg = new message("system", statusMessage);
			
				chat.add(msg);
				System.out.println(statusMessage);
				System.out.println();
				
				statusMessage = "*** " + player.name + " wins! ***";
				msg = new message("system", statusMessage);
				
				chat.add(msg);
				System.out.println(statusMessage);
				System.out.println();
				
				winner = player;
				endGame(chat);
				
				return true;

			}else{
				statusMessage = "Accusation was not correct, " + player.name + " has been eliminated.";
				msg = new message("system", statusMessage);
						
				chat.add(msg);
				System.out.println(statusMessage);
				System.out.println();
				
				player.disable();
				activePlayers--;
				endTurn(chat);
				
				return false;
			}		
			
		}
		
		return false;
	}
	
	
	public int getNumActivePlayers() {
		return activePlayers; 
	}

    private void shuffle(card[] ar){
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--){
			int index = rnd.nextInt(i + 1);
			card a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
	public location[] getAdjacent(location location){
		location[] adj = new location[]{null, null, null, null};
		location[] temp;
		if(location instanceof room){
			temp = ((room) location).getAdjacent("");
			adj[0] = temp[0];
			adj[1] = temp[1];
			adj[2] = temp[2];
		}else if(location instanceof billiardRoom){
			adj = ((billiardRoom) location).getAdjacent("");
		}else if(location instanceof hallway){
			temp = ((hallway) location).getAdjacent("");
			adj[0] = temp[0];
			adj[1] = temp[1];
		}else if(location instanceof startSquare){
			adj[0] = ((startSquare) location).getAdjacent("");
		}

		return adj;
	}

	public boolean inRoom() {
		player player = getCurrentPlayer();
		
		location place = player.getLocation();

		boolean inRoom = false;
		if (place instanceof room || place instanceof billiardRoom) {
			inRoom = true;
		}
	
		return inRoom;
	}

	public boolean canSuggest() {
		player player = getCurrentPlayer();
		
		if (inRoom() && player.canSuggest)
			return true;
		
		return false;
	} 

	public Boolean addPlayer(String id) {
		if (!isActive()) {
			if (getNumActivePlayers() < 6) {
				player p = getPlayer(getNumActivePlayers()+1);
				p.setId(id);
				p.disabled = false;
				activePlayers++;
				if(getNumActivePlayers() == 1){
					p.makeAdmin();
				}
				
				String statusMessage = "New player joined game as " + p.name + " - id # " + id;
				
				status.add(statusMessage);
				System.out.println(statusMessage);
				System.out.println();
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	

	public player getPlayer(String id) {
		for (player p : playerList) {
			if (p.getId() != null && !p.getId().isEmpty() && p.getId().equals(id))
				return p;
		}
		return null;
	}

	public player getPlayer(int position) {
		switch(position){
			case 1: return player1;
			case 2: return player2;
			case 3: return player3;
			case 4: return player4;
			case 5: return player5;
			case 6: return player6;
		}//end switch
		return null;
	}

	public player getPlayerName(String name){
		for(player p: playerList){
			if (p.getName().equals(name) || p.getCodename().equals(name) || p.getId().equals(name)){
				return p;
			}
		}
		return null;
	}

	public location getLocationName(String name){
		for(location l: locationList){
			if (l.name.equals(name) || l.codename.equals(name)){
				return l;
			}
		}
		return null;
	}

	public boolean isActive() {
		return active;
	}

	public Boolean isRoom(location l){
		for(card c: rooms){
			if(c.name == l.name){
				return true;
			}
		}
		return false;
	}

	public ArrayList<player> getActivePlayers(){
		ArrayList<player> players = new ArrayList<>();
		for(player p :playerList){
			if(!p.isDisabled()){
				players.add(p);
			}
		}
        return players;
	}

	public card getCardName(String name){
		for(card c: rooms){
			if (c.getName().equals(name)){
				return c;
			}
		}
		for(card c: players){
			if (c.getName().equals(name)){
				return c;
			}
		}
		for(card c: weapons){
			if (c.getName().equals(name)){
				return c;
			}
		}
		return null;
	}

	public card[] caseFile(){
		return CaseFile.reveal();
	}
}
