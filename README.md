# 11/20/2024 - Wednesday Activity

Created By: Caleb & JD

# GitHub Full Course

Today, you will learn how Git and GitHub works by creating an application collaboratively. Additionally, the basics of Git and conventions to use when doing a pull request or commit.

## Application Specs

Create a simple stock application that will generate profit. You will use the strategy of `buy low and sell high` for this application. There are 5 stocks that are available with different starting values and varying levels of risk.

Here are the 5 companies and their details.
| Company | Coca-Cola | Google | Nvidia | Lenovo | Honey Well |
| -------------------- | --------- | ------- | ------- | ------ | ---------- |
| Starting Stock Value | $64.38 | $169.43 | $136.02 | $9.09 | $230.60 |
| Perceived Risk Level | Low | High | Medium | Low | High |

`1 Second = 1 Day`

These values will change every second, representing one full day. The prices will follow a randomly fluctuating graph. The choice to buy or sell during a second will depend on the graph. The Perceived Risk Level is the chance of a stock performing very well or bad.

### Rules

Your application must meet these requirements to be complete.
| Starting Budget | $1,000 |
| --------------- | -------- |
| Target Goal | $10,000 |
| Timeframe | 180 Days |

1. The application must reach at least $10,000 by the 180th day (roughly 6 months) from it's starting budget of $1,000.
2. The application must track the amount of stock per company it purchases or sells per day.
3. The application must track the net worth of purchasing and selling of stocks.
4. On the 180th day, the application must report how many stocks it bought and sold for each company.
5. The application must surpass the $10,000 target 50% of all runs to pass the requirement.

### Starter Code

The available resources for your application to gather is the `getCompanies()` and `getCurrentStock()` methods from the `Stocks` class.

```java
/**
 * Returns a String array of company names available for stocks.
 * @return The String array of company names.
 */
String[] companies = stocks.getCompanies();
/**
 * Returns the double of a requested company's stock price.
 * @param company Company name in lowercase
 * @return The price of the requested company's stock
 */
double colaStock = stocks.getCurrentStock("coca-cola");
```

<details>
  <summary>Starting your application (Click to reveal)</summary>
  
  The Stock class is changing every second. So your application also needs to keep up with the thread. To simplify the process, here is example code to get you started and in sync with the emulated Stock market.
  
  ```java
public class Main {
    public static Stocks stocks = new Stocks(); // Import Stocks object to get information.
    public static void main(String[] args) {
        stocks.start(); // Needed to start Stock market. Do not put in loop.
        while (true) { // To constantly check for new prices and calculate.
            try { // Try Catch around the sleep is necessary.
                Thread.sleep(1000); // Wait every second to prevent unnecessary calculations between days.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Access the Stocks methods with the dot operator.
            stocks.getCompanies();
            stocks.getCurrentStock("coca-cola"); 
        }
    }
}
  ```

</details>

## GitHub Intro

[Link GitHub to VSCode](https://docs.google.com/presentation/d/1sSJEyJDiNvWRryz5Uf32xsFG6aphf1amna9CFw5BIJU/edit?usp=sharing)
