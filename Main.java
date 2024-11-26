import java.util.ArrayList;

import java.util.Collections;

public class Main {

    public static Stocks stocks = new Stocks();
    public static void main(String[] args) {
        double bankAccount = 10000.00;
        int cokeStock = 0;
        int googleStock = 0;
        int nvidiaStock = 0;
        int lenovoStock = 0;
        int honeywellStock = 0;


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
        double SumAverageCokeCola = 0.0;
        double SumAverageGoogle = 0.0;
        double SumAverageLenovo = 0.0;
        double SumAverageNvidia = 0.0;
        double SumAverageHoneywell = 0.0;
        int year = 365;
        for (int i = 0; i < year; i++) {
            try{
                //wait a day
            Thread.sleep(1000);
            }
            catch(Exception e){
                System.out.println("Failed to wait a day");
            }
            //getting stocks
            stocksListCokeCola.add(stocks.getCurrentStock("Coke-Cola"));
            stocksListGoogle.add(stocks.getCurrentStock("Google"));
            stocksListNvidia.add(stocks.getCurrentStock("Nvidia"));
            stocksListLenovo.add(stocks.getCurrentStock("Lenovo"));
            stocksListHoneywell.add(stocks.getCurrentStock("Honeywell"));

            //System.out.println(stocksListGoogle.get(i));

            AverageGoogle = AverageGoogle + stocksListGoogle.get(i);
            SumAverageCokeCola = SumAverageCokeCola + stocksListCokeCola.get(i);
            SumAverageGoogle = SumAverageGoogle + stocksListGoogle.get(i);
            SumAverageNvidia = SumAverageNvidia + stocksListNvidia.get(i);
            SumAverageLenovo = SumAverageLenovo + stocksListLenovo.get(i);
            SumAverageHoneywell = SumAverageHoneywell + stocksListHoneywell.get(i);

          }
            //dividing to create averages
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
            //averageing average of stocks to lowest or highest stock to create these variables
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

            //rounding to nearest hundredth
            StartLowCokeCola = Math.round(StartLowCokeCola * 100.0) / 100.0;
            StartHighCokeCola = Math.round(StartHighCokeCola * 100.0) / 100.0;
            StartLowGoogle = Math.round(StartLowGoogle * 100.0) / 100.0;
            StartHighGoogle = Math.round(StartHighGoogle * 100.0) / 100.0;
            StartLowNvidia = Math.round(StartLowNvidia * 100.0) / 100.0;
            StartHighNvidia = Math.round(StartHighNvidia * 100.0) / 100.0;
            StartLowLenovo = Math.round(StartLowLenovo * 100.0) / 100.0;
            StartHighLenovo = Math.round(StartHighLenovo * 100.0) / 100.0;
            StartLowHoneywell = Math.round(StartLowHoneywell * 100.0) / 100.0;
            StartHighHoneywell = Math.round(StartHighHoneywell * 100.0) / 100.0;
           
        while(true) {
            int i = 0;
            //google
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Google
            // top if statement sells stock if google stock is at or above StartHighGoogle value and the amount of shares that is owned is above 0  
            if(stocks.getCurrentStock("Google") >= StartHighGoogle && googleStock > 0) {
                bankAccount += stocks.getCurrentStock("Google");
                googleStock -= 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Google Stock (sell)::"+ googleStock);
            // bottom if statement buys shares if share value is below StartLowGoogle and bank account is higher than share value
            }else if(stocks.getCurrentStock("Google") <= StartLowGoogle && bankAccount > stocks.getCurrentStock("Google") && bankAccount > 1000) {
                bankAccount -= stocks.getCurrentStock("Google");
                googleStock += 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Google Stock (buy)::"+ googleStock);
            }

            //Nvidia
            if(stocks.getCurrentStock("Nvidia") >= StartHighNvidia && nvidiaStock > 0) {
                bankAccount += stocks.getCurrentStock("Nvidia");
                nvidiaStock -= 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Nvidia Stock (sell)::"+ nvidiaStock);
            // bottom if statement buys shares if share value is below StartHighNvidia and bank account is higher than share value
            }else if(stocks.getCurrentStock("Nvidia") <= StartLowNvidia && bankAccount > stocks.getCurrentStock("Nvidia") && bankAccount > 1000) {
                bankAccount -= stocks.getCurrentStock("Nvidia");
                nvidiaStock += 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Nvidia Stock (buy)::"+ nvidiaStock);
            }

            //Coke
            if(stocks.getCurrentStock("Coke-Cola") >= StartHighCokeCola && cokeStock > 0) {
                bankAccount += stocks.getCurrentStock("Coke-Cola");
                cokeStock -= 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Coke Stock (sell)::"+ cokeStock);
            // bottom if statement buys shares if share value is below StartHighNvidia and bank account is higher than share value
            }else if(stocks.getCurrentStock("Coke-Cola") <= StartLowCokeCola && bankAccount > stocks.getCurrentStock("Coke-Cola") && bankAccount > 1000) {
                bankAccount -= stocks.getCurrentStock("Coke-Cola");
                cokeStock += 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Coke Stock (buy)::"+ cokeStock);
            }

            //Lenovo
            if(stocks.getCurrentStock("Lenovo") >= StartHighLenovo && lenovoStock > 0) {
                bankAccount += stocks.getCurrentStock("Lenovo");
                lenovoStock -= 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Lenovo Stock (sell)::"+ lenovoStock);
            // bottom if statement buys shares if share value is below StartHighNvidia and bank account is higher than share value
            }else if(stocks.getCurrentStock("Lenovo") <= StartLowLenovo && bankAccount > stocks.getCurrentStock("Lenovo") && bankAccount > 1000) {
                bankAccount -= stocks.getCurrentStock("Lenovo");
                lenovoStock += 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Lenovo Stock (buy)::"+ lenovoStock);
            }

            //Honeywell
            if(stocks.getCurrentStock("Honeywell") >= StartHighHoneywell && honeywellStock > 0) {
                bankAccount += stocks.getCurrentStock("Honeywell");
                cokeStock -= 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Honeywell Stock (sell)::"+ honeywellStock);
            // bottom if statement buys shares if share value is below StartHighNvidia and bank account is higher than share value
            }else if(stocks.getCurrentStock("Honeywell") <= StartLowHoneywell && bankAccount > stocks.getCurrentStock("Honeywell") && bankAccount > 1000) {
                bankAccount -= stocks.getCurrentStock("Honeywell");
                honeywellStock += 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Honeywell Stock (buy)::"+ honeywellStock);
            }
            //getting stocks
            stocksListCokeCola.add(stocks.getCurrentStock("Coke-Cola"));
            stocksListGoogle.add(stocks.getCurrentStock("Google"));
            stocksListNvidia.add(stocks.getCurrentStock("Nvidia"));
            stocksListLenovo.add(stocks.getCurrentStock("Lenovo"));
            stocksListHoneywell.add(stocks.getCurrentStock("Honeywell"));

            //adding stocks to array

            SumAverageCokeCola = SumAverageCokeCola + stocksListCokeCola.get(i + year);
            SumAverageGoogle = SumAverageGoogle + stocksListGoogle.get(i + year);
            SumAverageNvidia = SumAverageNvidia + stocksListNvidia.get(i + year);
            SumAverageLenovo = SumAverageLenovo + stocksListLenovo.get(i + year);
            SumAverageHoneywell = SumAverageHoneywell + stocksListHoneywell.get(i + year);

          
            //dividing to create averages
            AverageCokeCola = SumAverageCokeCola / stocksListCokeCola.size();
            AverageGoogle = SumAverageGoogle / stocksListGoogle.size();
            AverageNvidia = SumAverageNvidia / stocksListNvidia.size();
            AverageLenovo = SumAverageLenovo / stocksListLenovo.size();
            AverageHoneywell = SumAverageHoneywell / stocksListHoneywell.size();
            //finding minimums and maximums of each company

            HighCokeCola = Collections.max(stocksListCokeCola);
            LowCokeCola = Collections.min(stocksListCokeCola);
            HighGoogle = Collections.max(stocksListGoogle);
            LowGoogle = Collections.min(stocksListGoogle);
            HighNvidia = Collections.max(stocksListNvidia);
            LowNvidia = Collections.min(stocksListNvidia);
            HighLenovo = Collections.max(stocksListLenovo);
            LowLenovo = Collections.min(stocksListLenovo);
            HighHoneywell = Collections.max(stocksListHoneywell);
            LowHoneywell = Collections.min(stocksListHoneywell);
            //averageing average of stocks to lowest or highest stock to create these variables
            StartLowCokeCola = (AverageCokeCola + LowCokeCola) / 2;
            StartHighCokeCola = (AverageCokeCola + HighCokeCola) / 2;
            StartLowGoogle = (AverageGoogle + LowGoogle) / 2;
            StartHighGoogle = (AverageGoogle + HighGoogle) / 2;
            StartLowNvidia = (AverageNvidia + LowNvidia) / 2;
            StartHighNvidia = (AverageNvidia + HighNvidia) / 2;
            StartLowLenovo = (AverageLenovo + LowLenovo) / 2;
            StartHighLenovo = (AverageLenovo + HighLenovo) / 2;
            StartLowHoneywell = (AverageHoneywell + LowHoneywell) / 2;
            StartHighHoneywell = (AverageHoneywell + HighHoneywell) / 2;

            //rounding to nearest hundredth
            StartLowCokeCola = Math.round(StartLowCokeCola * 100.0) / 100.0;
            StartHighCokeCola = Math.round(StartHighCokeCola * 100.0) / 100.0;
            StartLowGoogle = Math.round(StartLowGoogle * 100.0) / 100.0;
            StartHighGoogle = Math.round(StartHighGoogle * 100.0) / 100.0;
            StartLowNvidia = Math.round(StartLowNvidia * 100.0) / 100.0;
            StartHighNvidia = Math.round(StartHighNvidia * 100.0) / 100.0;
            StartLowLenovo = Math.round(StartLowLenovo * 100.0) / 100.0;
            StartHighLenovo = Math.round(StartHighLenovo * 100.0) / 100.0;
            StartLowHoneywell = Math.round(StartLowHoneywell * 100.0) / 100.0;
            StartHighHoneywell = Math.round(StartHighHoneywell * 100.0) / 100.0;

            i++;
            System.out.println("Net worth::" + (honeywellStock * stocks.getCurrentStock("Honeywell")  + lenovoStock * stocks.getCurrentStock("Lenovo") + googleStock * stocks.getCurrentStock("Google") + nvidiaStock * stocks.getCurrentStock("Nvidia") + cokeStock * stocks.getCurrentStock("Coke-Cola") + bankAccount) );
            if(bankAccount >= 10300){
                break;
            }
        }

    }
}