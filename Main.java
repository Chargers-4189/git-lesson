import java.util.ArrayList;

import java.util.Collections;

public class Main {

    public static Stocks stocks = new Stocks();
    public static void main(String[] args) {
        double bankAccount = 1000.00;
        int cokeStock = 0;
        int googleStock = 0;
        int nvidiaStock = 0;
        int WalmartStock = 0;
        int honeywellStock = 0;
        int MicrosoftStock = 0;


        stocks.start();
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

        int year = 15;
        for (int i = 0; i < year; i++) {
            try{
                //wait a day
            Thread.sleep(50);
            }
            catch(Exception e){
                System.out.println("Failed to wait a day");
            }
            //getting stocks
            stocksListCokeCola.add(stocks.getCurrentStock("Coke-Cola"));
            stocksListGoogle.add(stocks.getCurrentStock("Google"));
            stocksListNvidia.add(stocks.getCurrentStock("Nvidia"));
            stocksListWalmart.add(stocks.getCurrentStock("Walmart"));
            stocksListHoneywell.add(stocks.getCurrentStock("Honeywell"));
            stocksListMicrosoft.add(stocks.getCurrentStock("Microsoft"));

            //System.out.println(stocksListGoogle.get(i));

            SumAverageCokeCola = SumAverageCokeCola + stocksListCokeCola.get(i);
            SumAverageGoogle = SumAverageGoogle + stocksListGoogle.get(i);
            SumAverageNvidia = SumAverageNvidia + stocksListNvidia.get(i);
            SumAverageWalmart = SumAverageWalmart + stocksListWalmart.get(i);
            SumAverageHoneywell = SumAverageHoneywell + stocksListHoneywell.get(i);
            SumAverageMicrosoft = SumAverageMicrosoft + stocksListMicrosoft.get(i);

          }
            //dividing to create averages
            AverageCokeCola = AverageCokeCola / year;
            AverageGoogle = AverageGoogle / year;
            AverageNvidia = AverageNvidia / year;
            AverageWalmart = AverageWalmart / year;
            AverageHoneywell = AverageHoneywell / year;
            AverageMicrosoft = AverageMicrosoft / year;

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
           
        while(true) {
            int i = 0;
            //google
            try {
                Thread.sleep(50);
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

            //Walmart
            if(stocks.getCurrentStock("Walmart") >= StartHighWalmart && WalmartStock > 0) {
                bankAccount += stocks.getCurrentStock("Walmart");
                WalmartStock -= 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Walmart Stock (sell)::"+ WalmartStock);
            // bottom if statement buys shares if share value is below StartHighNvidia and bank account is higher than share value
            }else if(stocks.getCurrentStock("Walmart") <= StartLowWalmart && bankAccount > stocks.getCurrentStock("Walmart") && bankAccount > 1000) {
                bankAccount -= stocks.getCurrentStock("Walmart");
                WalmartStock += 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Walmart Stock (buy)::"+ WalmartStock);
            }

            //Honeywell
            if(stocks.getCurrentStock("Honeywell") >= StartHighHoneywell && honeywellStock > 0) {
                bankAccount += stocks.getCurrentStock("Honeywell");
                honeywellStock -= 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Honeywell Stock (sell)::"+ honeywellStock);
            // bottom if statement buys shares if share value is below StartHighNvidia and bank account is higher than share value
            }else if(stocks.getCurrentStock("Honeywell") <= StartLowHoneywell && bankAccount > stocks.getCurrentStock("Honeywell") && bankAccount > 1000) {
                bankAccount -= stocks.getCurrentStock("Honeywell");
                honeywellStock += 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Honeywell Stock (buy)::"+ honeywellStock);
            }
            
            //Microsoft
            // top if statement sells stock if google stock is at or above StartHighGoogle value and the amount of shares that is owned is above 0  
            if(stocks.getCurrentStock("Microsoft") >= StartHighMicrosoft && MicrosoftStock > 0) {
                bankAccount += stocks.getCurrentStock("Google");
                MicrosoftStock -= 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Microsoft Stock (sell)::"+ MicrosoftStock);
            // bottom if statement buys shares if share value is below StartLowGoogle and bank account is higher than share value
            }else if(stocks.getCurrentStock("Microsoft") <= StartLowMicrosoft && bankAccount > stocks.getCurrentStock("Microsoft") && bankAccount > 1000) {
                bankAccount -= stocks.getCurrentStock("Microsoft");
                MicrosoftStock += 1;
                System.out.println("Bank::" + bankAccount);
                System.out.println("Microsoft Stock (buy)::"+ MicrosoftStock);
            }

            //This variable tells how many stocks are taken away from the data. This is so we don't use really outdated data

            int taken = 1;

            // taking out old data

            stocksListCokeCola.remove(taken - 1);
            stocksListGoogle.remove(taken - 1);
            stocksListNvidia.remove(taken - 1);
            stocksListWalmart.remove(taken - 1);
            stocksListHoneywell.remove(taken - 1);
            stocksListMicrosoft.remove(taken - 1);
            
            //reseting variables to take out old data before adding new data;
            SumAverageCokeCola = 0;
            SumAverageGoogle = 0;
            SumAverageNvidia = 0;
            SumAverageWalmart = 0;
            SumAverageHoneywell = 0;
            SumAverageMicrosoft = 0;
            
            //getting sum of all stocks to later create average
            for (int rounds = 0; rounds <= stocksListCokeCola.size(); rounds++) {
                SumAverageCokeCola = SumAverageCokeCola + stocksListCokeCola.get(rounds);
                SumAverageGoogle = SumAverageGoogle + stocksListGoogle.get(rounds);
                SumAverageNvidia = SumAverageNvidia + stocksListNvidia.get(rounds);
                SumAverageWalmart = SumAverageWalmart + stocksListWalmart.get(rounds);
                SumAverageHoneywell = SumAverageHoneywell + stocksListHoneywell.get(rounds);
                SumAverageMicrosoft = SumAverageMicrosoft + stocksListMicrosoft.get(rounds);
            }

            //dividing to create averages
            AverageCokeCola = SumAverageCokeCola / (stocksListCokeCola.size() - taken);
            AverageGoogle = SumAverageGoogle / (stocksListGoogle.size() - taken);
            AverageNvidia = SumAverageNvidia / (stocksListNvidia.size() - taken);
            AverageWalmart = SumAverageWalmart / (stocksListWalmart.size() - taken);
            AverageHoneywell = SumAverageHoneywell / (stocksListHoneywell.size() - taken);
            AverageMicrosoft = SumAverageMicrosoft / (stocksListMicrosoft.size() - taken);

            //finding minimums and maximums of each company

            HighCokeCola = Collections.max(stocksListCokeCola);
            LowCokeCola = Collections.min(stocksListCokeCola);
            HighGoogle = Collections.max(stocksListGoogle);
            LowGoogle = Collections.min(stocksListGoogle);
            HighNvidia = Collections.max(stocksListNvidia);
            LowNvidia = Collections.min(stocksListNvidia);
            HighWalmart = Collections.max(stocksListWalmart);
            LowWalmart = Collections.min(stocksListWalmart);
            HighHoneywell = Collections.max(stocksListHoneywell);
            LowHoneywell = Collections.min(stocksListHoneywell);
            HighMicrosoft = Collections.max(stocksListMicrosoft);
            LowMicrosoft = Collections.min(stocksListMicrosoft);
            //averageing average of stocks to lowest or highest stock to create these variables
            StartLowCokeCola = (AverageCokeCola + LowCokeCola) / 2;
            StartHighCokeCola = (AverageCokeCola + HighCokeCola) / 2;
            StartLowGoogle = (AverageGoogle + LowGoogle) / 2;
            StartHighGoogle = (AverageGoogle + HighGoogle) / 2;
            StartLowNvidia = (AverageNvidia + LowNvidia) / 2;
            StartHighNvidia = (AverageNvidia + HighNvidia) / 2;
            StartLowWalmart = (AverageWalmart + LowWalmart) / 2;
            StartHighWalmart = (AverageWalmart + HighWalmart) / 2;
            StartLowHoneywell = (AverageHoneywell + LowHoneywell) / 2;
            StartHighHoneywell = (AverageHoneywell + HighHoneywell) / 2;
            StartLowMicrosoft = (AverageMicrosoft + LowMicrosoft) / 2;
            StartHighMicrosoft = (AverageMicrosoft + HighMicrosoft) / 2;

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

            i++;
            //Calculates and prints net worth
            System.out.println("Net worth::" + (
                (honeywellStock * stocks.getCurrentStock("Honeywell"))
                  + (WalmartStock * stocks.getCurrentStock("Walmart"))
                   + (googleStock * stocks.getCurrentStock("Google"))
                    + (nvidiaStock * stocks.getCurrentStock("Nvidia"))
                     + (cokeStock * stocks.getCurrentStock("Coke-Cola"))
                      + (MicrosoftStock * stocks.getCurrentStock("Microsoft"))
                      + bankAccount) );
            if(bankAccount >= 5000){
                System.out.println("The Bank Account Reached $5000. The code will continue until it reaches the bonus : $8000");
            }
            else if(bankAccount >= 8000){
                break;
            }
        }

    }
}