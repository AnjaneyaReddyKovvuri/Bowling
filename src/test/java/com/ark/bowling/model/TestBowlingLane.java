package com.ark.bowling.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ark.bowling.model.BowlingLane;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class TestBowlingLane {
	
	@DataProvider
	public static Object[] laneScoreData() {
		return new Object[][] { 
			{ Arrays.asList(10, 10, 10, 10, 10, 10, 10, 10, 10, 10,10,10), Arrays.asList(30,30,30,30,30,30,30,30,30,30), 300 , true },
			{ Arrays.asList(10, 7,3, 9,0, 10, 0,8, 8,2, 0,6, 10, 10, 10,8,1), Arrays.asList(20,19,9,18,8,10,6,30,28,19), 167, true },
		};
	}
	
    @Test   
	@UseDataProvider("laneScoreData")
    public void testFrameScores(List<Integer> throwScores, List<Integer> expectedFrameScores, int totalLaneScore, boolean isGameOver) {
        BowlingLane lane = new BowlingLane();
        for(Integer score: throwScores) {
        	lane.dropPins(score);
        }
        lane.printLane();
        System.out.println(lane.gameOver());
        assertEquals(lane.gameOver(),isGameOver);
        assertArrayEquals(lane.getLaneCalculatedScores().toArray(),expectedFrameScores.toArray());
    }
}
