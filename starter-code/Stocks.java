public class Stocks extends Thread {

  private String[] stockCompanies = {
    "coca-cola",
    "google",
    "nvidia",
    "lenovo",
    "honeywell",
  };
  private double[] stockPrices = { 64.38, 169.43, 136.02, 9.09, 230.60 };

  public Stocks() {}

  @Override
  public void run() {
    while (true) {
      for (int i = 0; i < stockCompanies.length; i++) {
        double priceChange = 1;
        if (Math.random() >= 0.5) {
          priceChange = +Math.random() * 0.025;
        } else {
          priceChange = +Math.random() * -0.025;
        }
        stockPrices[i] += stockPrices[i] * priceChange;
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Returns a String array of company names available for stocks.
   * @return The String array of company names.
   */
  public String[] getCompanies() {
    return stockCompanies;
  }

  /**
   * Returns the double of a requested company's stock price.
   * @param company Company name in lowercase
   * @return The price of the requested company's stock
   */
  public double getCurrentStock(String company) {
    int index = -1;
    for (int i = 0; i < stockCompanies.length; i++) {
      if (stockCompanies[i].equals(company)) {
        index = i;
        break;
      }
    }
    return stockPrices[index];
  }
}
