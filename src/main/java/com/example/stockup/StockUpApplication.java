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
			double[] stockPrices = {101, 99, 98, 97, 102, 105, 106, 108, 107, 104};
			for (int i = 0; i < 10; i++) {
				double stockPrice = stockMarket.getCurrentPrice();
				System.out.println("Current Stock Price: " + stockPrice);
				myStockBots.updateStockPrice(stockPrice);
				
				Thread.sleep(500);
			}
			stockReport.printTradeHistory();
			System.out.println("Final Balance: " + myStockBots.getBalance());
			System.out.println("Total Profit/Loss: " + myStockBots.getProfitLoss());
		};
	}
}