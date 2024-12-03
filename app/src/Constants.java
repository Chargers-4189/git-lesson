package app.src;

public class Constants {
    private Constants() {
    }

    public static final String ANSI_RESET = Main.showConsoleColor ? "\u001B[0m" : "";
    public static final String ANSI_BLACK = Main.showConsoleColor ? "\u001B[30m" : "";
    public static final String ANSI_RED = Main.showConsoleColor ? "\u001B[31m" : "";
    public static final String ANSI_GREEN = Main.showConsoleColor ? "\u001B[32m" : "";
    public static final String ANSI_YELLOW = Main.showConsoleColor ? "\u001B[33m" : "";
    public static final String ANSI_BLUE = Main.showConsoleColor ? "\u001B[34m" : "";
    public static final String ANSI_PURPLE = Main.showConsoleColor ? "\u001B[35m" : "";
    public static final String ANSI_CYAN = Main.showConsoleColor ? "\u001B[36m" : "";
    public static final String ANSI_WHITE = Main.showConsoleColor ? "\u001B[37m" : "";

    // Stock Market
    public static final double HIGH_VOLATILITY_FACTOR = 0.20;
    public static final double MEDIUM_VOLATILITY_FACTOR = 0.10;
    public static final double LOW_VOLATILITY_FACTOR = 0.03;

    public static final double MAX_VOLATILITY = 0.6;
    public static final double MIN_VOLATILITY = 0.2;
    public static final double RANDOMNESS_FACTOR = 2.5;

    public static final double shareFactor = 0.005;

    public static final String M_SOURCE = "MARKET";
    public static final String STOCK_FILE = "./app/logs/stock-market.csv";

    public static final String ERR_M_COMP_NAME = "Company name lookup failed: Invalid company name provided: ";
    public static final String ERR_M_BUY_SHARE = "Purchasing share failed: Transaction cancelled by account.";
    public static final String ERR_M_SELL_SHARE = "Selling share failed: No shares left to sell.";

    // Account
    public static final double INITIAL_BALANCE = 1000;
    public static final String TRANSACTION_FILE = "./app/logs/transactions.csv";

    public static final String A_SOURCE = "ACCOUNT";
    public static final String ERR_A_DEPOSIT = "Invalid deposit amount. You cannot deposit $0 or less.";
    public static final String ERR_A_WITHDRAW = "Insufficient funds. Please make more money.";

    // Main
    public static final String MAIN_SOURCE = "MAIN";
    public static final String ERR = "MAIN";

    // Logger
    public static final String L_SOURCE = "LOGGER";
    public static final String LOG_FILE = "./app/logs/app.log";

}
