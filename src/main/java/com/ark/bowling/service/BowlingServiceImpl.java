package com.ark.bowling.service;

import org.springframework.stereotype.Service;

import com.ark.bowling.model.BowlingGame;

@Service
public class BowlingServiceImpl implements BowlingService {
	
	BowlingGame game=null;
	public BowlingServiceImpl() {
		game= new BowlingGame();
	}
		
	@Override
	public BowlingGame dropPins(Integer pinnedDrops) {
		game.dropPins(pinnedDrops);
		return game ;
	}

	@Override
	public BowlingGame getScoreCard() {
		return game ;
	}
	
	@Override
	public BowlingGame resetGame() {
		game= new BowlingGame();
		return game ;
	}
}
