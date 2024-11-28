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

    public double getBalance() {
        return Math.round(balance * 100.0) / 100.0;
    }

    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transact(amount, "DEPOSIT");
        } else {
            Logger.logEvent(Logger.LogLevel.WARN, Constants.ERR_A_SOURCE, Constants.ERR_A_DEPOSIT);
        }
    }

    public synchronized boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transact(-amount, "WITHDRAWAL");
            return true;
        } else {
            Logger.logEvent(Logger.LogLevel.WARN, Constants.ERR_A_SOURCE, Constants.ERR_A_WITHDRAW);
            return false;
        }
    }

    private void transact(double amount, String type) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS");
        String formattedDateTime = now.format(formatter);
        String transactionRecord = formattedDateTime + "," + type + "," + amount + "," + getBalance();

        synchronized (fileLock) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("./app/logs/transactions.csv", true))) {
                writer.write(transactionRecord);
                writer.newLine();
                if (Main.end) {
                    writer.flush();
                }
            } catch (IOException e) {
                Logger.logEvent(Logger.LogLevel.ERROR, Constants.ERR_A_SOURCE,
                        "Error writing transaction to file: " + e.getMessage());
            }
        }
    }
}
