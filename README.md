# Probo-StockUp

### Overview

This is a backend service developed in Java using Spring Boot to simulate a simple stock trading bot. The bot executes
trades based on predefined rules and tracks the profit/loss and trading history over time.

### Key Features:

1. Simulated Stock Market: The bot fetches stock price changes via a mock service that generates random fluctuations.
2. Trading Strategy: The bot follows a simple strategy:
   Buy if the stock price drops by 2% or more.
   Sell if the stock price rises by 3% or more.
3. Trade History: Logs all buy/sell transactions for future reference.
4. Profit/Loss Tracking: Tracks the overall profit or loss made by the bot through trading activities.

### Usage Instructions

1. Installation:

- Clone the repository from GitHub.
- Open the project in your IDE.
- Ensure that you have the necessary dependencies (Spring Boot, Lombok, etc.) in your application.properties

2. Running the Application:

- Run the main class StockUpApplication.java.
- The bot will simulate stock price changes and execute trades based on the defined strategy.
- After the simulation is complete, the final trade history, balance, and profit/loss will be printed to the console.