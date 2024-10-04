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
		return (purchasePrice - stockPrice)/purchasePrice >= bestBuyPercent;
	}
	
	private boolean OurSellTime(double stockPrice) {
		return (stockPrice - purchasePrice)/purchasePrice >= bestSellPercent;
	}
	
	private void buyStock(double stockPrice) {
		purchasePrice = stockPrice;
		stockHolding = true;
		stockReport.addTrade("Bought stock at: " + stockPrice);
		System.out.println("Bought stock at: " + stockPrice);
	}
	
	private void sellStock(double stockPrice) {
		double profit = stockPrice - purchasePrice;
		profitOrLoss += profit;
		balance += profit;
		stockHolding = false;
		stockReport.addTrade("Sold stock at: " + stockPrice + ", Profit: " + profit);
		System.out.println("Sold stock at: " + stockPrice + ", Profit: " + profit);
	}
	
	public double getProfitLoss() {
		return profitOrLoss;
	}
}