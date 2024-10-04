package com.example.stockup;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MarketService {
	private final Random rand = new Random();
	// initial price for any stock
	private double currentPrice = 100.0;
	
	// method to get random price between -2% to +2%
	public double getCurrentPrice() {
		double changeInPrice = (rand.nextDouble()*4 - 2)/100;
		return currentPrice += changeInPrice*currentPrice;
	}
}