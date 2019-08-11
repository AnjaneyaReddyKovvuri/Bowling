package com.ark.bowling.model;

import com.ark.bowling.exceptions.InvalidThrowException;

class LastFrame extends Frame {

	private static final int MAX_ALLOWED_THROWS = 3;

	public LastFrame() {
		super();
	}

	@Override
	protected int requiredThrowsForFrame() {
		return isStrike() || isSpare() ? 3 : 2;
	}

	protected boolean isStrike() {
		return scores.size() >= 1 && scores.get(0) == TOTAL_NO_OF_PINS;
	}

	protected boolean isSecondDropStrike() {
		return scores.size() >= 2 && scores.get(1) == TOTAL_NO_OF_PINS;
	}

	protected boolean isSpare() {
		return scores.size() >= 2 && !isStrike() && scores.get(0) + scores.get(1) == TOTAL_NO_OF_PINS;
	}

	@Override
	void setNextScore(Integer score) {

		if ( frameCompleted 
			|| scores.size() + 1 > MAX_ALLOWED_THROWS 
			|| score > TOTAL_NO_OF_PINS 
			|| !isStrike() && scores.size()==1 && scores.get(0)+score>TOTAL_NO_OF_PINS) {
			throw new InvalidThrowException("Invalid no of pins dropped .." + score);
		}
		scores.add(score);
		calculatedScore += score;

		if ( ( (isStrike() || isSpare())  && scores.size() == 3) 
			|| !isStrike() && !isSpare() && scores.size() == 2) {
			frameCompleted = true;
		}
	}
}