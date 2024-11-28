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
    Main.setMsPerDay(50);
    Main.setMaxOpenDays(365);
    stockMarket.setRandomSeed(948573627495667526L);
  }

  public void end() {
    runApp = false;
  }

  @Override
  public void run() {
    while (runApp) {
      try {
        Thread.sleep(Main.msPerDay);
      } catch (Exception e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
      // Your Code Here
      // Available Companies: coke walmart google nvidia microsoft honeywell

      String company = "honeywell";
      double price = stockMarket.getStockPrice(company);

      if (Main.getDay() > 0 && Main.getDay() < 50 && account.getBalance() > price) {
        stockMarket.buyShares(1, company);
        System.out.println("Account Balance: " + account.getBalance());
      }
      if (Main.getDay() >= 60 && stockMarket.getShares(company) > 0) {
        stockMarket.sellShares(1, company);
        System.out.println("Account Balance: " + account.getBalance());
      }
      // System.out.println(
      // company +
      // " | Volatile Level: " +
      // volatileLvl +
      // " | Stock Price: " +
      // price);

    }
  }
}
