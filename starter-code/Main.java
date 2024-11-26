public class Main {

  public static Stocks stocks = new Stocks();

  public static void main(String[] args) {
    stocks.start();

    String[] companies = stocks.getCompanies();
    double colaStock = stocks.getCurrentStock("coca-cola");

    System.out.println(companies);
    System.out.println(colaStock);
  }
}
