package com.ark.bowling.service;

import com.ark.bowling.model.BowlingGame;

public interface BowlingService {
	BowlingGame dropPins(Integer pinsDropped);
	BowlingGame getScoreCard();
	BowlingGame resetGame();
}
