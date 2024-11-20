public class Stocks extends Thread{
    private String[] stockNames = {"Coke-Cola", "Google", "Nvidia", "Lenovo", "Honeywell"};
    private double[] stockPrices = {20.35, 100.02, 900.45, 90.22, 70.23};

    public Stocks(){

    }
    @Override
    public void run(){
       while (true) {
                for (int i = 0; i < stockNames.length; i++) {
            double priceChange = Math.random() * 0.15 - 0.01;
            stockPrices[i] += stockPrices[i] * priceChange;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }        
       } 

    }

    public double getCurrentStock(String company) {
        int index = -1;
        for (int i = 0; i < stockNames.length; i++) {
            if (stockNames[i].equals(company)) {
                index = i;
                break;
            }
        }
        return stockPrices[index];
    }
}
