public class Stocks extends Thread{
    private String[] stockNames = {"Coke-Cola", "Google", "Nvidia", "Lenovo", "Honeywell"};
    private double[] stockPrices = {70.23, 100.02, 900.45, 90.22, 20.35};

    public Stocks(){

    }
    @Override
    public void run(){
       while (true) {
        for (int i = 0; i < stockNames.length; i++) {
            double priceChange = 1;
            if (Math.random() > 0.5) {
                priceChange =+ Math.random() * (Math.random() * 0.05);
            } else {
                priceChange =+ Math.random() * -(Math.random() * 0.05);
            }
            stockPrices[i] += stockPrices[i] * priceChange;
        }
        try {
            Thread.sleep(100);
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
        return Math.round(stockPrices[index] * 100.0) / 100.0;
    }
}
