package com.clueless.clueless;

import java.util.ArrayList;
import java.util.Map;

public class statusTracker {
	private boolean playerTurn;
	private boolean gameActive;
	private boolean suggestAllowed;
	private boolean accusationAllowed;
	private boolean moveAllowed;
	private boolean endGame;

	private ArrayList<String> statusUpdate;
	private ArrayList<String> moveHistory;

	private String[] locations;
	private Map<String, String> playerLocations;

	public statusTracker(boolean playerTurn, boolean gameActive, boolean suggestAllowed, boolean accusationAllowed,
			boolean moveAllowed,
			ArrayList<String> statusUpdate, ArrayList<String> moveHistory, String[] locations,
			Map<String, String> playerLocations, boolean endGame) {
		// constructors
		this.playerTurn = playerTurn;
		this.gameActive = gameActive;
		this.suggestAllowed = suggestAllowed;
		this.accusationAllowed = accusationAllowed;
		this.moveAllowed = moveAllowed;
		this.statusUpdate = statusUpdate;
		this.moveHistory = moveHistory;
		this.locations = locations;
		this.playerLocations = playerLocations;
		this.endGame = endGame;
	}

	// setter and getters
	public boolean getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(boolean playerTurn) {
		this.playerTurn = playerTurn;
	}

	public boolean getGameActive() {
		return gameActive;
	}

	public void setGameActive(boolean gameActive) {
		this.gameActive = gameActive;
	}

	public boolean getSuggestAllowed() {
		return suggestAllowed;
	}

	public void setSuggestAllowed(boolean suggestAllowed) {
		this.suggestAllowed = playerTurn;
	}

	public boolean getAccusationAllowed() {
		return accusationAllowed;
	}

	public void setAccusationAllowed(boolean accusationAllowed) {
		this.accusationAllowed = accusationAllowed;
	}

	public boolean getMoveAllowed() {
		return moveAllowed;
	}

	public void setMoveAllowed(boolean moveAllowed) {
		this.moveAllowed = moveAllowed;
	}

	public ArrayList<String> getStatusUpdate() {
		return statusUpdate;
	}

	public void setStatusUpdate(ArrayList<String> statusUpdate) {
		this.statusUpdate = statusUpdate;
	}

	public ArrayList<String> getMoveHistory() {
		return moveHistory;
	}

	public void setMoveHistory(ArrayList<String> moveHistory) {
		this.moveHistory = moveHistory;
	}

	public String[] getLocations() {
		return locations;
	}

	public void setLocations(String[] locations) {
		this.locations = locations;
	}

	public Map<String, String> getPlayerLocations() {
		return playerLocations;
	}

	public void setPlayerLocations(Map<String, String> locations) {
		this.playerLocations = playerLocations;
	}

	public boolean getEndGame() {
		return endGame;
	}

	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}

}
