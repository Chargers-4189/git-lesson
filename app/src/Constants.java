package app.src;

public class Constants {
    private Constants() {
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Stock Market
    public static final double HIGH_VOLATILITY_FACTOR = 0.20;
    public static final double MEDIUM_VOLATILITY_FACTOR = 0.10;
    public static final double LOW_VOLATILITY_FACTOR = 0.03;

    public static final double MAX_VOLATILITY = 0.6;
    public static final double MIN_VOLATILITY = 0.2;
    public static final double RANDOMNESS_FACTOR = 2.5;

    public static final double shareFactor = 0.005;

    public static final String M_SOURCE = "MARKET";
    public static final String ERR_M_COMP_NAME = "Company name lookup failed: Invalid company name provided.";
    public static final String ERR_M_BUY_SHARE = "Purchasing share failed: Transaction cancelled by account.";
    public static final String ERR_M_SELL_SHARE = "Selling share failed: No shares left to sell.";

    // Account
    public static final double INITIAL_BALANCE = 1000;

    public static final String A_SOURCE = "ACCOUNT";
    public static final String ERR_A_DEPOSIT = "Invalid deposit amount. You cannot deposit $0 or less.";
    public static final String ERR_A_WITHDRAW = "Insufficient funds. Please make more money.";

    // Main
    public static final String MAIN_SOURCE = "MAIN";
}
