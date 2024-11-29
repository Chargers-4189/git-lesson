# 11/20/2024 - Wednesday Activity

Created By: Caleb & JD

# GitHub Lesson

Today, you will learn how Git and GitHub works by creating an application collaboratively. Additionally, the basics of Git and conventions to use when doing a pull request or commit.

## Application Specs

Create a simple stock application that will generate profit. You will use the strategy of `buy low and sell high` for this application. There are six stocks that are available with different starting values and varying levels of risk.

Here are the six companies and their details.
| Company | Coke | Walmart | Google | Nvidia | Microsoft | Honey Well |
| -------------------- | --------- | --------- | ------- | ------- | ------ | ---------- |
| Starting Stock Value | $64.38 | $91.88 | $169.43 | $136.02 | $422.99 | $230.60 |
| Volatile Level | Low | Medium | High | Medium | High | High |

`50 Milliseconds = 1 Day`

These values will change every 50 milliseconds (configurable), representing one full day. The prices will randomly fluctuate and the choice to buy or sell during a cycle will depend on the graph. The Volatile Level is the chance of a stock's price fluctuating extremely or not much.

### Rules

Your application must meet these requirements to be complete.
| Starting Budget | $1,000 |
| --------------- | -------- |
| Target Goal | $5,000 |
| Bonus Goal | $8,000 |
| Time frame | 365 Days |

1. The application must reach at least $5,000 by the 365th day (one year) from it's starting budget of $1,000.
2. The application must track the amount of stock per company it purchases or sells per day.
3. The application must track the net worth of purchasing and selling of stocks.
4. On the 365th day, the application must report how many stocks it bought and sold for each company.
5. The application cannot take or give money from any outside methods. Change in balance must be done by the two methods of `buyStock()` and `sellStock()`.
6. You can only modify the code in the `/app/student` directory. You cannot modify the code in `/app/src/` directory.
7. The application must surpass the $5,000 target 50% of all random runs to pass the requirement.

### More Details

Use this example Stock Simulated Example to see the changes in data that occurs with varying volatile levels.

> **NOTICE:**
>
> This example was ran without any shares purchases or sold on the stock market. When your app buys or sells a share, there is a miniscule change in price which will affect future stock prices. This is minimal and will still generate the roughly the same shaped graph, but do not solely rely entirely on the graphs shown here.

Click here to view the Google Sheets: [Stock Simulated Example](https://docs.google.com/spreadsheets/d/1BrxBgBGBoQWM3y0zQTZ1_J-O4bmjuNbWNlnCV3NnQm4/edit?usp=sharing)

#### Volatility Level

As mentioned before, the Volatile Level is the chance of a stock's price fluctuating extremely or not much. In other words, when it is LOW, the price change is minimal, only a couple dollars change. When it is HIGH, the price change is substantial, tens or fifty dollars change. This is not directional; meaning at a HIGH level, the price will not always drastically increase. It can decrease the price immensely resulting in loses.

You can see the affects in this table:
| Google's Stock | Day 163 | Day 164 | Day 165 | Day 166 | Day 167 | Day 168 | Day 169 | Day 170 | Day 171 | Day 172 |
|------------------|---------|---------|---------|---------|---------|---------|---------|---------|---------|---------|
| Volatility Level | LOW | LOW | LOW | LOW | LOW | HIGH | HIGH | HIGH | HIGH | HIGH |
| Stock Price | 100.55 | 100.72 | 100.71 | 96.44 | 97.35 | 97.07 | 87.92 | 88.43 | 103.13 | 123.28 |

Google's stock price dramatically dropped and rose when the volatility level switch to HIGH. You can see the difference between Day 169 and 170 is not much. This is because when volatility is HIGH, the chance of fluctuating extremely it more frequent, but regular changes can still occur. You can also see that with the LOW section where all of the price changes are within $5.

A simple algorithm to use this feature:

> - As long as the volatility level is LOW or MEDIUM -> do not sell.
> - If Volatility level is HIGH and price has drastically decreased -> sell.
> - If Volatility level is HIGH and price has drastically increased -> do not sell.

LOW levels will typically represent peaks and troughs where it is stable. While MEDIUM and HIGH typically represents an increasing or decreasing stock price. You can incorporate much more complex algorithms that utilize past data to estimate. But remember that most of the fluctuations can be random and may drop to very low amounts.

### Starter Code

Let's look in `/app/student/StockApp.java`

```java
package app.student;

import app.src.Account;
import app.src.Main;
import app.src.StockMarket;

public class StockApp extends Thread {

  private final StockMarket stockMarket;
  private final Account account;
  private static volatile boolean runApp = true;

  public StockApp(StockMarket stockMarket, Account account) {
    this.stockMarket = stockMarket;
    this.account = account;

    // STOCK MARKET CONFIGURATIONS
    Main.setMsPerDay(50); // Default 50. Use lower milliseconds for quicker testing.
    Main.setMaxOpenDays(365); // Default 365. Use lower days for quicker testing.
    stockMarket.setRandomSeed(948573627495667526L); // Set Stock Market generation seed. Set to 0 for random seed.
  }

  public void end() {
    runApp = false;
  }

  @Override
  public void run() {
    while (runApp) {
      try {
        Thread.sleep(Main.getMsPerDay());
      } catch (Exception e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
      // Your Code Here
      // Available Companies: coke walmart google nvidia microsoft honeywell

      // Below is example code
      String company = "honeywell";
      double price = stockMarket.getStockPrice(company);
      String volatileLvl = stockMarket.getVolatileLevel(company);
      double balance = account.getBalance();

      System.out.println(
          company +
              " | Volatile Level: " +
              volatileLvl +
              " | Stock Price: " +
              price +
              " | Balance: " +
              balance);

    }
  }
}

```

#### Let's break it down

<details>
  <summary>Imports - Click to reveal</summary>
You don't need to worry about this portion of the app. It is mainly importing the required dependencies for the app to work.

```java
package app.student;

import app.src.Account;
import app.src.Main;
import app.src.StockMarket;

public class StockApp extends Thread {

  private final StockMarket stockMarket;
  private final Account account;
  private static volatile boolean runApp = true;

  public StockApp(StockMarket stockMarket, Account account) {
    this.stockMarket = stockMarket;
    this.account = account;
```

</details>

<details>
<summary>Configurations - Click to reveal</summary>
Here you can configure three configurations for the Stock Market.

- `Main.setMsPerDay()` - Sets the time step (in milliseconds) between simulation cycles. Each cycle represents one day. It is recommended to set the value above 50 to prevent calculation bugs when running the application rapidly. Changing the cycle may also change stock price results.
- `Main.setMaxOpenDays()` - Sets the maximum number of days the stock market will be open for in the simulation. Configure this to lower values for quick testing.
- `stockMarket.setRandomSeed()` - Sets the seed for the random number generator used in the stock market simulation. Setting a seed value other than 0 will produce repeatable results for the same seed value. Using a seed of 0 will use a randomly generated seed.
  - Your app will start with the seed `948573627495667526L` as a benchmark. This just helps as a starting point so you can see what algorithm increase overall. However, you app will need to work regardless of the seed. Do not create algorithms solely around this seed, as it can fail with other random seeds.

```java

    // STOCK MARKET CONFIGURATIONS
    Main.setMsPerDay(50);
    Main.setMaxOpenDays(365);
    stockMarket.setRandomSeed(948573627495667526L);
}
```

</details>

<details>
<summary>Code - Click to reveal</summary>

The Stock class is changing every second. So your application also needs to keep up with the thread, which is why it is in a loop with a `Thread.sleep()`. Inside the loop and after the `try_catch` is where your actual application will exist. You will use the given methods from `StockMarket` and `Account` to purchase, keep track of, and sell stocks.

```java
  @Override
  public void run() {
    while (runApp) {
      try {
        Thread.sleep(Main.getMsPerDay());
      } catch (Exception e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
      // Your Code Here
      // Available Companies: coke walmart google nvidia microsoft honeywell

      // Below is example code
      String company = "honeywell";
      double price = stockMarket.getStockPrice(company);
      String volatileLvl = stockMarket.getVolatileLevel(company);
      double balance = account.getBalance();

      System.out.println(
          company +
              " | Volatile Level: " +
              volatileLvl +
              " | Stock Price: " +
              price +
              " | Balance: " +
              balance);

    }
  }
}
```

</details>

### Methods

These methods will interact with the stock market so that you can buy and sell stocks. Here are the available methods for your application. Others not mentioned will not be allowed in your application.

#### From `/app/src/StockMarket.java`

```java
/**
 * Returns an array of company names whose stocks are available for trading.
 *
 * @return An array of company names (Strings). Returns an empty array if there
 *         are no stocks.
 */
stockMarket.getCompanies(company);
```

```java
/**
 * Returns the current price of a specified company's stock.
 *
 * @param company The name of the company (case-insensitive).
 * @return The stock price (double). Returns -1 if the company is not found.
 */
stockMarket.getStockPrice(company);
```

```java
/**
 * Returns the volatility level of a specified company's stock.
 *
 * @param company The name of the company (case-insensitive).
 * @return The volatility level (String). Returns an empty string if the company
 *         is not found.
 */
stockMarket.getVolatileLevel(company);
```

```java
/**
 * Returns the number of shares owned of a specified company's stock.
 *
 * @param company The name of the company (case-insensitive).
 * @return The number of shares owned (int). Returns -1 if the company is not
 *         found.
 */
stockMarket.getShares(company);
```

```java
/**
 * Purchases shares of a specified company's stock.
 * <p>
 * Cannot purchase shares if the account balance is below the price of `stock
 * price * amount of shares`. Buying shares will slightly increase the stock
 * price. This method will automatically charge the account the money due.
 *
 * @param amount  The number of shares to purchase (must be a positive, non-zero
 *                value).
 * @param company The name of the company (case-insensitive).
 * @return True if the purchase was successful, false otherwise. Returns false
 *         if the company is not found, insufficient funds are available, or the
 *         amount is invalid.
 */
stockMarket.buyShares(amount, company)
```

```java
/**
 * Sells shares of a specified company's stock.
 * <P>
 * Cannot sell shares if the total shares is 0. Buying shares will slightly
 * decrease the stock price. This method will automatically pay the account the
 * money earned.
 *
 * @param amount  The number of shares to sell (must be a positive, non-zero
 *                value).
 * @param company The name of the company (case-insensitive).
 * @return True if the sale was successful, false otherwise. Returns false if
 *         the company is not found, insufficient shares are available, or the
 *         amount is invalid.
 */
stockMarket.sellShares(amount, company)
```

#### From `/app/src/Account.java`

```java
/**
 * Returns the current account balance, rounded to the nearest hundredth.
 * <p>
 * The account balance cannot go negative. Transactions that would result in a
 * negative balance are cancelled.
 *
 * @return The current account balance (double).
 */
account.getBalance();
```

#### From `/app/src/Main.java`

```java
/**
 * Returns the current day of the stock market.
 *
 * @return The number of days since the market opened.
 */
Main.getDay();
```

## GitHub Course

Click on the [`github-course.md`](./github-course.md) to get started on learning Git and GitHub.

Once complete, you can move onto the next section.

## Using GitHub

You will be using this GitHub repository for today's course:

- Copy this `https://github.com/Chargers-4189/git-lesson.git` or [View the repository online](https://github.com/Chargers-4189/git-lesson).
