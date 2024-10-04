package com.example.stockup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockReport {
	private final List<String> report = new ArrayList<>();
	
	public void addTrade(String trade) {
		report.add(trade);
	}
	
	public void printTradeHistory() {
		System.out.println("Trade History:");
		report.forEach(System.out::println);
	}
}