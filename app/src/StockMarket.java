package app.src;

import java.util.Random;

public class StockMarket extends Thread {

  private boolean runMarket = true;
  private Stock[] stocks = {
    new Stock("coke", 64.38, "LOW"),
    new Stock("google", 169.43, "HIGH"),
    new Stock("nvidia", 136.02, "MEDIUM"),
    new Stock("lenovo", 9.09, "LOW"),
    new Stock("honeywell", 230.60, "HIGH"),
  };

  private static final double HIGH_VOLATILITY_FACTOR = 0.15;
  private static final double MEDIUM_VOLATILITY_FACTOR = 0.07;
  private static final double LOW_VOLATILITY_FACTOR = 0.03;

  private static final double MAX_VOLATILITY = 0.9;
  private static final double MIN_VOLATILITY = 0.1;
  private static final double RANDOMNESS_FACTOR = 0.9;

  public StockMarket() {}

  public void end() {
    runMarket = false;
  }

  @Override
  public void run() {
    while (runMarket) {
      try {
        Thread.sleep(Main.msPerDay);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      Main.addDay();
      for (int i = 0; i < stocks.length; i++) {
        stocks[i].price =
          simulateStockPrice(stocks[i].price, stocks[i].volatility);
        stocks[i].volatility =
          adjustVolatility(stocks[i].price, stocks[i].volatility);
      }
    }
  }

  private static double noise(double x, Random random) {
    return random.nextDouble() * 2 - 1;
  }

  public static String adjustVolatility(
    double stockPrice,
    String currentVolatility
  ) {
    Random random = new Random();
    double volatility = 0;

    // Convert string volatility to a double value (0.1 to 1.0)
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
        throw new IllegalArgumentException(
          "Invalid volatility level: " + currentVolatility
        );
    }

    double priceInfluence = Math.min(1, stockPrice / 1000); // Scale so it won't blow up for large prices

    double randomAdjustment =
      (random.nextDouble() - 0.5) * RANDOMNESS_FACTOR * priceInfluence;

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

  public static double simulateStockPrice(
    double previousPrice,
    String riskLevel
  ) {
    Random random = new Random();
    double volatilityFactor;
    double noiseValue;

    switch (riskLevel.toUpperCase()) {
      case "HIGH":
        volatilityFactor = HIGH_VOLATILITY_FACTOR;
        break;
      case "MEDIUM":
        volatilityFactor = MEDIUM_VOLATILITY_FACTOR;
        break;
      case "LOW":
        volatilityFactor = LOW_VOLATILITY_FACTOR;
        break;
      default:
        throw new IllegalArgumentException("Invalid risk level: " + riskLevel);
    }

    //Introduce a general upward trend (adjust the 0.002 factor to control trend strength)
    double trendFactor = 0.002 * previousPrice;

    noiseValue = noise(random.nextDouble() * 100, random);

    double priceChange =
      (trendFactor + volatilityFactor * previousPrice * noiseValue);

    return Math.max(0, previousPrice + priceChange);
  }

  /**
   * Returns a String array of company names available for stocks.
   * @return The String array of company names.
   */
  public String[] getCompanies() {
    String[] companies = { "", "", "", "", "" };
    for (int i = 0; i < stocks.length; i++) {
      companies[i] = stocks[i].company;
    }
    return companies;
  }

  /**
   * Returns the double of a requested company's stock price.
   * @param company Company name in lowercase
   * @return The price of the requested company's stock
   */
  public double getCurrentStock(String company) {
    int index = -1;
    double price = 0;
    for (int i = 0; i < stocks.length; i++) {
      if (stocks[i].company.equals(company.toLowerCase())) {
        index = i;
        break;
      }
    }
    try {
      price = Math.round(stocks[index].price * 100.0) / 100.0;
    } catch (Exception e) {
      System.err.println(
        e.getMessage() + ": Most likely a type in company name."
      );
    }
    return price;
  }

  /**
   * Returns the string of a requested company's volatile level.
   * @param company Company name in lowercase
   * @return The volatile level of the requested company
   */
  public String getVolatileLevel(String company) {
    int index = -1;
    String volatileLevel = "";
    for (int i = 0; i < stocks.length; i++) {
      if (stocks[i].company.equals(company.toLowerCase())) {
        index = i;
        break;
      }
    }
    try {
      volatileLevel = stocks[index].volatility;
    } catch (Exception e) {
      System.err.println(
        e.getMessage() + ": Most likely a type in company name."
      );
    }
    return volatileLevel;
  }

  private static class Stock {

    String company;
    double price;
    String volatility;

    public Stock(String company, double price, String volatility) {
      this.company = company;
      this.price = price;
      this.volatility = volatility;
    }
  }
}
