package com.example.stockup;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyStockBots {
	private final double initialBalance = 10000; // Initial amount in hand
	private final double bestBuyPercent = 0.02; // taking 2% drop to buy
	private final double bestSellPercent = 0.03; // 3% rise to sell
	@Getter
	private double balance;
	private boolean stockHolding = false;
	private double purchasePrice;
	private double profitOrLoss = 0;
	private int sharesHeld = 0;
	@Autowired
	private StockReport stockReport;
	
	public MyStockBots() {
		this.balance = initialBalance;
	}
	
	public void updateStockPrice(double stockPrice) {
		try {
			if (!stockHolding && OurBuyTime(stockPrice)) {
				buyStock(stockPrice);
			}
			else if (stockHolding && OurSellTime(stockPrice)) {
				sellStock(stockPrice);
			}
		} catch (Exception e) {
			System.out.println("Error in Trading " + e.getMessage());
		}
	}
	
	private boolean OurBuyTime(double stockPrice) {
		return !stockHolding &&
		       (purchasePrice == 0 || (purchasePrice - stockPrice)/purchasePrice >= bestBuyPercent);
	}
	
	private boolean OurSellTime(double stockPrice) {
		return stockHolding && (stockPrice - purchasePrice)/purchasePrice >= bestSellPercent;
	}
	
	private void buyStock(double stockPrice) {
		sharesHeld = (int) (balance/stockPrice);
		purchasePrice = stockPrice;
		balance -= sharesHeld*stockPrice;
		stockHolding = true;
		stockReport.addTrade("Bought stock at: " + stockPrice);
		System.out.println("Bought stock at: " + stockPrice);
	}
	
	private void sellStock(double stockPrice) {
		double profit = (stockPrice - purchasePrice)*sharesHeld;
		profitOrLoss += profit;
		balance += sharesHeld*stockPrice;
		stockHolding = false;
		sharesHeld = 0;
		stockReport.addTrade("Sold stock at: " + stockPrice + ", Profit: " + profit);
		System.out.println("Sold stock at: " + stockPrice + ", Profit: " + profit);
	}
	
	public double getProfitLoss() {
		return profitOrLoss;
	}
}