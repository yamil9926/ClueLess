package com.clueless.clueless;

import java.util.ArrayList;
import java.util.UUID;

public class game {
	private String name;
	private String password;
	private String id;
	private gameBoard gameBoard = null;
	public ArrayList<message> chat = new ArrayList<message>();
	
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
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}