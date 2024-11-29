package app.src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    private double balance;
    private final Object fileLock = new Object();

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    /**
     * Returns the current account balance, rounded to the nearest hundredth.
     * <p>
     * The account balance cannot go negative. Transactions that would result in a
     * negative balance are cancelled.
     *
     * @return The current account balance (double).
     */
    public synchronized double getBalance() {
        return Math.round(balance * 100.0) / 100.0;
    }

    /**
     * Deposits funds into the account. This method is intended for internal
     * use within the {@code StockMarket} class.
     *
     * @param amount      The amount to deposit (must be a positive, non-zero
     *                    value).
     * @param description A description of the deposit transaction.
     * @return True if the deposit was successful, false otherwise. Returns false if
     *         the provided amount is invalid.
     * @throws NullPointerException if the description is null.
     */
    public synchronized boolean deposit(double amount, String description) {
        Math.abs(amount);
        if (amount > 0) {
            balance += amount;
            transact(amount, "DEPOSIT", description);
            return true;
        } else {
            Logger.logEvent(Logger.LogLevel.WARN, Constants.A_SOURCE, Constants.ERR_A_DEPOSIT);
            return false;
        }
    }

    /**
     * Withdraws funds from the account. This method is intended for internal
     * use within the {@code StockMarket} class.
     *
     * @param amount      The amount to withdraw (must be a positive, non-zero value
     *                    less than or equal to the current balance).
     * @param description A description of the withdrawal transaction.
     * @return True if the withdrawal was successful, false otherwise. Returns false
     *         if the provided amount is invalid or insufficient funds are
     *         available.
     * @throws NullPointerException if the description is null.
     */
    public synchronized boolean withdraw(double amount, String description) {
        Math.abs(amount);
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transact(-amount, "WITHDRAWAL", description);
            return true;
        } else {
            Logger.logEvent(Logger.LogLevel.WARN, Constants.A_SOURCE, Constants.ERR_A_WITHDRAW);
            return false;
        }
    }

    private void transact(double amount, String type, String description) {
        Logger.logEvent(Logger.LogLevel.DEBUG, Constants.A_SOURCE,
                "Transaction successful. " + type == "DEPOSIT" ? Constants.ANSI_GREEN
                        : Constants.ANSI_RED + type + ": " + amount + Constants.ANSI_RESET + ". New balance: $  "
                                + Constants.ANSI_CYAN + balance
                                + Constants.ANSI_RESET);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS");
        String formattedDateTime = now.format(formatter);
        String transactionRecord = formattedDateTime + "," + type + "," + amount + "," + description + ","
                + balance;

        synchronized (fileLock) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("./app/logs/transactions.csv", true))) {
                writer.write(transactionRecord);
                writer.newLine();
                if (Main.end) {
                    writer.flush();
                }
            } catch (IOException e) {
                Logger.logEvent(Logger.LogLevel.ERROR, Constants.A_SOURCE,
                        "Error writing transaction to file: " + e.getMessage());
            }
        }
    }
}
