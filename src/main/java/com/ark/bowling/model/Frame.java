package com.ark.bowling.model;

import java.util.ArrayList;
import java.util.List;

import com.ark.bowling.exceptions.InvalidThrowException;

public class Frame {
	protected static final int TOTAL_NO_OF_PINS = 10;
	protected static final int MAX_ALLOWED_THROWS = 2;
	protected boolean frameCompleted;

	protected List<Integer> scores ;
	public int calculatedScore;

	public Frame() {
		scores=new ArrayList<>();
		calculatedScore = 0;
		frameCompleted=false;
	}

	public List<Integer> getScores() {
		return scores;
	}

	int getThrowsDoneInFrame() {
		return scores.size();
	}

	boolean isComplete() {
		return frameCompleted;
	}

	protected int requiredThrowsForFrame() {
		return isStrike() ? 1 : 2;
	}

	protected boolean isStrike() {
		return getFrameScore() == TOTAL_NO_OF_PINS && scores.size() == 1;
	}

	protected boolean isSpare() {
		return !isStrike() && getFrameScore() == TOTAL_NO_OF_PINS && scores.size() == 2;
	}

	Integer getFrameScore() {
		return scores.stream().reduce(0, (a,b)->a+b);
	}
	
	Integer getCalculatedFrameScore() {
		return calculatedScore;
	}

	void setCalculatedFrameScore(Integer score) {
		this.calculatedScore = score;
	}

	void setNextScore(Integer score) {
		if ( frameCompleted || score > TOTAL_NO_OF_PINS || calculatedScore + score > TOTAL_NO_OF_PINS ) {
			throw new InvalidThrowException("Invalid no of pins dropped .." + score);
		}
		
		scores.add(score);
		calculatedScore += score;
		
		if(isStrike()|| scores.size()== MAX_ALLOWED_THROWS) {
			frameCompleted=true;
		}
	}
}