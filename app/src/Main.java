package app.src;

import app.student.StockApp;

public class Main {

  public static volatile boolean end = false;
  public static int msPerDay = 1000;
  private static int openDays = 365;
  private static int day = 0;

  private static final Account account = new Account(Constants.INITIAL_BALANCE);
  private static final StockMarket market = new StockMarket(account);
  private static final StockApp stockApp = new StockApp(market, account);

  public static void main(String[] args) {
    new Logger(Logger.LogLevel.WARN);
    System.out.println("|----- MARKET OPEN | APP STARTED -----|");
    market.start();
    stockApp.start();
    while (true) {
      synchronized (Main.class) {
        if (day >= openDays) {
          end = true;
          market.end();
          stockApp.end();
          break;
        }
      }
    }
    try {
      Thread.sleep(Main.msPerDay);
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("|----- MARKET CLOSED | APP STOPPED -----|");
    System.out.println("FINAL ACCOUNT BALANCE: $" + account.getBalance());
  }

  public static synchronized void addDay() {
    day++;
  }

  public static synchronized int getDay() {
    return day;
  }

  public static void setMaxOpenDays(int days) {
    openDays = days;
  }

  public static int getOpenDays() {
    return openDays;
  }

  public static void setMsPerDay(int milliseconds) {
    msPerDay = milliseconds;
  }
}
