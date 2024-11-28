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
        Thread.sleep(Main.msPerDay);
        Main.addDay();
        updateStocks();

        logStockMarket();

      } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
    }
  }

  /**
   * Returns a String array of company names available for stocks.
   * 
   * @return The String array of company names.
   */
  public String[] getCompanies() {
    String[] companies = { "", "", "", "", "", "" };
    for (int i = 0; i < stocks.length; i++) {
      companies[i] = stocks[i].company;
    }
    return companies;
  }

  /**
   * Returns the double of a requested company's stock price.
   * 
   * @param company Company name in lowercase
   * @return The price of the requested company's stock
   */
  public synchronized double getStockPrice(String company) {
    for (Stock stock : stocks) {
      if (stock.company.equals(company.toLowerCase())) {
        return stock.price;
      }
    }
    Logger.logEvent(Logger.LogLevel.ERROR, Constants.ERR_M_SOURCE, Constants.ERR_M_COMP_NAME);
    return 0;
  }

  /**
   * Returns the string of a requested company's volatile level.
   * 
   * @param company Company name in lowercase
   * @return The volatile level of the requested company
   */
  public synchronized String getVolatileLevel(String company) {
    for (Stock stock : stocks) {
      if (stock.company.equals(company.toLowerCase())) {
        return stock.volatility;
      }
    }
    Logger.logEvent(Logger.LogLevel.ERROR, Constants.ERR_M_SOURCE, Constants.ERR_M_COMP_NAME);
    return "";
  }

  public synchronized int getShares(String company) {
    for (Stock stock : stocks) {
      if (stock.company.equals(company.toLowerCase())) {
        return stock.shares;
      }
    }
    Logger.logEvent(Logger.LogLevel.ERROR, Constants.ERR_M_SOURCE, Constants.ERR_M_COMP_NAME);
    return 0;
  }

  public synchronized boolean buyShares(int amount, String company) {
    amount = Math.abs(amount);
    for (Stock stock : stocks) {
      if (stock.company.equals(company.toLowerCase())) {
        double funds = stock.price * amount;
        if (account.withdraw(funds)) {
          stock.shares += amount;
          for (int i = amount; i > 0; i--) {
            double priceChange = (Math
                .abs(randomGenerator.nextGaussian()) * getVolatileFactor(stock.volatility) * Constants.shareFactor
                * stock.price);
            stock.price = stock.price + priceChange;
          }
          return true;
        }
        Logger.logEvent(Logger.LogLevel.WARN, Constants.ERR_M_SOURCE, Constants.ERR_M_BUY_SHARE);
        return false;
      }
    }
    Logger.logEvent(Logger.LogLevel.ERROR, Constants.ERR_M_SOURCE, Constants.ERR_M_COMP_NAME);
    return false;
  }

  public synchronized boolean sellShares(int amount, String company) {
    amount = Math.abs(amount);
    for (Stock stock : stocks) {
      if (stock.company.equals(company.toLowerCase())) {
        if (stock.shares > 0) {
          double funds = stock.price * amount;
          account.deposit(funds);
          stock.shares -= amount;
          for (int i = amount; i > 0; i--) {
            double priceChange = (Math
                .abs(randomGenerator.nextGaussian()) * getVolatileFactor(stock.volatility) * Constants.shareFactor
                * stock.price);
            stock.price = stock.price - priceChange;
          }
          return true;
        }
        Logger.logEvent(Logger.LogLevel.WARN, Constants.ERR_M_SOURCE, Constants.ERR_M_SELL_SHARE);
        return false;
      }
    }
    Logger.logEvent(Logger.LogLevel.ERROR, Constants.ERR_M_SOURCE, Constants.ERR_M_COMP_NAME);
    return false;
  }

  public void setRandomSeed(long seed) {
    randomGenerator = new Random();
    if (seed != 0) {
      this.seed = seed;
    } else {
      this.seed = new Random(System.nanoTime()).nextLong();
    }
    randomGenerator.setSeed(this.seed);
    System.out.println(Constants.ANSI_PURPLE + "\nRandom Seed: " + this.seed +
        "L\n" + Constants.ANSI_RESET);
  }

  private void updateStocks() {
    synchronized (stocks) {
      for (Stock stock : stocks) {
        Random random = randomGenerator;
        stock.price = simulateStockPrice(stock.price, stock.volatility, random);
        stock.volatility = adjustVolatility(stock.price, stock.volatility, random);
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
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("./app/logs/stock-market.log", true))) {
      writer.write(output);
      writer.newLine();
      if (Main.end) {
        writer.flush();
      }
    } catch (IOException e) {
      Logger.logEvent(Logger.LogLevel.ERROR, Constants.ERR_M_SOURCE,
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
