package app.src;

import app.student.StockApp;

public class Main {

  public static final int msPerDay = 10;
  private static final int demoDays = 400;

  private static int day = 0;

  private static final StockMarket market = new StockMarket();
  private static final StockApp stockApp = new StockApp(market);

  public static void main(String[] args) {
    //System.out.println("|----- MARKET OPEN | APP STARTED -----|");
    market.start();
    stockApp.start();
    while (true) {
      synchronized (Main.class) {
        if (day >= demoDays) {
          market.end();
          stockApp.end();
          //System.out.println("|----- MARKET CLOSED | APP STOPPED -----|");
          break;
        }
      }
    }
  }

  public static synchronized void addDay() {
    day++;
  }
}
