import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static Stocks stocks = new Stocks();
    public static void main(String[] args) {
        stocks.start();
        ArrayList<Double> stocksListCokeCola = new ArrayList<Double>();
        ArrayList<Double> stocksListGoogle = new ArrayList<Double>();
        ArrayList<Double> stocksListNvidia = new ArrayList<Double>();
        ArrayList<Double> stocksListLenovo = new ArrayList<Double>();
        ArrayList<Double> stocksListHoneywell = new ArrayList<Double>();

        Double AverageCokeCola = 0.0;
        Double AverageGoogle = 0.0;
        Double AverageNvidia = 0.0;
        Double AverageLenovo = 0.0;
        Double AverageHoneywell = 0.0;
        int year = 365;
        for (int i = 0; i < year; i++) {
            try{
            Thread.sleep(1000);
            }
            catch(Exception e){
                System.out.println("Failed to wait a day");
            }
            stocksListCokeCola.add(stocks.getCurrentStock("Coke-Cola"));
            stocksListGoogle.add(stocks.getCurrentStock("Google"));
            stocksListNvidia.add(stocks.getCurrentStock("Nvidia"));
            stocksListLenovo.add(stocks.getCurrentStock("Lenovo"));
            stocksListHoneywell.add(stocks.getCurrentStock("Honeywell"));

            System.out.println(stocksListGoogle.get(i));

            AverageCokeCola = AverageCokeCola + stocksListGoogle.get(i);
            AverageGoogle = AverageGoogle + stocksListGoogle.get(i);
            AverageNvidia = AverageNvidia + stocksListGoogle.get(i);
            AverageLenovo = AverageLenovo + stocksListGoogle.get(i);
            AverageHoneywell = AverageHoneywell + stocksListGoogle.get(i);

          }
          
            AverageCokeCola = AverageCokeCola / year;
            AverageGoogle = AverageGoogle / year;
            AverageNvidia = AverageNvidia / year;
            AverageLenovo = AverageLenovo / year;
            AverageHoneywell = AverageHoneywell / year;

            double HighCokeCola = Collections.max(stocksListCokeCola);
            double LowCokeCola = Collections.min(stocksListCokeCola);
            double HighGoogle = Collections.max(stocksListGoogle);
            double LowGoogle = Collections.min(stocksListGoogle);
            double HighNvidia = Collections.max(stocksListNvidia);
            double LowNvidia = Collections.min(stocksListNvidia);
            double HighLenovo = Collections.max(stocksListLenovo);
            double LowLenovo = Collections.min(stocksListLenovo);
            double HighHoneywell = Collections.max(stocksListHoneywell);
            double LowHoneywell = Collections.min(stocksListHoneywell);

            double StartLowCokeCola = (AverageCokeCola + LowCokeCola) / 2;
            double StartHighCokeCola = (AverageCokeCola + HighCokeCola) / 2;
            double StartLowGoogle = (AverageGoogle + LowGoogle) / 2;
            double StartHighGoogle = (AverageGoogle + HighGoogle) / 2;
            double StartLowNvidia = (AverageNvidia + LowNvidia) / 2;
            double StartHighNvidia = (AverageNvidia + HighNvidia) / 2;
            double StartLowLenovo = (AverageLenovo + LowLenovo) / 2;
            double StartHighLenovo = (AverageLenovo + HighLenovo) / 2;
            double StartLowHoneywell = (AverageHoneywell + LowHoneywell) / 2;
            double StartHighHoneywell = (AverageHoneywell + HighHoneywell) / 2;


            System.out.println("You Sell Google At:" + StartHighGoogle);
            System.out.println("You Buy Google At:" + StartLowGoogle);
            System.out.println("Google's Average is:" + AverageGoogle);
    }
}