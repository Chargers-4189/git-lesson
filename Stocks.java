public class Stocks extends Thread{
    private String[] stockNames = {"Coke-Cola", "Google", "Nvidia", "Lenovo", "Honeywell"};
    private double[] stockPrices = {20.35, 100.02, 900.45, 90.22, 70.23};

    public Stocks(){

    }
    @Override
    public void run(){
       while (true) {
<<<<<<< HEAD
        for (int i = 0; i < stockNames.length; i++) {
            double priceChange = 1;
            if (Math.random() >= 0.5) {
                priceChange =+ Math.random() * 0.025;
            } else {
                priceChange =+ Math.random() * -0.025;
            }
            stockPrices[i] += stockPrices[i] * priceChange;
=======
                for (int i = 0; i < stockNames.length; i++) {
            double priceChange = Math.random() * 5 - 2.4;
            if(0<stockPrices[i]+priceChange){
                stockPrices[i] += priceChange;
            }else{
                stockPrices[i]=0;
            }
            
>>>>>>> 8d8b416 (findAverage)
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
