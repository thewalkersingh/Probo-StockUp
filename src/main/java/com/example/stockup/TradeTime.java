package com.example.stockup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TradeTime {
	@Autowired
	private Market market;
	@Autowired
	private MyBots myBots;
	
	// we will run this method every 5 seconds
	@Scheduled(fixedRate = 5000)
	public void currentStatus() {
		double stockPrice = market.getCurrentPrice();
		System.out.println("Current Stock Price: " + stockPrice);
		myBots.updateStockPrice(stockPrice);
		
		// Output current balance and profit/loss after each run
		System.out.println("Current Balance: " + myBots.getBalance());
		System.out.println("Current Profit/Loss: " + myBots.getProfitLoss());
	}
}