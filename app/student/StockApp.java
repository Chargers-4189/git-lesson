package app.student;

import java.util.ArrayList;
import java.util.Collections;

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
      //volititity variables
       String CokeColaVolatility = "low";
       String WalmartVolatility = "medium";
       String GoogleVolatility = "high";
       String NvidiaVolatility = "medium";
       String MicrosoftVolatility = "high";
       String HoneywellVolatility = "high";
      //Averageing Code

        ArrayList<Double> stocksListCokeCola = new ArrayList<Double>();
        ArrayList<Double> stocksListGoogle = new ArrayList<Double>();
        ArrayList<Double> stocksListNvidia = new ArrayList<Double>();
        ArrayList<Double> stocksListWalmart = new ArrayList<Double>();
        ArrayList<Double> stocksListHoneywell = new ArrayList<Double>();
        ArrayList<Double> stocksListMicrosoft = new ArrayList<Double>();

        Double AverageCokeCola = 0.0;
        Double AverageGoogle = 0.0;
        Double AverageNvidia = 0.0;
        Double AverageWalmart = 0.0;
        Double AverageHoneywell = 0.0;
        Double AverageMicrosoft = 0.0;

        double SumAverageCokeCola = 0.0;
        double SumAverageGoogle = 0.0;
        double SumAverageWalmart = 0.0;
        double SumAverageNvidia = 0.0;
        double SumAverageHoneywell = 0.0;
        double SumAverageMicrosoft = 0.0;

        int dataAmount = 20;
            //variable containing how many stocks have been taken away from the data to make it more recent
            int taken = 0;
            //Vairbale counting how many days have passed
            int daysPassed = 0;
            //getting stocks
            stocksListCokeCola.add(stockMarket.getStockPrice("Coke-Cola"));
            stocksListGoogle.add(stockMarket.getStockPrice("Google"));
            stocksListNvidia.add(stockMarket.getStockPrice("Nvidia"));
            stocksListWalmart.add(stockMarket.getStockPrice("Walmart"));
            stocksListHoneywell.add(stockMarket.getStockPrice("Coke-Cola"));
            stocksListMicrosoft.add(stockMarket.getStockPrice("Microsoft"));

            //System.out.println(stocksListGoogle.get(i));

            SumAverageCokeCola = SumAverageCokeCola + stocksListCokeCola.get(daysPassed);
            SumAverageGoogle = SumAverageGoogle + stocksListGoogle.get(daysPassed);
            SumAverageNvidia = SumAverageNvidia + stocksListNvidia.get(daysPassed);
            SumAverageWalmart = SumAverageWalmart + stocksListWalmart.get(daysPassed);
            SumAverageHoneywell = SumAverageHoneywell + stocksListHoneywell.get(daysPassed);
            SumAverageMicrosoft = SumAverageMicrosoft + stocksListMicrosoft.get(daysPassed);\
            
            //Taking out old data
            if(daysPassed >= dataAmount){
              stocksListCokeCola.remove(0);
              stocksListGoogle.remove(0);
              stocksListNvidia.remove(0);
              stocksListWalmart.remove(0);
              stocksListHoneywell.remove(0);
              stocksListMicrosoft.remove(0);
            }

            //dividing to create averages
            AverageCokeCola = AverageCokeCola / dataAmount;
            AverageGoogle = AverageGoogle / dataAmount;
            AverageNvidia = AverageNvidia / dataAmount;
            AverageWalmart = AverageWalmart / dataAmount;
            AverageHoneywell = AverageHoneywell / dataAmount;
            AverageMicrosoft = AverageMicrosoft / dataAmount;
            
            //Finding minimus and maximums for each company
            double HighCokeCola = Collections.max(stocksListCokeCola);
            double LowCokeCola = Collections.min(stocksListCokeCola);
            double HighGoogle = Collections.max(stocksListGoogle);
            double LowGoogle = Collections.min(stocksListGoogle);
            double HighNvidia = Collections.max(stocksListNvidia);
            double LowNvidia = Collections.min(stocksListNvidia);
            double HighWalmart = Collections.max(stocksListWalmart);
            double LowWalmart = Collections.min(stocksListWalmart);
            double HighHoneywell = Collections.max(stocksListHoneywell);
            double LowHoneywell = Collections.min(stocksListHoneywell);
            double HighMicrosoft = Collections.max(stocksListMicrosoft);
            double LowMicrosoft = Collections.min(stocksListMicrosoft);
            
            //averageing average of stocks to lowest or highest stock to create these variables
            double StartLowCokeCola = (AverageCokeCola + LowCokeCola) / 2;
            double StartHighCokeCola = (AverageCokeCola + HighCokeCola) / 2;
            double StartLowGoogle = (AverageGoogle + LowGoogle) / 2;
            double StartHighGoogle = (AverageGoogle + HighGoogle) / 2;
            double StartLowNvidia = (AverageNvidia + LowNvidia) / 2;
            double StartHighNvidia = (AverageNvidia + HighNvidia) / 2;
            double StartLowWalmart = (AverageWalmart + LowWalmart) / 2;
            double StartHighWalmart = (AverageWalmart + HighWalmart) / 2;
            double StartLowHoneywell = (AverageHoneywell + LowHoneywell) / 2;
            double StartHighHoneywell = (AverageHoneywell + HighHoneywell) / 2;
            double StartLowMicrosoft = (AverageMicrosoft + LowMicrosoft) / 2;
            double StartHighMicrosoft = (AverageMicrosoft + HighMicrosoft) / 2;

            //rounding to nearest hundredth
            StartLowCokeCola = Math.round(StartLowCokeCola * 100.0) / 100.0;
            StartHighCokeCola = Math.round(StartHighCokeCola * 100.0) / 100.0;
            StartLowGoogle = Math.round(StartLowGoogle * 100.0) / 100.0;
            StartHighGoogle = Math.round(StartHighGoogle * 100.0) / 100.0;
            StartLowNvidia = Math.round(StartLowNvidia * 100.0) / 100.0;
            StartHighNvidia = Math.round(StartHighNvidia * 100.0) / 100.0;
            StartLowWalmart = Math.round(StartLowWalmart * 100.0) / 100.0;
            StartHighWalmart = Math.round(StartHighWalmart * 100.0) / 100.0;
            StartLowHoneywell = Math.round(StartLowHoneywell * 100.0) / 100.0;
            StartHighHoneywell = Math.round(StartHighHoneywell * 100.0) / 100.0;
            StartLowMicrosoft = Math.round(StartLowMicrosoft * 100.0) / 100.0;
            StartHighMicrosoft = Math.round(StartHighMicrosoft * 100.0) / 100.0;
           


            

      // Available Companies: coke walmart google nvidia microsoft honeywell
      //Caleb made the code below, I didn't do anything to this

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
