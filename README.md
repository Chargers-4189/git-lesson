# 11/20/2024 - Wednesday Activity

Created By: Caleb & JD

Today, you will learn how Git and GitHub works by creating an application collaboratively. Additionally, the basics of Git and conventions to use when doing a pull request or commit.

# Application Specs

Create a simple stock application that will generate profit. You will use the strategy of `buy low and sell high` for this application. There are six stocks that are available with different starting values and varying levels of risk.

Here are the six companies and their details.
| Company | Coke | Walmart | Google | Nvidia | Microsoft | Honey Well |
| -------------------- | --------- | --------- | ------- | ------- | ------ | ---------- |
| Starting Stock Value | $64.38 | $91.88 | $169.43 | $136.02 | $422.99 | $230.60 |
| Starting Volatile Level | Low | Medium | High | Medium | High | High |

`50 Milliseconds = 1 Day`

These values will change every 50 milliseconds (configurable), representing one full day. The prices will randomly fluctuate and the choice to buy or sell during a cycle will depend on the graph. The Volatile Level is the chance of a stock's price fluctuating extremely or not much.

## Rules

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
7. The application must surpass the $5,000 target 25% of all random runs to pass the requirement.

## More Details

Use this example Stock Simulated Example to see the changes in data that occurs with varying volatile levels.

> **NOTICE:**
>
> This example was ran without any shares purchases or sold on the stock market. When your app buys or sells a share, there is a miniscule change in price which will affect future stock prices. This is minimal and will still generate the roughly the same shaped graph, but do not solely rely entirely on the graphs shown here.

Click here to view the Google Sheets: [Stock Simulated Example](https://docs.google.com/spreadsheets/d/1BrxBgBGBoQWM3y0zQTZ1_J-O4bmjuNbWNlnCV3NnQm4/edit?usp=sharing)

### Volatility Level

As mentioned before, the Volatile Level is the chance of a stock's price fluctuating extremely or not much. In other words, when it is LOW, the price change is minimal, only a couple dollars change. When it is HIGH, the price change is substantial, tens or fifty dollars change. This is not directional; meaning at a HIGH level, the price will not always drastically increase. It can decrease the price immensely resulting in loses.

You can see the affects in this table:
| Google's Stock | Day 163 | Day 164 | Day 165 | Day 166 | Day 167 | Day 168 | Day 169 | Day 170 | Day 171 | Day 172 |
|------------------|---------|---------|---------|---------|---------|---------|---------|---------|---------|---------|
| Volatility Level | LOW | LOW | LOW | LOW | LOW | HIGH | HIGH | HIGH | HIGH | HIGH |
| Stock Price | $100.55 | $100.72 | $100.71 | $96.44 | $97.35 | $97.07 | $87.92 | $88.43 | $103.13 | $123.28 |

Google's stock price dramatically dropped and rose when the volatility level switch to HIGH. Additionally, the LOW section where all of the price changes are within $5. You can see the difference between Day 169 and 170 is not much. This is because when volatility is HIGH, the chance of fluctuating extremely is more frequent, but regular changes can still occur.

A simple algorithm to use this feature:

> - As long as the volatility level is LOW or MEDIUM -> do not sell.
> - If Volatility level is HIGH and price has drastically decreased -> sell.
> - If Volatility level is HIGH and price has drastically increased -> do not sell.

LOW levels will typically represent peaks and troughs where it is stable. While MEDIUM and HIGH typically represents an increasing or decreasing stock price. You can incorporate much more complex algorithms that utilize past data to estimate. But remember that most of the fluctuations can be random and may drop to very low amounts.

## Starter Code

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

### Let's break it down

<details>
  <summary>Imports - Click to reveal</summary>
You don't need to worry about this portion of the app. It is importing the required dependencies for the app to work.

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

The Stock class is changing every second. So your application also needs to keep up with the thread, which is why it is in a loop with a `Thread.sleep()`. Inside the loop and after the `try_catch` is where your actual application will exist. You will use the given methods from `StockMarket` and `Account` to keep track of, purchase, and sell stocks.

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

## Methods

These methods will interact with the stock market so that you can buy and sell stocks. Here are the available methods for your application. Methods not mentioned here will not be allowed in your application.

### From `/app/src/StockMarket.java`

<details>
<summary>Methods - Click to reveal</summary>

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

</details>

### From `/app/src/Account.java`

<details>
<summary>Methods - Click to reveal</summary>

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

</details>

### From `/app/src/Main.java`

<details>
<summary>Methods - Click to reveal</summary>

```java
/**
 * Returns the current day of the stock market.
 *
 * @return The number of days since the market opened.
 */
Main.getDay();
```

</details>

# GitHub Course

Click on the [`github-course.md`](./github-course.md) to get started on learning Git and GitHub.

It is recommended to read the whole article. However, you can skim the sections and use it as a guide when you encounter issues or new areas.

Once complete, you can move onto the next section.

# Activity Rubric

You will be using this GitHub repository for today's course:

- Copy this `https://github.com/Chargers-4189/git-lesson.git` or [View the repository online](https://github.com/Chargers-4189/git-lesson).

## Before You Start

Designate 3 roles to your group:

- **Branch Maintainer:**

  Actively identifies and removes obsolete branches to prevent clutter and maintain a clear development history. Responsible for the health and organization of the branches. This includes creating and deleting branches as needed, enforcing branching strategies, reviewing pull requests for adherence to best practices, and ensuring a clean and efficient branch structure.

- **PR Reviewer:**

  Reviews pull requests submitted by developers, ensuring code quality, functionality, and adherence to coding standards. Provides constructive feedback, identifies potential bugs or issues, and approves or requests changes to ensure the code meets the project's requirements before merging.

- **Developers:**

  Develop and implement features and bug fixes. Responsible for writing clean, well-documented code, creating and managing their feature branches, and submitting pull requests for review. Actively participate in code reviews and incorporate feedback from reviewers to improve code quality.

Depending on your group size, multiple people may need to be designated multiple roles. However, ensure your group has at least one Branch Maintainer and two PR Reviewers for this activity. Branch maintainers can be your officers or mentors.

## Branch Layout

**Do not touch the main branch.**

The main branch contains the lesson resources and starter code for students. You must make a branch off of main to start developing your app.

You'll use the prefix `24-app` for all your branches to preserve this year's project from future student projects. Here is your branch layout, your branch maintainer should create these.

_You will create these primary branches to aid in development._

- `24-app`

  The main production branch. This branch contains the stable, released version of the application.

- `24-app/[release]`

  The release branch. Used to prepare a specific version of the application for release. The `[release]` portion will be replaced with a version number (e.g., `24-app/v1.0.0`).

- `24-app/development`

  The main development branch. Features and bug fixes are integrated here before being merged into `24-app`.

_These are prefixes that you would add in the name of your branch. Do not create these branches._

- `24-app/feature`

  Branches created for new features (e.g., `24-app/feature/buy-algorithm`). Each feature will have its own branch.

- `24-app/bugfix`

  Branches created to address bugs (e.g., `24-app/bugfix/buy-error`). Each bug fix will have its own branch.

- `24-app/hotfix`

  Branches created for urgent bug fixes to the production version. These branches will be branched directly from `24-app` and merged back into both `24-app` and `24-app/development`.

> What's the difference between main and releases?
>
> - **`main`** is always the most up-to-date, stable version of your code. New features are integrated here _after_ thorough testing and review.
> - **`release`** is a temporary branch created from `main` specifically for preparing a particular release. It’s a stable snapshot used for final testing and packaging before deployment. After release, it's typically merged back into `main` and then deleted. This keeps your `main` branch clean and focused on the current stable state.

Do not directly code on these branches. The purpose of these branches is to organize and separate your development on the application. Here are some examples:

- New feature or bug fix

  - Branch off of `24-app/development` and name the new branch with the prefix `24-app/feature` or `24-app/bugfix` and the name of the feature/bug.
    - For example: `24-app/feature/sell-algorithm`
  - In this branch, develop, test, and commit your changes.
  - Once finished, create a pull request to merge the feature/bug into `24-app/develop`
  - Request approval from developers to catch any mistakes or code conventions.
  - Once approved, merge the branch into `24-app/development`

- Creating a release

  - When releasing a version of your app to the main (`24-app`), you must first prepare it in `24-app/[release]` by pushing your changes from `24-app/development`. This allows for last-minute changes and minor bug fixes. Releases helps the develop branch to work on features that will be in the next release (however this might not be needed for a small application).

    - Ensure the develop branch is in a state ready for release. All features and bug fixes intended for this release should be merged into `24-app/development`.
    - Create a branch from develop specifically for this release. We'll use `24-app/v1.0.0` as an example.
    - Test `24-app/v1.0.0` branch to ensure stability and correctness.
    - Then you can add bugfixes or hotfixes if needed. Adding to the branch name after each addition: `24-app/v1.0.1`

  - As always, when releases are stable and ready to be fully released to the public, it is merged to main. Here we need to do three things: Merge release into main, create a tag for future reference, and merge release into development to continue work on the next release.

    - Create a pull request to merge `24-app/v1.0.1` into `24-app`.
    - Request approval from developers to catch any mistakes or code conventions.
    - After approval and merge, tag the release branch with a version number. [Use this article to learn how →](https://docs.github.com/en/desktop/managing-commits/managing-tags-in-github-desktop)
    - Then you can merge `24-app/v1.0.1` into `24-app/development`, so it contains the bugfixes and hotfixes we made in this release.

  > As mentioned, this application has a small team and doesn't require many releases. However, this process will be used in massive projects that require testing and a lot of development work. This activity will give you some experience in this space so you can understand how it works in larger projects.

You can review this image for reference:
![Illustration of Git Workflow](https://nvie.com/img/git-model@2x.png)

## Rubric

You will be graded based on how well your teammates communicate and collaborate with branching and pull requests. Here is the full rubric details:

|            Criterion             |                                                     4 - Excellent (10 points)                                                     |                                             3 - Good (7 points)                                             |                                           2 - Fair (4 points)                                            |                              1 - Poor (1 point)                               |  0 - Not Attempted (0 points)   |
| :------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------: | :-----------------------------: |
| Meets Requirements (Rules 1-5):  |                              All requirements (target goal, tracking, restrictions) flawlessly met.                               |                         Most requirements met; minor omissions or inconsistencies.                          |                           Several requirements unmet or significantly flawed.                            |         Most requirements unmet; application largely non-functional.          |   Application not submitted.    |
| Algorithm Performance (Rule 7):  |                          Algorithm consistently surpasses the $5,000 target in over 25% of random runs.                           |                Algorithm surpasses the target in over 10% of runs, with occasional failures.                |                   Algorithm surpasses the target in under 10% of runs; many failures.                    |                Algorithm rarely or never surpasses the target.                |           Not tested.           |
|  Code Clarity and Readability:   |                                 Code is well-organized, well-documented, and easy to understand.                                  |           Code is generally understandable, but could benefit from more comments or organization.           |              Code is difficult to understand; lack of comments or inconsistent formatting.               |           Code is poorly written, unreadable, and lacks structure.            | Code is missing or unorganized. |
|       Branching Strategy:        | Consistent use of feature, bugfix, and release branches; clear naming conventions; effective use of main and development branches |                 Mostly correct branching; minor inconsistencies in naming or organization.                  |       Some misuse of branches; inconsistent naming; difficulty understanding the branch structure.       |  Significant misuse of branches; confusing or disorganized branch structure.  |        No branches used.        |
|         Commit Messages:         |                  All commits have clear, concise, and informative messages following the specified conventions.                   |                        Most commits have good messages; some minor inconsistencies.                         |               Many commits lack sufficient information or follow inconsistent conventions.               |         Commit messages are unclear, incomplete, or missing entirely.         |        No commits made.         |
|          Pull Requests:          |    All pull requests are well-written, have clear descriptions, and include thorough testing information. On-time submissions.    | Most pull requests are adequate; some could benefit from more detailed descriptions or testing information. | Pull requests are poorly written, lack sufficient descriptions, or have inadequate testing information.  |   Pull requests are missing or severely lacking in quality and information.   |   No pull requests submitted.   |
|  Collaboration and Code Review:  |      Active participation in code review; constructive feedback provided; timely responses to comments; conflict resolution.      |                 Participation in code review; feedback provided; some delays in responses.                  | Minimal participation in code review; feedback is limited or unhelpful; significant delays in responses. | Little to no participation in code review; feedback is missing or irrelevant. |  No code review participation.  |
| Issue Tracking & Communication:  |                          Effective use of issues for questions and to inform others about code reviews.                           |          Primarily used GitHub for communication; some minor mistakes in using issues or comments.          |                                          Partial use of GitHub.                                          |                   Limited use of GitHub for communication.                    |    No use of GitHub Issues.     |
| Collaboration via GitHub Issues: |                    Thorough planning and role assignment fully documented and discussed through GitHub Issues.                    |                   Most planning and role assignment details documented on GitHub Issues.                    |                      Limited use of GitHub Issues for planning and role assignment.                      |           No use of GitHub Issues for planning and role assignment.           | No planning or role assignment. |

**Total Points: 90**

### Additional Details

- Pull requests are required before merging.
- 2 Review approvals are required (2 developers must review and approve before merge).
- Conversation is required before merging.
- Utilize the issues page to ask questions or inform others about a code review.
- It is recommended to fully communicate through GitHub (No Gmail or Discord) to get the hang of using issues, comments, and pull requests.
- To plan out and assign roles, use the issues page to collaborate and discuss with other developers.

The better you can use GitHub to it's full potential, the better we can develop and push code without error!

# Go Create your Application!

Now that you've learned the fundamentals of GitHub, it's time to apply it in your application.

If any issues arises or you have questions/concerns, please contact us.

**Good luck and have fun! 🎉🎊**
