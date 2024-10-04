package com.example.stockup;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StockUpApplication {
	public static void main(String[] args) {
		SpringApplication.run(StockUpApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run(ApplicationContext appContext) {
		return args -> {
			StockMarket stockMarket = appContext.getBean(StockMarket.class);
			MyStockBots myStockBots = appContext.getBean(MyStockBots.class);
			StockReport stockReport = appContext.getBean(StockReport.class);
			
			// Simulate stock price changes and trading decisions
			for (int i = 0; i < 10; i++) {
				double stockPrice = stockMarket.getCurrentPrice();
				System.out.println("Current Stock Price: " + stockPrice);
				myStockBots.updateStockPrice(stockPrice);
				
				// Delay to simulate time intervals (1 second)
				Thread.sleep(1000);
			}
			
			// After simulation, print trade history
			stockReport.printTradeHistory();
			
			// Print final profit/loss and balance
			System.out.println("Final Balance: " + myStockBots.getBalance());
			System.out.println("Total Profit/Loss: " + myStockBots.getProfitLoss());
		};
	}
}