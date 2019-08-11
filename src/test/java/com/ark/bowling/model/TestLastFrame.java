package com.ark.bowling.model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ark.bowling.exceptions.InvalidThrowException;
import com.ark.bowling.model.Frame;
import com.ark.bowling.model.LastFrame;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class TestLastFrame {

	@DataProvider
	public static Object[] lastFrameData() {
		return new Object[][] { 
			{ Arrays.asList(5, 5, 5), 15 , false, true, true },
			{ Arrays.asList(3, 7, 2), 12 , false, true, true },
			{ Arrays.asList(5, 5), 10 , false, true, false },
			{ Arrays.asList(0, 1), 1 , false, false, true}, 
			{ Arrays.asList(0, 0), 0, false, false , true},
			{ Arrays.asList(0, 10, 6), 16, false, true , true},
			{ Arrays.asList(10), 10 , true, false, false},
			{ Arrays.asList(10,2), 12 , true, false, false},
			{ Arrays.asList(10,2,1), 13 , true, false, true},
			{ Arrays.asList(10,10), 20 , true, false, false},
			{ Arrays.asList(10,10,8), 28 , true, false, true},
			{ Arrays.asList(10,10,10), 30 , true, false, true},
			{ Arrays.asList(10,8,1), 19 , true, false, true},
			{ Arrays.asList(7), 7 , false, false, false} 
		};
	}

	@Test
	@UseDataProvider("lastFrameData")
	public void testSetNextScore(List<Integer> scores, Integer frameScore, boolean isStrike, boolean isSpare, boolean isComplete) {
		Frame frame = new LastFrame();
		
		for (Integer score : scores) {
			frame.setNextScore(score);
		}
		
		assertEquals(frame.getFrameScore(), frameScore);
		assertEquals(frame.isStrike(), isStrike);
		assertEquals(frame.isSpare(), isSpare);
		assertEquals(frame.isComplete(), isComplete);
	}
	
	@DataProvider
	public static Object[] frameInvalidData() {
		return new Object[][] { 
			{ Arrays.asList(11)},
			{ Arrays.asList(9, 2) },
			{ Arrays.asList(5, 6, 1) },
			{ Arrays.asList(10, 10, 10, 1) },
			{ Arrays.asList(10, 8, 2, 1) },
			{ Arrays.asList(7, 2, 1)} 
		};
	}

	@Test(expected = InvalidThrowException.class)
	@UseDataProvider("frameInvalidData")
	public void testInvalidScores(List<Integer> scores) {
		Frame frame = new LastFrame();
		for (Integer score : scores) {
			frame.setNextScore(score);
		}		
	}
}