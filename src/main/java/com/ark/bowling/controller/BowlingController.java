package com.ark.bowling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ark.bowling.exceptions.InvalidThrowException;
import com.ark.bowling.model.BowlingGame;
import com.ark.bowling.service.BowlingService;
import com.ark.bowling.util.Constants;

@Controller
public class BowlingController {
	
	@Autowired
	BowlingService bowlingService;
	
	@GetMapping(Constants.GET_DROP_PINS)
	public String dropPins(@RequestParam Integer pinsDropped, Model model) {
		BowlingGame game=null;
		try {
			 game= bowlingService.dropPins(pinsDropped);
		}catch(InvalidThrowException e) {
			game=bowlingService.getScoreCard();
			model.addAttribute("error_message",e.getMessage());
		}finally {
			model.addAttribute("game",game);					
		}
		
		return "bowling/bowling_scorecard";		
	}
	
	@GetMapping(Constants.GET_ROOT)
	public String getScoreCard( Model model) {
		model.addAttribute("game",bowlingService.getScoreCard());
		return "bowling/bowling_scorecard";
	}
	
	@GetMapping(Constants.GET_RESET_GAME)
	public String resetGame(Model model) {		
		model.addAttribute("game",bowlingService.resetGame());	
		return "bowling/bowling_scorecard";		
	}	
}