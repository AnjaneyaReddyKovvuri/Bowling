package com.ark.bowling.model;

public class BowlingGame {

	public static final int NO_OF_PLAYERS = 1;

	private BowlingLane[] lanes = null;

	private int currentPlayerIndex = 0;

	private boolean isGameOver=false;

	public BowlingGame() {
		lanes = new BowlingLane[NO_OF_PLAYERS];
		for (int i = 0; i < NO_OF_PLAYERS; i++) {
			lanes[i] = new BowlingLane();
		}
	}

	public void dropPins(Integer score) {
		
		Frame currentFrame= lanes[currentPlayerIndex].getCurrentFrame();
		lanes[currentPlayerIndex].dropPins(score);

		if (currentFrame.isComplete()) {
			currentPlayerIndex++;
			currentPlayerIndex = currentPlayerIndex % NO_OF_PLAYERS;
		}
		
		if( isLastPlayer() && lanes[currentPlayerIndex].getCurrentFrame().isComplete()) {
			isGameOver=true;
		}
	}

	public int getCurrentPlayer() {
		return currentPlayerIndex;
	}

	
	public int getCurrentFrame() {
		return lanes[currentPlayerIndex].getCurrentFrameIndex();
	}
	
	public int getCurrentThrow() {
		return lanes[currentPlayerIndex].getCurrentThrow();
	}

	private boolean isLastPlayer() {
		return currentPlayerIndex==NO_OF_PLAYERS-1;
	}
	public boolean isGameOver() {
		return isGameOver;
	}

	public BowlingLane[] getLanes() {
		return lanes;
	}

	public void setLanes(BowlingLane[] lanes) {
		this.lanes = lanes;
	}

	public static int getNoOfPlayers() {
		return NO_OF_PLAYERS;
	}
}