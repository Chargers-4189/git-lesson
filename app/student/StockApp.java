package app.student;

import app.src.Account;
import app.src.Main;
import app.src.StockMarket;

public class StockApp extends Thread {

  private final StockMarket stockMarket;
  private final Account account;
  private static volatile boolean runApp = true;

  public StockApp(StockMarket stockMarket, Account account) {
    this.stockMarket = stockMarket;
    this.account = account;

    // STOCK MARKET CONFIGURATIONS
    Main.setMsPerDay(50); // Default 50. Use lower milliseconds for quicker testing.
    Main.setMaxOpenDays(365); // Default 365. Use lower days for quicker testing.
    stockMarket.setRandomSeed(948573627495667526L); // Set Stock Market generation seed. Set to 0 for random seed.
  }

  public void end() {
    runApp = false;
  }

  @Override
  public void run() {
    while (runApp) {
      try {
        Thread.sleep(Main.getMsPerDay());
      } catch (Exception e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
      // Your Code Here
      // Available Companies: coke walmart google nvidia microsoft honeywell

      String company = "honeywell";
      double price = stockMarket.getStockPrice(company);
      String volatileLvl = stockMarket.getVolatileLevel(company);
      double balance = account.getBalance();

      System.out.println(
          company +
              " | Volatile Level: " +
              volatileLvl +
              " | Stock Price: " +
              price +
              " | Balance: " +
              balance);

    }
  }
}
