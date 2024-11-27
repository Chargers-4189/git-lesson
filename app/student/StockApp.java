package app.student;

import app.src.Main;
import app.src.StockMarket;

public class StockApp extends Thread {

  private final StockMarket stockMarket;
  private static volatile boolean runApp = true;

  public StockApp(StockMarket stockMarket) {
    this.stockMarket = stockMarket;
  }

  public void end() {
    runApp = false;
  }

  @Override
  public void run() {
    // Your Code Here
    //Coca-Cola	Google	Nvidia	Lenovo	Honey Well

    while (runApp) {
      try {
        Thread.sleep(Main.msPerDay);
      } catch (Exception e) {
        e.printStackTrace();
      }

      String company = "Coke";

      double price = stockMarket.getCurrentStock(company);
      String volatileLvl = stockMarket.getVolatileLevel(company);
      /*System.out.println(
        company +
        " | Volatile Level: " +
        volatileLvl +
        " | Stock Price: " +
        price
      );*/
      System.out.println(price);
    }
  }
}
