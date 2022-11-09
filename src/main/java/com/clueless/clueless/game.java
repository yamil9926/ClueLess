package com.clueless.clueless;

import java.util.UUID;

public class game {
	private String name;
	private String password;
	private String id;
	private gameBoard gameBoard = null;
	
	public game(String gameName, String gamePassword) {
		name = gameName;
		password = gamePassword;
		id = String.valueOf(UUID.randomUUID());
		
		setGameBoard();
	}
	public boolean login(String gameName, String gamePassword) {
		if (name.equals(gameName) && password.equals(gamePassword))
			return true;
		
		return false;
	}
	private void setGameBoard() {
		gameBoard = new gameBoard();
	}
	public gameBoard getGameBoard() {
		return gameBoard;
	}
	public void endGame() {
		if (gameBoard != null)
			gameBoard.endGame();
		gameBoard = null;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}