package app.src;

import app.student.StockApp;

public class Main {

  public static volatile boolean end = false;
  private static int msPerDay = 50;
  private static int openDays = 365;
  private static int day = 0;

  @SuppressWarnings("unused")
  private static final Logger log = new Logger(Logger.LogLevel.INFO);

  private static final Account account = new Account(Constants.INITIAL_BALANCE);
  private static final StockMarket market = new StockMarket(account);
  private static final StockApp stockApp = new StockApp(market, account);

  public static void main(String[] args) {
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
    System.out
        .println(account.getBalance() >= 5000 ? Constants.ANSI_GREEN + "Reached target goal!" + Constants.ANSI_RESET
            : Constants.ANSI_RED + "Failed to reach target goal." + Constants.ANSI_RESET);
    System.out
        .println(account.getBalance() >= 8000 ? Constants.ANSI_GREEN + "Reached bonus goal!" + Constants.ANSI_RESET
            : Constants.ANSI_RED + "Failed to reach bonus goal." + Constants.ANSI_RESET);
  }

  /**
   * Increments the stock market day counter. This method is intended for internal
   * use within the {@code StockMarket} class.
   */
  public static synchronized void addDay() {
    day++;
  }

  /**
   * Returns the current day of the stock market.
   *
   * @return The number of days since the market opened.
   */
  public static synchronized int getDay() {
    return day;
  }

  /**
   * Sets the maximum number of days the stock market will be open for in the
   * simulation.
   * <p>
   * Configure this to lower values for quick testing.
   *
   * @param days The maximum number of days (must be a positive, non-zero
   *             integer).
   * @throws IllegalArgumentException if days is not positive.
   */
  public static void setMaxOpenDays(int days) {
    openDays = days;
  }

  /**
   * Sets the time step (in milliseconds) between cycles. Each cycle represents
   * one day.
   * <p>
   * It is recommended to set the value above 50 to prevent calculation bugs when
   * running the application rapidly. Changing the cycle may also change stock
   * price results.
   *
   * @param milliseconds The time step in milliseconds (must be a positive,
   *                     non-zero integer).
   * @throws IllegalArgumentException if milliseconds is not positive.
   */
  public static void setMsPerDay(int milliseconds) {
    msPerDay = Math.abs(milliseconds);
  }

  /**
   * Returns the currently configured time step (in milliseconds) between cycles.
   *
   * @return The time step in milliseconds.
   */
  public static synchronized int getMsPerDay() {
    return msPerDay;
  }
}
