Instructions to run the program:

1. Download the JAR file containing all the supporting files onto local device.
2. Make sure that the directory in which the JAR is stored has a folder named "stocksdata" and it
should contain "stocks_data.csv file".
3. Open the command prompt and run the program b y using the command "java -jar Stocks.jar"

Following is the flow of the program:
The menu asks to choose from 3 different options. (Stage 1)
  1. Create portfolio
  2. View portfolio and speculate portfolio
  3. Exit

  Flow for option 1 - Create portfolio:
    STEP 1. Enter 1
    STEP 2. Enter the name of the portfolio.
        The name should be unique, if you enter the existing name it prompts that it is an invalid
        input or file already exists and redirects to main menu which displays the above 3 options.
    STEP 3. After entering the name it displays the list of companies for which the user can invest
        the stocks.
        The entered stock should be in the given list if not it redirects to main menu which
        displays the above 3 options.
    STEP 4. If a valid ticker symbol is entered then it displays the data for that particular
        company and asks you to choose from the below 4 options.
              1. Add this to portfolio.
              2. Do not add and search stocks for new company.
              3. Go back.
              4. Exit

                 Option 1 (Add this to portfolio.): Steps to save the stock to portfolio:
                    Select option 1 to add the current stock to portfolio.
                    Enter the number of shares to invest
                    Then this particular stock is saved and it will prompt you the below options
                      1. Add another stock
                      2. Save this portfolio. (You can not edit it after saving!!!)
                      3. Back to main menu.
                      4. Exit.

                      Option 1 will repeat the entire process from step 3 in this document.

                      Option 2 will write the current chosen stocks to the file and saves
                      it and redirect to "Stage 1"

                      Option 3 will redirect to "Stage 1" without saving your data.

                      Option 4 will exit from the program.

                 Option 2 (Do not add and search stocks for new company.):
                      Redirects to STEP 3.

                 Option 3 (Go back.):
                      Redirects to Stage 1.

                 Option 4 (EXIT):
                       Will exit from the program.


  Flow for option 2 - View portfolio and speculate portfolio:
     1. Choose from the list of companies which are displayed in the text interface.
     2. Enter the filename without the .csv extension.
         a) If filename exists
            Displays the current portfolio.
            Prompts - Would you like to speculate your portfolio?(YES/NO)
              If "YES":
                Enter the date between the given range and it will display the total portfolio value
                for that particular day and redirects to STAGE 1.
         b) File doesn't exist
            Redirect to "Stage 1"

  Flow for option 3 - Exit from program


ASSIGNMENT 5:
Create a portfolio:


