package app.src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import static app.src.Constants.HIGH_VOLATILITY_FACTOR;
import static app.src.Constants.MEDIUM_VOLATILITY_FACTOR;
import static app.src.Constants.LOW_VOLATILITY_FACTOR;
import static app.src.Constants.MAX_VOLATILITY;
import static app.src.Constants.MIN_VOLATILITY;
import static app.src.Constants.RANDOMNESS_FACTOR;

public class StockMarket extends Thread {

  private final Account account;

  private volatile boolean runMarket = true;
  private final Stock[] stocks;

  private long seed;
  private Random randomGenerator;

  public StockMarket(Account account) {
    this.account = account;
    stocks = new Stock[] {
        new Stock("coke", 64.38, "LOW", 0),
        new Stock("walmart", 91.88, "MEDIUM", 0),
        new Stock("google", 169.43, "HIGH", 0),
        new Stock("nvidia", 136.02, "MEDIUM", 0),
        new Stock("microsoft", 422.9, "HIGH", 0),
        new Stock("honeywell", 230.60, "HIGH", 0),
    };
  }

  public void end() {
    runMarket = false;
  }

  @Override
  public void run() {
    while (runMarket) {
      try {
        Thread.sleep(Main.getMsPerDay());
        Main.addDay();
        updateStocks();
      } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
      if (Logger.getLogSeverity() == Logger.LogLevel.DEBUG) {
        logStockMarket();
      }
    }
  }

  /**
   * Returns an array of company names whose stocks are available for trading.
   *
   * @return An array of company names (Strings). Returns an empty array if there
   *         are no stocks.
   */
  public String[] getCompanies() {
    String[] companies = { "", "", "", "", "", "" };
    for (int i = 0; i < stocks.length; i++) {
      companies[i] = stocks[i].company;
    }
    return companies;
  }

  /**
   * Returns the current price of a specified company's stock.
   *
   * @param company The name of the company (case-insensitive).
   * @return The stock price (double). Returns -1 if the company is not found.
   */
  public synchronized double getStockPrice(String company) {
    for (Stock stock : stocks) {
      if (stock.company.equals(company.toLowerCase())) {
        return stock.price;
      }
    }
    Logger.logEvent(Logger.LogLevel.ERROR, Constants.M_SOURCE, Constants.ERR_M_COMP_NAME + company);
    return -1;
  }

  /**
   * Returns the volatility level of a specified company's stock.
   *
   * @param company The name of the company (case-insensitive).
   * @return The volatility level (String). Returns an empty string if the company
   *         is not found.
   */
  public synchronized String getVolatileLevel(String company) {
    for (Stock stock : stocks) {
      if (stock.company.equals(company.toLowerCase())) {
        return stock.volatility;
      }
    }
    Logger.logEvent(Logger.LogLevel.ERROR, Constants.M_SOURCE, Constants.ERR_M_COMP_NAME + company);
    return "";
  }

  /**
   * Returns the number of shares owned of a specified company's stock.
   *
   * @param company The name of the company (case-insensitive).
   * @return The number of shares owned (int). Returns -1 if the company is not
   *         found.
   */
  public synchronized int getShares(String company) {
    for (Stock stock : stocks) {
      if (stock.company.equals(company.toLowerCase())) {
        return stock.shares;
      }
    }
    Logger.logEvent(Logger.LogLevel.ERROR, Constants.M_SOURCE, Constants.ERR_M_COMP_NAME + company);
    return -1;
  }

  /**
   * Purchases shares of a specified company's stock.
   * <p>
   * Cannot purchase shares if the account balance is below the price of `stock
   * price * amount of shares`. Buying shares will slightly increase the stock
   * price. This method will automatically charge the account the money due.
   *
   * @param amount  The number of shares to purchase (must be a positive, non-zero
   *                value).
   * @param company The name of the company (case-insensitive).
   * @return True if the purchase was successful, false otherwise. Returns false
   *         if the company is not found, insufficient funds are available, or the
   *         amount is invalid.
   */
  public synchronized boolean buyShares(int amount, String company) {
    amount = Math.abs(amount);
    for (Stock stock : stocks) {
      if (stock.company.equals(company.toLowerCase())) {
        double funds = stock.price * amount;
        if (account.withdraw(funds, "Purchased " + amount + " shares from " + company + ".")) {
          stock.shares += amount;
          for (int i = amount; i > 0; i--) {
            double priceChange = (Math
                .abs(randomGenerator.nextGaussian()) * getVolatileFactor(stock.volatility) * Constants.shareFactor
                * stock.price);
            stock.price = stock.price + priceChange;
            Logger.logEvent(Logger.LogLevel.DEBUG, Constants.M_SOURCE,
                "Share bought from " + Constants.ANSI_CYAN + stock.company + Constants.ANSI_RESET + "."
                    + Constants.ANSI_RED + " Cost: $"
                    + Math.round(funds * 100.0) / 100.0
                    + " | Balance: $"
                    + Math.round(account.getBalance() * 100.0) / 100.0
                    + " | Stock Price Now: $" + Math.round(stock.price * 100.0)
                        / 100.0
                    + Constants.ANSI_RESET);
          }
          return true;
        }
        Logger.logEvent(Logger.LogLevel.WARN, Constants.M_SOURCE, Constants.ERR_M_BUY_SHARE);
        return false;
      }
    }
    Logger.logEvent(Logger.LogLevel.ERROR, Constants.M_SOURCE, Constants.ERR_M_COMP_NAME + company);
    return false;
  }

  /**
   * Sells shares of a specified company's stock.
   * <P>
   * Cannot sell shares if the total shares is 0. Buying shares will slightly
   * decrease the stock price. This method will automatically pay the account the
   * money earned.
   * 
   * @param amount  The number of shares to sell (must be a positive, non-zero
   *                value).
   * @param company The name of the company (case-insensitive).
   * @return True if the sale was successful, false otherwise. Returns false if
   *         the company is not found, insufficient shares are available, or the
   *         amount is invalid.
   */
  public synchronized boolean sellShares(int amount, String company) {
    amount = Math.abs(amount);
    for (Stock stock : stocks) {
      if (stock.company.equals(company.toLowerCase())) {
        double funds = stock.price * amount;
        if (stock.shares > 0 && account.deposit(funds, "Sold " + amount + " shares from " + company + ".")) {
          stock.shares -= amount;
          for (int i = amount; i > 0; i--) {
            double priceChange = (Math
                .abs(randomGenerator.nextGaussian()) * getVolatileFactor(stock.volatility) * Constants.shareFactor
                * stock.price);
            stock.price = stock.price - priceChange;
            Logger.logEvent(Logger.LogLevel.DEBUG, Constants.M_SOURCE,
                "Share sold from " + Constants.ANSI_CYAN + stock.company + Constants.ANSI_RESET + "."
                    + Constants.ANSI_GREEN + " Profit: $"
                    + Math.round(funds * 100.0) / 100.0
                    + " | Balance: $"
                    + Math.round(account.getBalance() * 100.0) / 100.0
                    + " | Stock Price Now: $" + Math.round(stock.price * 100.0)
                        / 100.0
                    + Constants.ANSI_RESET);
          }
          return true;
        }
        Logger.logEvent(Logger.LogLevel.WARN, Constants.M_SOURCE, Constants.ERR_M_SELL_SHARE);
        return false;
      }
    }
    Logger.logEvent(Logger.LogLevel.ERROR, Constants.M_SOURCE, Constants.ERR_M_COMP_NAME + company);
    return false;
  }

  /**
   * Sets the seed for the random number generator used in the stock market
   * simulation.
   * <P>
   * Setting a seed value other than 0 will produce repeatable results
   * for the same seed value. Using a seed of 0 will use a randomly generated
   * seed.
   *
   * @param seed The seed value for the random number generator.
   */
  public void setRandomSeed(long seed) {
    randomGenerator = new Random();
    if (seed != 0) {
      this.seed = seed;
      Logger.logEvent(Logger.LogLevel.DEBUG, Constants.M_SOURCE,
          "Using set seed: " + this.seed);
    } else {
      this.seed = new Random(System.nanoTime()).nextLong();
      Logger.logEvent(Logger.LogLevel.DEBUG, Constants.M_SOURCE,
          "Generating seed: " + this.seed);
    }
    randomGenerator.setSeed(this.seed);
    Logger.logEvent(Logger.LogLevel.INFO, Constants.M_SOURCE,
        Constants.ANSI_PURPLE + "Random Seed: " + this.seed + Constants.ANSI_RESET);
  }

  private void updateStocks() {
    synchronized (stocks) {
      for (Stock stock : stocks) {
        Random random = randomGenerator;
        stock.price = simulateStockPrice(stock.price, stock.volatility, random);
        stock.volatility = adjustVolatility(stock.price, stock.volatility, random);
        Logger.logEvent(Logger.LogLevel.DEBUG, Constants.M_SOURCE,
            "Stock " + Constants.ANSI_CYAN + stock.company + Constants.ANSI_RESET + " updated. Price: "
                + Constants.ANSI_BLUE + "$" + stock.price
                + Constants.ANSI_RESET
                + " | Volatility Level: " + (stock.volatility == "HIGH" ? Constants.ANSI_RED
                    : stock.volatility == "MEDIUM" ? Constants.ANSI_YELLOW
                        : Constants.ANSI_GREEN)
                + stock.volatility + Constants.ANSI_RESET);
      }
    }
  }

  private static double getVolatileFactor(String riskLevel) {
    switch (riskLevel.toUpperCase()) {
      case "HIGH":
        return HIGH_VOLATILITY_FACTOR;
      case "MEDIUM":
        return MEDIUM_VOLATILITY_FACTOR;
      case "LOW":
        return LOW_VOLATILITY_FACTOR;
      default:
        throw new IllegalArgumentException("Invalid risk level: " + riskLevel);
    }
  }

  private synchronized static String adjustVolatility(
      double stockPrice,
      String currentVolatility,
      Random random) {
    double volatility = 0;

    switch (currentVolatility.toUpperCase()) {
      case "LOW":
        volatility = MIN_VOLATILITY;
        break;
      case "MEDIUM":
        volatility = (MAX_VOLATILITY + MIN_VOLATILITY) / 2;
        break;
      case "HIGH":
        volatility = MAX_VOLATILITY;
        break;
      default:
        throw new IllegalArgumentException("Invalid volatility level: " + currentVolatility);
    }

    double priceInfluence = Math.min(1, stockPrice / 500); // Scale so it won't blow up for large prices

    double randomAdjustment = (random.nextGaussian() - 0.5) * RANDOMNESS_FACTOR * priceInfluence;

    volatility += randomAdjustment;
    volatility = Math.max(MIN_VOLATILITY, Math.min(MAX_VOLATILITY, volatility));

    if (volatility <= (MAX_VOLATILITY + MIN_VOLATILITY) / 3) {
      return "LOW";
    } else if (volatility <= (2 * (MAX_VOLATILITY + MIN_VOLATILITY) / 3)) {
      return "MEDIUM";
    } else {
      return "HIGH";
    }
  }

  private synchronized static double simulateStockPrice(
      double previousPrice,
      String riskLevel,
      Random random) {
    double volatilityFactor = getVolatileFactor(riskLevel);
    double noiseValue;

    // Random TrendFactor
    double trendFactor = (0.008
        * (new Random(random.nextLong()).nextDouble() < 0.9 ? random.nextGaussian() * 1 : -random.nextGaussian() * 1))
        * previousPrice;

    noiseValue = random.nextDouble() * 2 - 1;

    double priceChange = (trendFactor + volatilityFactor * previousPrice * noiseValue);

    return Math.round(Math.max(0, previousPrice + priceChange) * 100.0) / 100.0;
  }

  private synchronized void logStockMarket() {
    String output = "";
    for (Stock stock : stocks) {
      output += Math.round(stock.price * 100.0) / 100.0 + "," + stock.volatility +
          ",";
    }
    output = output.substring(0, output.length() - 1);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.STOCK_FILE, true))) {
      writer.write(output);
      writer.newLine();
      if (Main.end) {
        writer.flush();
      }
    } catch (IOException e) {
      Logger.logEvent(Logger.LogLevel.ERROR, Constants.M_SOURCE,
          "Error writing stock logger to file: " + e.getMessage());
    }
  }

  private static class Stock {

    String company;
    double price;
    String volatility;
    int shares;

    public Stock(String company, double price, String volatility, int shares) {
      this.company = company;
      this.price = price;
      this.volatility = volatility;
      this.shares = shares;
    }
  }
}
