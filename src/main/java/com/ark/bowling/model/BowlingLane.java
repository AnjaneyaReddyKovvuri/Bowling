package com.ark.bowling.model;

import java.util.ArrayList;
import java.util.List;

import com.ark.bowling.exceptions.InvalidThrowException;

public class BowlingLane {

	public static final int TOTAL_NO_OF_FRAMES = 10;
	private int laneTotalScore=0;

	protected Frame currentFrame;

	protected List<Frame> frames = new ArrayList<>();

	public BowlingLane() {
		for (int i = 0; i < TOTAL_NO_OF_FRAMES - 1; i++) {
			frames.add(new Frame());
		}
		frames.add(new LastFrame());
		currentFrame = frames.get(0);
	}

	public int currentFramePosition() {
		return frames.indexOf(currentFrame) + 1;
	}

	public void dropPins(int score) {

		if (gameOver()) {
			throw new InvalidThrowException("Game is already over");
		}

		currentFrame.setNextScore(score);
		
		if (frames.indexOf(currentFrame) != 0) {
			recalculateFrames();
		}
		
		laneTotalScore=calculateTotal();

		if (!isLastFrame(currentFrame) && currentFrame.isComplete()) {
			nextFrame();
		}
	}

	private void recalculateFrames() {
		if (!isFirstFrame(currentFrame)) {
			Frame previousFrame = frames.get(frames.indexOf(currentFrame) - 1);
			if (previousFrame.isSpare()) {
				previousFrame.setCalculatedFrameScore(previousFrame.getFrameScore() + currentFrame.getScores().get(0));
			}
			if (previousFrame.isStrike()) {
				if (currentFrame.getScores().size() == 2) {
					previousFrame.setCalculatedFrameScore(previousFrame.getFrameScore()
							+ currentFrame.getScores().get(0) + currentFrame.getScores().get(1));
				} else if (!isFirstFrame(previousFrame)) {
					Frame previousToPreviousFrame = frames.get(frames.indexOf(previousFrame) - 1);
					if (previousToPreviousFrame.isStrike()) {
						previousToPreviousFrame.setCalculatedFrameScore(previousToPreviousFrame.getFrameScore()
								+ previousFrame.getFrameScore() + currentFrame.getScores().get(0));
					}
				}
			}
		}
	}

	private void nextFrame() {
		currentFrame = nextFrame(currentFrame);
	}

	public boolean gameOver() {
		return isLastFrame(currentFrame) && currentFrame.isComplete();
	}

	public List<Integer> getFrameScores(int frameNumber) {
		return frames.get(frameNumber - 1).getScores();
	}

	public int frameScore(int frameNumber) {
		return frames.get(frameNumber - 1).getFrameScore();
	}

	boolean isFirstFrame(Frame frame) {
		return frames.indexOf(frame) == 0;
	}

	boolean isLastFrame(Frame frame) {
		return frames.indexOf(frame) == TOTAL_NO_OF_FRAMES - 1;
	}

	boolean isLastButOneFrame(Frame frame) {
		return frames.indexOf(frame) == TOTAL_NO_OF_FRAMES - 2;
	}

	Frame nextFrame(Frame frame) {
		return frames.get(frames.indexOf(frame) + 1);
	}

	int index(Frame currentFrame) {
		return frames.indexOf(currentFrame);
	}
	
	public List<Frame> getFrames() {
		return frames;
	}

	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}

	public void printLane() {
		for (Frame frame : frames) {
			System.out.println();
			frame.getScores().stream().forEach(System.out::print);
			System.out.println(frame.getCalculatedFrameScore());
		}
	}
	
	private int calculateTotal() {
		int laneTotalScore=0;
		for (Frame frame : frames) {
			laneTotalScore+=frame.calculatedScore;
		}
		return laneTotalScore;
	}

	public List<Integer> getLaneCalculatedScores() {
		List<Integer> scores = new ArrayList<Integer>();
		for (Frame frame : frames) {
			scores.add(frame.getCalculatedFrameScore());
		}
		return scores;
	}
	
	public int getCurrentFrameIndex() {
		return frames.indexOf(currentFrame);
	}
	
	public int getCurrentThrow() {
		return currentFrame.getScores().size();
	}
	
	public Frame getCurrentFrame() {
		return currentFrame;
	}
	
	public int getLaneTotalScore() {
		return laneTotalScore;
	}

	public void setLaneTotalScore(int laneTotalScore) {
		this.laneTotalScore = laneTotalScore;
	}
}
